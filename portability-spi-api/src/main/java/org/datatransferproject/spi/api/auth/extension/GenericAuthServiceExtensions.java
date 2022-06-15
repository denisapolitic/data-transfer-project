package org.datatransferproject.spi.api.auth.extension;

import org.datatransferproject.api.launcher.AbstractExtension;

import java.util.List;

public interface GenericAuthServiceExtensions extends AbstractExtension {
    List<AuthServiceExtension> getGenericTransferExtensions();
}
