package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingAffiliatedCompanies {
    private Long id;
    private String inn;
    private String kpp;
    private Long sparkid;
    private String nameOrg;
    private String innH;
    private Double vyruchkaRtk2022;
    private Double vyruchkaTsod2022;
    private Double vyruchkaSolar2022;
    private Double vyruchkaTele22022;
    private String kamRtk;
    private String kamTsod;
    private String kamSolar;
    private String kamTele2;
    private Boolean isHolding;
}
