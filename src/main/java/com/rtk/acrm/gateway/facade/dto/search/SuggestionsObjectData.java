package com.rtk.acrm.gateway.facade.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

import static com.rtk.acrm.gateway.utils.MapUtils.getStringOrNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestionsObjectData {

    private String inn;
    private String address;
    private String name;
    private String status;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("value")
    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("data")
    private void unpackCompanyData(Map<String, Object> data) {
        inn = getStringOrNull(data, "inn");
        status = getStringOrNull(data, "status");

        Map<String, Object> addressMap = (Map<String, Object>) data.get("address");

        if(addressMap != null) {
            address = getStringOrNull(addressMap, "value");
        }
    }
}
