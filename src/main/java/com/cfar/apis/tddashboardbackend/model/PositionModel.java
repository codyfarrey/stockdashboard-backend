package com.cfar.apis.tddashboardbackend.model;

import lombok.Data;

@Data
public class PositionModel {
    private String ticker;
    private int quantity;
    private float priceChange;
    private float priceChangePercent;
    private float plDay;
    private float plOpen;
    private float plDayPercent;
    private float plOpenPercent;
    private float tradeCost;
    private float netLiq;
    private float tradePrice;
}
