package com.rtk.acrm.gateway.facade.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private Long companyId;
    private String holdingName;
    private Long holdingId;
    private String companyName;
    private String companyInn;
    private Boolean isHolding;
    private Float searchScore;
}
