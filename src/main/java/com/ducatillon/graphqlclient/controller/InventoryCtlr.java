package com.ducatillon.graphqlclient.controller;

import com.ducatillon.graphqlclient.client.CountryClient;
import com.ducatillon.graphqlclient.data.CountryDto;
import com.ducatillon.graphqlclient.servce.InventorySvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1")
public class InventoryCtlr {
    @Autowired
    private InventorySvc service;


    @RequestMapping(path = "/country/{country_id}", method = RequestMethod.GET)
    public ResponseEntity<Object>  getCountry(@PathVariable String country_id) {
        log.info("requesting '" + country_id + "'");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            CountryDto result = new CountryClient().getCountryDetails(country_id);
            return new ResponseEntity<Object>(result.getData().getCountry(), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            log.error("failed to obtain country info for '" + country_id + "' - " + ex.getMessage());
        }
        return new ResponseEntity<Object>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
