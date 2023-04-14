package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KamLprInfoNew {
    private Long rn;
    private Long company_id;
    private Integer dzo_id;
    private Integer kam_type_id;
    private String type_name;
    private String kam_name;
    private String kam_phone;
    private String kam_email;
}
