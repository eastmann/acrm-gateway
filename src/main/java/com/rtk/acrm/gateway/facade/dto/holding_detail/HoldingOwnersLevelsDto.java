package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingOwnersLevelsDto {
    private List<HoldingOwners> holdingOwnersLevel1;
    private List<HoldingOwners> holdingOwnersLevel2;
    private List<HoldingOwners> holdingOwnersLevel3;
    private List<HoldingOwners> holdingOwnersLevel4;
}
