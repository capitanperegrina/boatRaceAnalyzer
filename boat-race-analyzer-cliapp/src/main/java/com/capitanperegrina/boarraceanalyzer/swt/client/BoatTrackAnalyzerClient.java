package com.capitanperegrina.boarraceanalyzer.swt.client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.capitanperegrina.boarraceanalyzer.swt.component.MenuPrincipal;

public class BoatTrackAnalyzerClient {

	private final Display display;
	private final Shell shell;
	private final MenuPrincipal fileDialog;
	
	private BoatTrackAnalyzerClient() {
		this.display = new Display();
		
		/** create the new window */
		this.shell = new Shell(this.display);
		
		/** adding the window title */
		this.shell.setText("Boat Rave Analyzer");

		/** setting up the window size */
		this.shell.setSize(800, 600);
		this.shell.setMaximized(false);
		
		
		this.fileDialog = new MenuPrincipal(this.display, this.shell);
		
		/** this will open the window and will make it visible */
		this.shell.open();
		
		/**
		 * this loop is to maintain the window open until a dispose event is received -
		 * usually a close event
		 */
		while (!this.shell.isDisposed()) {
			if (!this.display.readAndDispatch())
				this.display.sleep();
		}
		this.display.dispose();		
	}
	
	public static void main(String[] args) {
		new BoatTrackAnalyzerClient();
	}
}
