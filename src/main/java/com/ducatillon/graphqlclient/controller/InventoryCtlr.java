package com.ducatillon.graphqlclient.controller;

import com.ducatillon.graphqlclient.client.CountryClient;
import com.ducatillon.graphqlclient.client.CountryClientGraphQL;
import com.ducatillon.graphqlclient.client.CountryClientWebClient;
import com.ducatillon.graphqlclient.data.CountryDto;
import com.ducatillon.graphqlclient.servce.InventorySvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1")
public class InventoryCtlr {
    @Autowired
    private InventorySvc service;


    @RequestMapping(path = "/country/{country_id}", method = RequestMethod.GET)
    public ResponseEntity<Object>  getCountry(@PathVariable String country_id,
                                              @RequestParam(defaultValue = "web") String client_type) {
        log.info("requesting '" + country_id + "' via " + client_type);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            CountryClient  client = null;
            switch (client_type) {
                case "web": client = new CountryClientWebClient();  break;
                case "graphql": client = new CountryClientGraphQL();  break;
            }
            if (client == null) {
                throw new IllegalArgumentException("invalid client_type: " + client_type);
            }
            CountryDto result = client.getCountryDetails(country_id);
            return new ResponseEntity<Object>(result.getData().getCountry(), HttpStatus.OK);
        }
        catch (IllegalArgumentException ex)
        {
            log.error("failed to obtain country info for '" + country_id + "' - " + ex.getMessage());
            return new ResponseEntity<Object>("{ err: \"bad request\" }", HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex)
        {
            log.error("failed to obtain country info for '" + country_id + "' - " + ex.getMessage());
        }
        return new ResponseEntity<Object>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
