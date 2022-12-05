/*
 * Copyright 2019 The Data Transfer Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.datatransferproject.datatransfer.generic;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.io.*;
import java.util.List;
import okhttp3.OkHttpClient;
import org.datatransferproject.api.launcher.ExtensionContext;
import org.datatransferproject.api.launcher.Monitor;
import org.datatransferproject.datatransfer.generic.social.GenericPostsImporter;
import org.datatransferproject.spi.cloud.storage.TemporaryPerJobDataStore;
import org.datatransferproject.spi.transfer.extension.TransferExtension;
import org.datatransferproject.spi.transfer.provider.Exporter;
import org.datatransferproject.spi.transfer.provider.Importer;
import org.datatransferproject.datatransfer.generic.photos.GenericPhotosImporter;
import org.datatransferproject.types.common.models.DataVertical;

/* Generic transfer extension */
public class GenericTransferExtension implements TransferExtension {
  private GenericConfigSpecification configSpec = null;
  private File configFile;

  private boolean initialized = false;
  private ImmutableMap<String, Importer> importerMap;

  public GenericTransferExtension() {
  }

  public GenericTransferExtension(File configFile) {
    this.configFile = configFile;
    setConfigParams(configFile);
  }

  public GenericConfigSpecification readConfigFile(File configFile) throws IOException {
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile.getPath());
    return new ObjectMapper(new YAMLFactory())
        .readValue(in, GenericConfigSpecification.class);
  }

  @Override
  public void initialize(ExtensionContext context) {
    Monitor monitor = context.getMonitor();
    if (initialized) {
      monitor.severe(() -> "GenericTransferExtension is already initialized");
      return;
    }

    ObjectMapper mapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    OkHttpClient client = context.getService(OkHttpClient.class);
    TemporaryPerJobDataStore jobStore = context.getService(TemporaryPerJobDataStore.class);

    ImmutableMap.Builder<String, Importer> importerBuilder = ImmutableMap.builder();
    importerBuilder.put(
        "PHOTOS",
        new GenericPhotosImporter(monitor, client, mapper, jobStore, configSpec.getBaseURL()));
    importerBuilder.put(
        "SOCIAL-POSTS", new GenericPostsImporter(monitor, client, mapper, configSpec.getBaseURL()));
    importerMap = importerBuilder.build();
    initialized = true;
  }

  private void setConfigParams(File configFile) {
    try {
      this.configSpec = readConfigFile(configFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    ;
  }

  @Override
  public String getServiceId() {
    return this.configSpec.getServiceID();
  }

  @Override
  public Importer<?, ?> getImporter(DataVertical transferDataType) {
    Preconditions.checkArgument(
        initialized, "The transfer extension is not initialized. Unable to get Importer");
    List<String> supportedDataTypes = configSpec.getSupportedDataTypes();
    Preconditions.checkArgument(
        supportedDataTypes.contains(transferDataType),
        "The transfer extension doesn't support " + transferDataType);
    return importerMap.get(transferDataType);
  }

  @Override
  public Exporter<?, ?> getExporter(DataVertical transferDataType) {
    throw new IllegalArgumentException();
  }
}
