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
package com.tlcsdm.eclipse.rcp.example.headless.ease.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.ease.IScriptEngine;
import org.eclipse.ease.ScriptResult;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

public class RunScriptHandler extends AbstractHandler {

    private static final String SMARTDEMO_PARAM_PATH = "smartdemo.param.path"; //$NON-NLS-1$

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        IScriptEngine scriptEngine = ScriptHelper.createScriptEngine();
        Path scriptFilePath = new Path(event.getParameter(RunScriptHandler.SMARTDEMO_PARAM_PATH));
        Bundle bundle = BundleUtils.getBundle();
        if (scriptEngine != null && bundle != null) {
            URL file = FileLocator.find(bundle, scriptFilePath, null);
            if (file != null) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(file.openStream()))) {
                    // scriptEngine.setTerminateOnIdle(true);
                    ScriptResult result = scriptEngine.execute(in);
                    scriptEngine.schedule();
                    result.get(60, TimeUnit.SECONDS);
                } catch (IOException | InterruptedException | java.util.concurrent.ExecutionException
                        | TimeoutException e) {
                    Display.getDefault().asyncExec(() -> MessageDialog.openError(Display.getDefault().getActiveShell(),
                            "Unable to execute Demostration script", e.getMessage())); //$NON-NLS-1$
                    BundleUtils.logException(e);
                }
            } else {
                MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", //$NON-NLS-1$
                        "Unable to start the demonstration, error is logged."); //$NON-NLS-1$
                BundleUtils.logException(new RuntimeException("Unable to find script file: " //$NON-NLS-1$
                        + scriptFilePath.toPortableString()));

            }
        } else {
            MessageDialog.openError(Display.getDefault().getActiveShell(), "Unable to start the demonstration script", //$NON-NLS-1$
                    "Unable to setup Scripting engine."); //$NON-NLS-1$
            BundleUtils.logException(new RuntimeException("Unable to find script engine")); //$NON-NLS-1$

        }
        return null;
    }

}
