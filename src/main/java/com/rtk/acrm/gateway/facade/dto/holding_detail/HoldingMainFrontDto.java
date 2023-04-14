package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingMainFrontDto {
    private Long id;
    private String inn;
    private String kpp;
    private Long dzoId;

    private String name;
    private String year2020;
    private String year2021;
    private String year2022;
    private String year2023;
    private String year2024;
}
