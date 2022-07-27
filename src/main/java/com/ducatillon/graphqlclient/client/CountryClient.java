package com.ducatillon.graphqlclient.client;

import com.ducatillon.graphqlclient.data.CountryDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public abstract class CountryClient {
    abstract public CountryDto getCountryDetails(final String countryCode) throws IOException;

    @Value("${app.graphql.uri}")
    @Getter
    private String url = "https://countries.trevorblades.com/";
}
