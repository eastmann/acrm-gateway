package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingMain {
    private Long rn;
    private Long id;
    private String inn;
    private String kpp;
    private String nameOrg;
    private String innH;
    private String kppH;
    private Long dzoId;
    private Double pribDzo2020;
    private Double pribDzo2021;
    private Double pribDzo2022;
    private Double virDzo2020;
    private Double virDzo2021;
    private Double virDzo2022;
    private Double planDzo2022;
    private Double planDzo2023;
    private Double budgetDzo2022;
    private Double budgetDzo2023;
    private Double marginFact2021;
    private Double marginFact2022;
    private String keyProjectDesc;
    private Double iktGeneralBud;
    private Double iktAddressBud;
    private Double iktNonaddressBud;
    private String iktBudComment;
    private Double virEgrul2021;
    private Timestamp createDate;
    private Timestamp changeDate;
    private Double virGkRtk2022;
}
