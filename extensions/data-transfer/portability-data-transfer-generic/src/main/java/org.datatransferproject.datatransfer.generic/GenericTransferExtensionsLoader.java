// (c) Meta Platforms, Inc. and affiliates. Confidential and proprietary.

package org.datatransferproject.datatransfer.generic;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.datatransferproject.spi.transfer.extension.GenericTransferExtensionInterface;
import org.datatransferproject.api.launcher.ExtensionContext;
import org.datatransferproject.spi.transfer.extension.TransferExtension;

/** Class that iterates through all the transfer extension config files and creates GenericTransferExtension instances */
public class GenericTransferExtensionsLoader implements GenericTransferExtensionInterface {
  private List<TransferExtension> genericTransferExtensions = new ArrayList<TransferExtension>();

  @Override
  public void initialize(ExtensionContext context) {

  }

  @Override
  public List<TransferExtension> getGenericTransferExtensions() {
    // Iterate through config files
    try {
      List<File> configFiles = getResourceFromJar();
      for(File file : configFiles) {
        this.genericTransferExtensions.add(new GenericTransferExtension(file));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this.genericTransferExtensions;
  }

  private List<File> getResourceFromJar() throws IOException {
    String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring("file:".length());
    final Enumeration<JarEntry> entries = new JarFile(path).entries();
    List<File> result = new ArrayList<>();
    while (entries.hasMoreElements()) {
      String entryName = entries.nextElement().getName();
      if (entryName.endsWith("TransferConfig.yaml")) {
        result.add(new File(entryName));
      }
    }
    return result;
  }
}
