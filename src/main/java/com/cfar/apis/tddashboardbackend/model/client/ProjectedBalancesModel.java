package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

@Data
public class ProjectedBalancesModel {
    private float availableFunds;
    private float availableFundsNonMarginableTrade;
    private float buyingPower;
    private float dayTradingBuyingPower;
    private float getDayTradingBuyingPowerCall;
    private float maintenanceCall;
    private float regTCall;
    private boolean isInCall;
    private float stockBuyingPower;
}
