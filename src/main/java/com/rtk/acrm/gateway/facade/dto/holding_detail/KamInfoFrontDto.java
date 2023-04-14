package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KamInfoFrontDto {
    private String type;
    private String name;
    private String contact;
    private String email;
    private Long dzoId;
}
