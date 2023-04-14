package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingDetailDto {
    private List<HoldingAffiliatedCompanies> holdingAffiliatedCompanies;
    private MainInfoDto mainInfo;
    private HoldingOwnersFrontDto holdingOwners;
    private List<KamLprInfoNew> kamLprInfo;
    private List<KamInfoFrontDto> kamInfoFront;
    private List<HoldingMain> holdingMains;
    private List<HoldingMainFrontDto> holdingMainsFront;
    private List<HoldingMainFrontDto> holdingMainsTotalFront;
    private List<HoldingMainFrontDto> holdingMainsWallet;
    private List<HoldingOwners> parents;
    private List<ProductWithMarginDto> productMargin;
    private List<HoldingActualDate> holdingActualDates;
}
