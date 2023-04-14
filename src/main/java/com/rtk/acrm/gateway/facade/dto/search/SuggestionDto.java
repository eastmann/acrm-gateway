package com.rtk.acrm.gateway.facade.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionDto {

    private String id;
    private String hid;
    private Boolean isHilding;
    private String value;
    private Object data;
}
