package com.rtk.acrm.gateway.facade.dto.search;

public class SearchOrganizationDto {
    private String id;
    private String inn;
    private String shortName;
    private String fullName;
    private float searchScore;
    private int isActing;

    //поля компании
    private String kpp;
    //поля ИП
    private String ogrnip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getSearchScore() {
        return searchScore;
    }

    public void setSearchScore(float searchScore) {
        this.searchScore = searchScore;
    }

    public int getIsActing() {
        return isActing;
    }

    public void setIsActing(int isActing) {
        this.isActing = isActing;
    }

    public String getOgrnip() {
        return ogrnip;
    }

    public void setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
    }
}
