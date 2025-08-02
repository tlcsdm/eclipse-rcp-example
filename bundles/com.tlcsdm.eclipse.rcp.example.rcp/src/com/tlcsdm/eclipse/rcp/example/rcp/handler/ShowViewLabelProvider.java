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
package com.tlcsdm.eclipse.rcp.example.rcp.handler;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.tlcsdm.eclipse.rcp.example.rcp.LightingPerspective;

public class ShowViewLabelProvider extends LabelProvider implements IColorProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof MApplication) {
            MApplication application = (MApplication) element;
            return application.getFolderName();
        }
        if (element instanceof String) {
            String node = (String) element;
            if (LightingPerspective.CONSOLEVIEWID.equals(node)) {
                return "Console View";
            }

            if (LightingPerspective.DALI_CONFIGURATION_VIEWId.equals(node)) {
                return "Configuration View";
            }
        }
        return super.getText(element);
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof MApplication) {
            return new Image(null, getClass().getResourceAsStream("/icons/folder_rep.png"));
        }
        return null;
    }

    @Override
    public Color getBackground(Object arg0) {
        return null;
    }

    @Override
    public Color getForeground(Object arg0) {
        if (arg0 instanceof String) {
            String node = (String) arg0;

            IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            if (workbenchPage.findView(node) != null) {
                return new Color(new RGB(143, 153, 159));
            }

        }
        return null;
    }

}
