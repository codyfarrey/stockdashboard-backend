package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

import java.util.List;

@Data
public class SecuritiesAccountModel {
    private String type;
    private String accountId;
    private String roundTrips;
    private boolean isDayTrader;
    private boolean isClosingOnlyRestricted;
    private List<TDPositionModel> positions;
    private InitialBalancesModel initialBalances;
    private CurrentBalancesModel currentBalances;
    private ProjectedBalancesModel projectedBalances;
}
