// (c) Meta Platforms, Inc. and affiliates. Confidential and proprietary.

package org.datatransferproject.spi.transfer.extension;

import org.datatransferproject.api.launcher.AbstractExtension;

import java.util.List;

public interface GenericTransferExtensionInterface extends AbstractExtension {
  List<TransferExtension> getGenericTransferExtensions();
}
