package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithMarginDto {
    Long id;
    String productName;
    Long dzoId;
    String nameOrg;
    String margin2021;
    String margin2022;
    String vyruchka2021;
    String vyruchka2022;
    String product_type;
}
