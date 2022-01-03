package com.cfar.apis.tddashboardbackend.service;

import com.cfar.apis.tddashboardbackend.client.TDAmeritradeAPIClient;
import com.cfar.apis.tddashboardbackend.model.OverviewModel;
import com.cfar.apis.tddashboardbackend.model.PortfolioDetailsResponse;
import com.cfar.apis.tddashboardbackend.model.PositionModel;
import com.cfar.apis.tddashboardbackend.model.client.TDAuthResponse;
import com.cfar.apis.tddashboardbackend.model.client.TDGetAccountResponse;
import com.cfar.apis.tddashboardbackend.model.client.TDPositionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioDetailsService {

    @Autowired
    TDAmeritradeAPIClient ameritradeAPIClient;

    public PortfolioDetailsResponse getPortfolioDetails() {
        String authToken = getAuthToken();

        if (authToken == null) {
            throw new NullPointerException("AuthToken came back as null from TDAmeritrade API.");
        }

        TDGetAccountResponse getAccountResponse = ameritradeAPIClient.getAccount(authToken);

        return mapResponse(getAccountResponse);
    }

    public String getAuthToken() {
        TDAuthResponse authResponse = ameritradeAPIClient.authorize();

        if (authResponse.getAccessToken() != null && !authResponse.getAccessToken().isEmpty()) {
            return authResponse.getAccessToken();
        }

        return null;
    }

    private PortfolioDetailsResponse mapResponse(TDGetAccountResponse getAccountResponse) {
        PortfolioDetailsResponse response = new PortfolioDetailsResponse();
        OverviewModel overview = new OverviewModel();
        List<PositionModel> positions = new ArrayList<>();

        overview.setAccountValue(getAccountResponse.getSecuritiesAccount().getCurrentBalances().getLiquidationValue());
        overview.setCash(getAccountResponse.getSecuritiesAccount().getCurrentBalances().getMoneyMarketFund());

        float plDay = 0f;
        float plDayPercent = 0f;
        List<TDPositionModel> positionsList;

        if (getAccountResponse.getSecuritiesAccount() != null) {
            positionsList = getAccountResponse.getSecuritiesAccount().getPositions();
        } else {
            throw new RuntimeException("No securities accounts found in TDAmeritrade accounts response.");
        }

        for (TDPositionModel pos : positionsList) {
            if ("EQUITY".equals(pos.getInstrument().getAssetType())) {
                positions.add(mapPositionModelResponse(pos));
            }

            plDay += pos.getCurrentDayProfitLoss();
            plDay += pos.getCurrentDayProfitLossPercentage();
        }

        plDayPercent = plDayPercent / positions.size();

        overview.setPlDay(plDay);
        overview.setPlDayPercent(plDayPercent);

        response.setOverview(overview);
        response.setPositions(positions);

        return response;
    }

    private PositionModel mapPositionModelResponse(TDPositionModel tdPositionModel) {
        PositionModel model = new PositionModel();

        model.setNetLiq(tdPositionModel.getMarketValue());
        model.setPlDay(tdPositionModel.getCurrentDayProfitLoss());
        model.setPlDayPercent(tdPositionModel.getCurrentDayProfitLossPercentage());
        model.setQuantity(tdPositionModel.getLongQuantity() + tdPositionModel.getShortQuantity());
        model.setTicker(tdPositionModel.getInstrument().getSymbol());
        model.setTradePrice(tdPositionModel.getAveragePrice());
        model.setTradeCost(model.getTradePrice() * model.getQuantity());
        model.setPlOpen(model.getNetLiq() - model.getTradeCost());
        model.setPlOpenPercent((model.getPlOpen()/model.getTradeCost())*100);

        return model;
    }

}
