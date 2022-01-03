package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

@Data
public class TDPositionModel {
    private int shortQuantity;
    private float averagePrice;
    private float currentDayProfitLoss;
    private float currentDayProfitLossPercentage;
    private int longQuantity;
    private int settledLongQuantity;
    private int settledShortQuantity;
    private InstrumentModel instrument;
    private float marketValue;
    private float maintenanceRequirement;
    private float currentDayCost;
    private int previousSessionLongQuantity;
}
