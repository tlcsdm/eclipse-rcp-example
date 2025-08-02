/*
 * Copyright (c) 2025 unknowIfGuestInDream
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of unknowIfGuestInDream, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL UNKNOWIFGUESTINDREAM BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.tlcsdm.eclipse.rcp.example.rcp;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

public class LightingPlugin {

    public static final String PLUGIN_ID = "com.tlcsdm.eclipse.rcp.example.rcp";

    public static URL getEntry(String path) {
        return getEntry(PLUGIN_ID, path);
    }

    public static void logException(Exception e) {
        logException(PLUGIN_ID, e);
    }

    public static URL getEntry(String plguinId, String path) {
        Bundle bundle = Platform.getBundle(plguinId);
        return bundle.getEntry(path);
    }

    public static void logException(String pluginId, Throwable e) {
        ILog log = Platform.getLog(Platform.getBundle(pluginId));
        if (log != null) {
            log.log(new Status(Status.ERROR, pluginId, e.getMessage(), e));
        }
    }

    public static void logException(Exception e, String reason) {
        Platform.getLog(Platform.getBundle(PLUGIN_ID)).log(new Status(Status.ERROR, PLUGIN_ID, reason, e));
    }

    public static String getBundlePath(String subpath) {

        String fullPath = null;
        Bundle bundle = Platform.getBundle(PLUGIN_ID);

        try {
            if (bundle != null) {
                URL url = bundle.getEntry(subpath);
                if (url != null) {
                    URL locatedURL = null;
                    locatedURL = FileLocator.toFileURL(url);
                    fullPath = new Path(locatedURL.getPath()).toOSString();
                }
            }
        } catch (IOException e) {
            logException(e);
        }

        return fullPath;
    }

    public static IPath getPlatformPath() {
        URL platformInstallationURL = Platform.getInstallLocation().getURL();
        String platformInstallationPath = platformInstallationURL.getPath();
        IPath newInstallationPath = new Path(platformInstallationPath);
        return newInstallationPath;
    }
}
