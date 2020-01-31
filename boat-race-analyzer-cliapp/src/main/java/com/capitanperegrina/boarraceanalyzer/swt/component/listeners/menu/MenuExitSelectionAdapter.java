package com.capitanperegrina.boarraceanalyzer.swt.component.listeners.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MenuExitSelectionAdapter extends SelectionAdapter {
	
	private Shell shell;
	
	public MenuExitSelectionAdapter(Shell shell) {
		this.shell = shell;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		MessageBox messageBox = new MessageBox(this.shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messageBox.setMessage("Do you really want to exit?");
		messageBox.setText("Exiting Application");
		int response = messageBox.open();
		if (response == SWT.YES)
			System.exit(0);
	}
}
