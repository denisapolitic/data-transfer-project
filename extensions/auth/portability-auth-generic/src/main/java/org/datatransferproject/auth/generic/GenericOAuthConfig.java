package org.datatransferproject.auth.generic;

import java.util.Map;
import java.util.Set;
import org.datatransferproject.auth.OAuth2Config;
import org.datatransferproject.types.common.models.DataVertical;

/**
 * Class that provides generic information for OAuth2
 */
public class GenericOAuthConfig implements OAuth2Config {

  private GenericAuthSpecification authSpecification;

  public GenericOAuthConfig(GenericAuthSpecification authSpecification) {
    this.authSpecification = authSpecification;
  }

  @Override
  public String getServiceName() {
    return authSpecification.getServiceName();
  }

  @Override
  public String getAuthUrl() {
    return authSpecification.getAuthURL();
  }

  @Override
  public String getTokenUrl() {
    return authSpecification.getTokenURL();
  }

  @Override
  public Map<DataVertical, Set<String>> getExportScopes() {
    return authSpecification.getExportScopes();
  }

  @Override
  public Map<DataVertical, Set<String>> getImportScopes() {
    return authSpecification.getImportScopes();
  }
}
