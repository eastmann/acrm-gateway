package com.rtk.acrm.gateway.facade.dto.holding_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KamLprInfo {
    private Long rn;
    private Long id;
    private String inn;
    private String kpp;
    private String nameOrg;
    private String innH;
    private String kppH;
    private Long dzoId;
    private String kamH;
    private String kamHContact;
    private String kamO;
    private String kamOContact;
    private String lprH;
    private String lprHContact;
    private String lprO;
    private String lprOContact;
    private String dirH;
    private String dirHContact;
    private String dirO;
    private String dirOContact;
    private Timestamp createDate;
    private Timestamp changeDate;
    private String kamHEmail;
    private String kamOEmail;
    private String lprHEmail;
    private String lprOEmail;
    private String dirHEmail;
    private String dirOEmail;
}
