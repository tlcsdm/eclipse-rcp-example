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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogLabelKeys;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;

import com.tlcsdm.eclipse.rcp.example.rcp.LightingPerspective;
import com.tlcsdm.eclipse.rcp.example.rcp.LightingPlugin;

@SuppressWarnings("restriction")
public class ShowViewDialog extends Dialog implements ISelectionChangedListener, IDoubleClickListener {

    private Button okButton;
    private TreeViewer treeViewer;
    private Tree tree;

    public ShowViewDialog(Shell parent) {
        super(parent);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            openSelectViewpart();
        }
        super.buttonPressed(buttonId);
    }

    /**
     * Notifies that the cancel button of this dialog has been pressed.
     */
    @Override
    protected void cancelPressed() {
        super.cancelPressed();
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        okButton = createButton(parent, IDialogConstants.OK_ID, WorkbenchMessages.ShowView_open_button_label, true);
        okButton.setText("OK");
        Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
                JFaceResources.getString(IDialogLabelKeys.CANCEL_LABEL_KEY), false);
        cancelButton.setText("Cancel");
        updateButtons();
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setFont(parent.getFont());
        getShell().setText("Show View");
        applyDialogFont(composite);
        composite.setLayout(new GridLayout(1, false));

        treeViewer = new TreeViewer(composite, SWT.BORDER);
        tree = treeViewer.getTree();
        GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gd_tree.widthHint = 508;
        gd_tree.heightHint = 255;
        tree.setLayoutData(gd_tree);
        treeViewer.setLabelProvider(new ShowViewLabelProvider());
        treeViewer.setContentProvider(new ShowViewContentProvider());
        treeViewer.setInput(MApplication.getApplications());
        treeViewer.expandAll();
        treeViewer.addSelectionChangedListener(this);
        treeViewer.addDoubleClickListener(this);
        updateButtons();
        // Return results.
        return composite;
    }

    protected void updateButtons() {
        if (okButton != null) {
            if (treeViewer.getStructuredSelection().getFirstElement() == null) {
                okButton.setEnabled(false);
            }
            if (treeViewer.getStructuredSelection().getFirstElement() instanceof MApplication) {
                okButton.setEnabled(false);
            } else {
                okButton.setEnabled(true);
            }
        }
    }

    private boolean openSelectViewpart() {

        if (okButton.isEnabled()) {
            IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

            try {
                workbenchPage.showView(treeViewer.getStructuredSelection().getFirstElement().toString(), null,
                        IWorkbenchPage.VIEW_VISIBLE);

                final IViewPart findView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(LightingPerspective.CONSOLEVIEWID);
                if (findView == null) {
                    return true;
                }
                final IActionBars actionBars = findView.getViewSite().getActionBars();
                final IToolBarManager toolBarManager = actionBars.getToolBarManager();
                final IContributionItem[] items = toolBarManager.getItems();
                for (final IContributionItem iContributionItem : items) {
                    iContributionItem.setVisible(false);
                }
                actionBars.updateActionBars();

                return true;
            } catch (PartInitException e) {
                LightingPlugin.logException(e,
                        "Not found viewPart: " + treeViewer.getStructuredSelection().getFirstElement().toString());
            }
            return false;
        }
        return false;
    }

    @Override
    public void doubleClick(DoubleClickEvent arg0) {
        if (openSelectViewpart()) {
            this.close();
        }
    }

    @Override
    public void selectionChanged(SelectionChangedEvent arg0) {
        updateButtons();
    }

}
