package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingOwners {
    private Long id;
    private String companyName;
    private String inn;
    private Long kpp;
    private Long okpo;
    private Long parentId;
    private Boolean isHolding;
    private Boolean isHead;
    private Long directionId;
    private String descr;
    private Long createUserId;
    private Date createDate;
    private Long changeUserId;
    private Date changeDate;
    private Double sharePart;
    private Long lvl;
    private Long sparkid;
    private Date actual_date;
    private Long etalonParentId;
    private Long dzoId;
    private Long vir2020;
    private Long vir2021;
    private Long vir2022;
    private String okved;
    private String virEgrul2021;
    private String contracts;
    private String kam;
    private Boolean selected;
}
