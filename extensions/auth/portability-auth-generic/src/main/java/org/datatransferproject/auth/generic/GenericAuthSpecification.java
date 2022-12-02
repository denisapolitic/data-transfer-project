// (c) Meta Platforms, Inc. and affiliates. Confidential and proprietary.

package org.datatransferproject.auth.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;

public class GenericAuthSpecification {
  @JsonProperty("serviceName")
  private String serviceName;

  @JsonProperty("authURL")
  private String authURL;

  @JsonProperty("tokenURL")
  private String tokenURL;

  @JsonProperty("exportScopes")
  private String exportScopes;

  @JsonProperty("importScopes")
  private String importScopes;

  public GenericAuthSpecification() {
    super();
  }

  public GenericAuthSpecification(
      @JsonProperty("serviceName") String serviceName,
      @JsonProperty("authURL") String authURL,
      @JsonProperty("tokenURL") String tokenURL,
      @JsonProperty("exportScopes") String exportScopes,
      @JsonProperty("importScopes") String importScopes) {
    this.serviceName = serviceName;
    this.authURL = authURL;
    this.tokenURL = tokenURL;
    this.exportScopes = exportScopes;
    this.importScopes = importScopes;
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public String getAuthURL() {
    return this.authURL;
  }

  public String getTokenURL() {
    return this.tokenURL;
  }

  public Map<String, Set<String>> getExportScopes() {
    // ObjectMapper doesn't work directly with Map so we save the export scopes line as a string first
    Map<String, Set<String>> exportScopes = new HashMap<String, Set<String>>();
    String dataType = this.exportScopes.substring(0, this.exportScopes.indexOf(':'));
    String scopes = this.exportScopes.substring(this.exportScopes.indexOf(':'));
    List<String> scopesList = Arrays.asList(scopes.split(","));
    Set<String> scopesSet = new HashSet<String>(scopesList);
    exportScopes.put(dataType, scopesSet);
    return exportScopes;
  }

  public Map<String, Set<String>> getImportScopes() {
    Map<String, Set<String>> importScopes = new HashMap<String, Set<String>>();
    String dataType = this.importScopes.substring(0, this.importScopes.indexOf(':'));
    String scopes = this.importScopes.substring(this.importScopes.indexOf(':'));
    List<String> scopesList = Arrays.asList(scopes.split(","));
    Set<String> scopesSet = new HashSet<String>(scopesList);
    importScopes.put(dataType, scopesSet);
    return importScopes;
  }
}
