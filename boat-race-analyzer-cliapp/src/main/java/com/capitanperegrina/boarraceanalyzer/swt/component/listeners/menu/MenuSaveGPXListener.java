package com.capitanperegrina.boarraceanalyzer.swt.component.listeners.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class MenuSaveGPXListener implements SelectionListener {

	private Shell shell;
	
	public MenuSaveGPXListener(Shell shell) {
		this.shell = shell;
	}
	
	@Override
	public void widgetSelected(SelectionEvent event) {
		FileDialog fd = new FileDialog(this.shell, SWT.SAVE);
		fd.setText("Save");
		fd.setFilterPath("C:/");
		String[] filterExt = { "*.gpx" };
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		System.out.println(selected);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
	}

}
