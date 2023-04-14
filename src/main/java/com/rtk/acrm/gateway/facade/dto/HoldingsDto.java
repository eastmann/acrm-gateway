package com.rtk.acrm.gateway.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingsDto {
    private List<HoldingsStartPage> holdings;
}
