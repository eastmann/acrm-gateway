package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoldingActualDate {
    private Long id;
    private Long company_id;
    private Integer dzo_id;
    private Date fin_actual_date;
    private Date product_actual_date;
    private Date kam_actual_date;
}
