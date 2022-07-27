package com.ducatillon.graphqlclient.client;

import com.ducatillon.graphqlclient.data.CountryDto;
import com.ducatillon.graphqlclient.data.GraphqlRequestBody;
import com.ducatillon.graphqlclient.util.GraphqlSchemaReaderUtil;
import java.io.IOException;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@NoArgsConstructor
@Configuration
@Slf4j
public class CountryClientWebClient extends CountryClient {

  @Override
  public CountryDto getCountryDetails(final String countryCode) throws IOException {

    WebClient webClient = WebClient.builder().build();

    GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

    final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getCountryDetails");
    final String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variables");

    graphQLRequestBody.setQuery(query);
    graphQLRequestBody.setVariables(variables.replace("countryCode", countryCode));

    return webClient.post()
        .uri(super.getUrl())
        .bodyValue(graphQLRequestBody)
        .retrieve()
        .bodyToMono(CountryDto.class)
        .block();
  }
}
