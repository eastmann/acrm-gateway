package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMargin {
    private Long id;
    private String inn;
    private String kpp;
    private String nameOrg;
    private Long dzoId;
    private SprProduct product;
    private String margin2021;
    private String margin2022;
    private String vyruchka2021;
    private String vyruchka2022;
    private Timestamp createDate;
}
