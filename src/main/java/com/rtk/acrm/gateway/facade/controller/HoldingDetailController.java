package com.rtk.acrm.gateway.facade.controller;

import com.rtk.acrm.gateway.facade.clients.BackendClient;
import com.rtk.acrm.gateway.facade.dto.holding_detail.HoldingDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facade/detail-info/")
public class HoldingDetailController {
    private final BackendClient backendClient;

    @Autowired
    public HoldingDetailController(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

//    @RequestMapping("by-inn")
//    public HoldingDetailDto getHoldingDetail(@RequestParam("inn") String inn){
//        String[] params = inn.split("\\?");
//        String innVal = params[0];
//        String dzoVal = null;
//        if (params.length > 1){
//            dzoVal = params[1].replace("dzo=", "");
//        }
//        HoldingDetailDto holdingDetail = backendClient.getHoldingDetailTransform(innVal, dzoVal);
//
//        return holdingDetail;
//    }


    @RequestMapping("by-hid")
    public HoldingDetailDto getHoldingDetailByHid(@RequestParam("hid") String hid){
        HoldingDetailDto holdingDetail = backendClient.getHoldingDetailTransform(hid, null, null);

        return holdingDetail;
    }

    @RequestMapping("by-id")
    public HoldingDetailDto getHoldingDetailById(@RequestParam("id") String id){
        String[] params = id.split("\\?");
        String idVal = params[0];
        String dzoVal = null;
        if (params.length > 1){
            dzoVal = params[1].replace("dzo=", "");
        }
        HoldingDetailDto holdingDetail = backendClient.getHoldingDetailTransform(null, idVal, dzoVal);

        return holdingDetail;
    }

}
