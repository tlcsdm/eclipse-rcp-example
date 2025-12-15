package com.tlcsdm.eclipse.rcp.example.intro.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroManager;

public class ShowWelcomeHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			IIntroManager introManager = PlatformUI.getWorkbench().getIntroManager();
			try {
				if (!introManager.hasIntro()) {
					return null;
				}
				introManager.showIntro(window, false);
			} catch (Exception e) {
				throw new ExecutionException("Failed to open welcome intro", e);
			}
		}
		return null;
	}
}
