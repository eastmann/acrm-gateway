package com.rtk.acrm.gateway.facade.clients;

import com.rtk.acrm.gateway.facade.BaseRestClient;
import com.rtk.acrm.gateway.facade.dto.search.CompanyDto;
import com.rtk.acrm.gateway.session.HttpSession;
import com.rtk.acrm.gateway.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ServiceSearchClient extends BaseRestClient {
    private Logger logger = LoggerFactory.getLogger(ServiceSearchClient.class);

    @Value("${service-search.read-time-out}")
    private Integer READ_TIME_OUT;

    @Value("${service-search.connection-time-out}")
    private Integer CONNECTION_TIME_OUT;

    @Value("${zuul.routes.service-search.url}")
    private String SERVICE_SEARCH;

    @Value("${service-search.find-by-all}")
    private String FIND_BY_ALL;
    
    private String FIND_BY_ALL_URL;

    protected final HttpSession session;

    @Autowired
    public ServiceSearchClient(RestTemplate restTemplate, HttpSession session) {
        super(restTemplate);
        this.session = session;
    }

    @PostConstruct
    public void init() {
        FIND_BY_ALL_URL = UrlUtils.joinUrl(SERVICE_SEARCH, FIND_BY_ALL);
    }

    public List<CompanyDto> findByAll(String value){
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("value", value);
        CompanyDto[] response = getRequest(FIND_BY_ALL_URL, CompanyDto[].class, urlParams, READ_TIME_OUT, CONNECTION_TIME_OUT);
        List<CompanyDto> companyDtoList = Arrays.asList(response);

        return companyDtoList;
    }
}
