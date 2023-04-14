package com.rtk.acrm.gateway.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingsStartPage {
    private Long hid;
    private Long sparkid;
    private String inn;
    private String kpp;
    private String name;
    private String industry;
    private Double allVir2020;
    private Double allVir2021;
    private String allVir2020format;
    private String allVir2021format;
    private Long dzoId;
    private Double virRtk2021;
    private Double virRtk2022;
    private Double virTsod2021;
    private Double virTsod2022;
}
