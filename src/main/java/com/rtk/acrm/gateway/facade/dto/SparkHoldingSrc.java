package com.rtk.acrm.gateway.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparkHoldingSrc {
    private Long sparkid;
    private String inn;
    private String name;
    private Long vir2020;
    private String vir2020format;
    private Long vir2021;
    private String vir2021format;
    private Long allVir2020;
    private String allVir2020format;
    private Long allVir2021;
    private String allVir2021format;
    private String codeOsnOkv;
    private String nameOsnOkv;
    private String rfRegionName;
    private String rfRegionCode;
    private String kpp;
    private String sector;
    private String industry;
    private String hk3k;
}
