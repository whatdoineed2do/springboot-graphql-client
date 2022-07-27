package com.ducatillon.graphqlclient.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class CountryDto {

  private CountryData data;

  public CountryDto(CountryData.Country data_)
  {
    data = new CountryData(data_);
  }

  @Getter
  static private class Continent {
    private String name;
  }

  @Getter
  static private class Language {
    private String  name;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  public static class CountryData {

    @Setter
    private Country country;

    @NoArgsConstructor
    @Getter
    public static class Country {

      private String name;
      private Continent continent;
      private List<Language> languages;
      private String currency;
      private String capital;
      private String phone;
    }
  }
}

