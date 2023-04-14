package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingOwnersFrontDto {
    private HoldingOwnersLevelsDto holdingOwnersDzo1;
    private HoldingOwnersLevelsDto holdingOwnersDzo2;
    private HoldingOwnersLevelsDto holdingOwnersDzo3;
    private HoldingOwnersLevelsDto holdingOwnersDzo4;
    private HoldingOwnersLevelsDto holdingOwnersDzo5;
}
