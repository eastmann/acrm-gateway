package com.rtk.acrm.gateway.facade.dto;

import lombok.Data;

@Data
public class AuthUserDto {
    private String login;
    private String fio;
    private Boolean active;
    private Boolean domainUser;
}
