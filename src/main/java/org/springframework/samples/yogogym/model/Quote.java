package org.springframework.samples.yogogym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

  private String quote;

  public Quote() {
  }

  public String getQuote() {
    return quote;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }


  @Override
  public String toString() {
    return "Quote{" + "quote='" + quote + '}';
  }
}