package com.ducatillon.graphqlclient.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class CountryDto {

  private CountryData data;


  @Getter
  static private class Continent {
    private String name;
  }

  @Getter
  static private class Language {
    private String  name;
  }

  @Getter
  public class CountryData {

    private Country country;

    @Getter
    public class Country {

      private String name;
      private Continent continent;
      private List<Language> languages;
      private String currency;
      private String capital;
      private String phone;
    }
  }
}

