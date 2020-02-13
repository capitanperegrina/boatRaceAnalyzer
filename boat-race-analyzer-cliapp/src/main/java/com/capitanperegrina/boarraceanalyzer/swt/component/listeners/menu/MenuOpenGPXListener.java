package com.capitanperegrina.boarraceanalyzer.swt.component.listeners.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class MenuOpenGPXListener implements SelectionListener {

	private Shell shell;
	
	public MenuOpenGPXListener(Shell shell) {
		this.shell = shell;
	}
	
	@Override
	public void widgetSelected(SelectionEvent event) {
		FileDialog fd = new FileDialog(this.shell, SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("C:/");
		String[] filterExt = { "*.gpx" };
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		// GPX gpx = leer archivo GPX.
		// System.out.println(gpx.toString());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
	}
}
