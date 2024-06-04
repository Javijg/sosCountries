package com.sos_countries.scrapper.emergencyNumbers;

import com.sos_countries.common.CommonConstants;
import com.sos_countries.scrapper.emergencyNumbers.remoteModel.EmergencyCountryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path= CommonConstants.REST_PATH + "/sos-numbers")
public class AldiSouthScrapperRestController {

    @Autowired
    AldiSouthScrapperService scrapperService;

    @GetMapping("/country")
    public List<EmergencyCountryDetails> getItems() {
        return scrapperService.retrieveOffers();
    }


}
