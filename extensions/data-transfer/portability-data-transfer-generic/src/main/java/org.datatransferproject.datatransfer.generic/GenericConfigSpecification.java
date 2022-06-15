// (c) Meta Platforms, Inc. and affiliates. Confidential and proprietary.

package org.datatransferproject.datatransfer.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GenericConfigSpecification {
  @JsonProperty("serviceID")
  private String serviceID;

  @JsonProperty("baseURL")
  private String baseURL;

  @JsonProperty(value = "supportedDataTypes", required = true)
  private List<String> supportedDataTypes;

  public GenericConfigSpecification() {
    super();
  }

  public GenericConfigSpecification(
      @JsonProperty("serviceID") String serviceID,
      @JsonProperty("baseURL") String baseURL,
      @JsonProperty("supportedDataTypes") List<String> supportedDataTypes) {
    this.serviceID = serviceID;
    this.baseURL = baseURL;
    this.supportedDataTypes = supportedDataTypes;
  }

  public String getServiceID() {
    return this.serviceID;
  }

  public String getBaseURL() {
    return this.baseURL;
  }
  public List<String> getSupportedDataTypes() {
    return this.supportedDataTypes;
  }
}
