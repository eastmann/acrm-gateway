package com.rtk.acrm.gateway.facade.controller;

import com.rtk.acrm.gateway.facade.clients.BackendClient;
import com.rtk.acrm.gateway.facade.clients.ServiceSearchClient;
import com.rtk.acrm.gateway.facade.dto.HoldingsDto;
import com.rtk.acrm.gateway.facade.dto.HoldingsStartPage;
import com.rtk.acrm.gateway.facade.dto.search.CompanyDto;
import com.rtk.acrm.gateway.facade.dto.search.SuggestionDto;
import com.rtk.acrm.gateway.facade.dto.search.SuggestionListDto;
import com.rtk.acrm.gateway.facade.dto.search.SuggestionsObjectData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/facade/service-search")
public class SearchController {

    private static final String LIQUIDATED_STATUS = "Ликвидирован";
    private final BackendClient backendClient;
    private final ServiceSearchClient searchClient;

    public SearchController(BackendClient backendClient, ServiceSearchClient searchClient) {
        this.backendClient = backendClient;
        this.searchClient = searchClient;
    }

//    @RequestMapping("/by-all")
//    public SuggestionListDto findByAll(@RequestParam("value") String value) {
//        SuggestionListDto suggestionListDto = new SuggestionListDto();
//        List<SuggestionDto> suggestionsDtoList = createStub(value);
//        suggestionListDto.setSuggestions(suggestionsDtoList);
//
//        return suggestionListDto;
//    }
//    private Long companyId;
//    private String holdingName;
//    private Long holdingId;
//    private String companyName;
//    private String companyInn;
//    private Boolean isHolding;
//    private Float searchScore;


    @RequestMapping("/by-all")
    public SuggestionListDto findByAll(@RequestParam("value") String value) {
        SuggestionListDto suggestionListDto = new SuggestionListDto();

        //List<SuggestionDto> suggestionsDtoList = createStub(value);
        List<SuggestionDto> dtos = new ArrayList<>();

        List<CompanyDto> search = searchClient.findByAll(value);

        for (CompanyDto el : search){
                SuggestionDto dto = new SuggestionDto();

                dto.setHid(String.valueOf(el.getHoldingId()));
                dto.setIsHilding(el.getIsHolding());
                dto.setId(String.valueOf(el.getCompanyId()));
                dto.setValue(el.getCompanyName());
                SuggestionsObjectData objectData = new SuggestionsObjectData();
                objectData.setInn(el.getCompanyInn());
                objectData.setName(el.getHoldingName());
                objectData.setAddress(el.getCompanyName());
                objectData.setStatus("");
                dto.setData(objectData);
                dtos.add(dto);
        }

        suggestionListDto.setSuggestions(dtos);

        return suggestionListDto;
    }

    private List<SuggestionDto> createStub(String value){
        if (value == null){
            return null;
        }
        List<SuggestionDto> dtos = new ArrayList<>();

        HoldingsDto holdingsDto = backendClient.getHoldings();
        for (HoldingsStartPage el : holdingsDto.getHoldings()){
            if (el.getName().toUpperCase().contains(value.toUpperCase())){
                SuggestionDto dto = new SuggestionDto();
                dto.setId(String.valueOf(el.getHid()));
                dto.setValue(el.getName());
                SuggestionsObjectData objectData = new SuggestionsObjectData();
                objectData.setInn(String.valueOf(el.getHid()));
                objectData.setName(el.getIndustry());
                objectData.setAddress(el.getName());
                objectData.setStatus("");
                dto.setData(objectData);
                dtos.add(dto);
            }
        }

        return dtos;
    }
}
