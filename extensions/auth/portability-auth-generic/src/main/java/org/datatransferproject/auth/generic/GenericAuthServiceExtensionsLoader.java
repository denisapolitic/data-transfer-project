// (c) Meta Platforms, Inc. and affiliates. Confidential and proprietary.

package org.datatransferproject.auth.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.datatransferproject.api.launcher.ExtensionContext;
import org.datatransferproject.spi.api.auth.extension.AuthServiceExtension;
import org.datatransferproject.spi.api.auth.extension.GenericAuthServiceExtensions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/** This class iterates through all the auth config files and build GenericAuthServiceExtension instances */
public class GenericAuthServiceExtensionsLoader implements GenericAuthServiceExtensions {
    private List<AuthServiceExtension> genericAuthServiceExtensions = new ArrayList<AuthServiceExtension>();;

    @Override
    public void initialize(ExtensionContext context) {
        // Iterate through auth config files
        try {
            List<File> configFiles = getResourceFromJar();
            for(File file : configFiles) {
                GenericOAuthConfig genericOAuthConfig = new GenericOAuthConfig(readConfigFile(file));
                genericAuthServiceExtensions.add(new GenericAuthServiceExtension(genericOAuthConfig));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AuthServiceExtension> getGenericTransferExtensions() {
        return genericAuthServiceExtensions;
    }

    public GenericAuthSpecification readConfigFile(File configFile) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile.getPath());
        return new ObjectMapper(new YAMLFactory())
                .readValue(in, GenericAuthSpecification.class);
    }

    private List<File> getResourceFromJar() throws IOException {
        // Iterates through all the files and returns a list of auth config files
        // Since this will be used from another package the files will be saved in a jar and loaded from there
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring("file:".length());
        final Enumeration<JarEntry> entries = new JarFile(path).entries();
        List<File> result = new ArrayList<>();
        while (entries.hasMoreElements()) {
            String entryName = entries.nextElement().getName();
            if (entryName.endsWith("AuthConfig.yaml")) {
                result.add(new File(entryName));
            }
        }
        return result;
    }
}
