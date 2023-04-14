package com.rtk.acrm.gateway.facade.controller;

import com.rtk.acrm.gateway.facade.clients.BackendClient;
import com.rtk.acrm.gateway.facade.dto.HoldingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facade/")
public class HoldingController {
    private final BackendClient backendClient;

    @Autowired
    public HoldingController(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

    @RequestMapping("holdings")
    public HoldingsDto getHoldings(){
        HoldingsDto holdingsDto = backendClient.getHoldings();
        return holdingsDto;
    }

}
