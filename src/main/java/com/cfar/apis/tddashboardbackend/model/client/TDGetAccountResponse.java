package com.cfar.apis.tddashboardbackend.model.client;

import lombok.Data;

import java.io.Serializable;

@Data
public class TDGetAccountResponse implements Serializable {
    private SecuritiesAccountModel securitiesAccount;
}
