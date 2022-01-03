package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

@Data
public class InitialBalancesModel {
    private float accruedInterest;
    private float availableFundsNonMarginableTrade;
    private float bondValue;
    private float buyingPower;
    private float cashBalances;
    private float cashAvailableForTrading;
    private float cashReceipts;
    private float dayTradingBuyingPower;
    private float dayTradingBuyingPowerCall;
    private float dayTradingEquityCall;
    private float equity;
    private float equityPercentage;
    private float liquidationValue;
    private float longMarginValue;
    private float longOptionMarketValue;
    private float longStockValue;
    private float maintenanceCall;
    private float maintenanceRequirement;
    private float margin;
    private float marginEquity;
    private float moneyMarketFund;
    private float mutualFundValue;
    private float regTCall;
    private float shortMarginValue;
    private float shortOptionMarketValue;
    private float shortStockValue;
    private float totalCash;
    private boolean isInCall;
    private float pendingDeposits;
    private float marginBalance;
    private float shortBalance;
    private float accountValue;
}
