package com.ducatillon.graphqlclient.client;

import com.ducatillon.graphqlclient.data.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.WebGraphQlClient;

import java.io.IOException;

@Configuration
@Slf4j
public class CountryClientGraphQL extends CountryClient {
    @Override
    public CountryDto getCountryDetails(final String countryCode) throws IOException {
        String  httpRequestDocument = """
               query($code: ID!)  {
                                country(code: $code) {
                                    name
                                    continent {
                                      name
                                    }
                                    languages {
                                      name
                                    }
                                    capital
                                    currency
                                    phone
                                }
                            }
                """;
        var  client = HttpGraphQlClient.builder().url(super.getUrl()).build();
        return new CountryDto(client.document(httpRequestDocument)
                .variable("code", countryCode)
                .retrieve("country")
                .toEntity(CountryDto.CountryData.Country.class)
                .block());
    }
}
