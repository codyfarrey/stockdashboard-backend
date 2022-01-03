package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

@Data
public class CurrentBalancesModel {
    private float accruedInterest;
    private float cashBalance;
    private float longOptionMarketValue;
    private float liquidationValue;
    private float moneyMarketFund;
    private float savings;
    private float shortMarketValue;
    private float pendingDeposits;
    private float availableFunds;
    private float availableFundsNonMarginableTrade;
    private float buyingPower;
    private float buyingPowerNonMarginableTrade;
    private float dayTradingBuyingPower;
    private float equity;
    private float equityPercentage;
    private float longMarginValue;
    private float maintenanceCall;
    private float maintenanceRequirement;
    private float marginBalance;
    private float regTCall;
    private float shortBalance;
    private float shortMarginValue;
    private float shortOptionMarketValue;
    private float sma;
    private float mutualFundValue;
    private float bondValue;
}
