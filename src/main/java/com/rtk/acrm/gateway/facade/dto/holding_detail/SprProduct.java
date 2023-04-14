package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SprProduct {
    private Long id;
    private String productName;
    private String com;
    private Timestamp createDate;
    private Long dzoId;
}
