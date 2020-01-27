package com.capitanperegrina.boarraceanalyzer.swt.client;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class BoatTrackAnalyzerClient {

	public static void main(String[] args) {
		Display display = new Display();

		/** create the new window */
		Shell shell = new Shell(display);

		/** adding the window title */
		shell.setText("Boat Track Analyzer");

		/** setting up the window size */
		shell.setSize(1024, 768);
		shell.setMaximized(true);

		/** creating a new label widget on the new created shell */
		Label label = new Label(shell, SWT.NONE);

		/** setting up the label text */
		label.setText("Hello World");

		/** to resize the label to the required size, based on the text */
		label.pack();

		shell.addPaintListener(event -> {
			Rectangle rect = shell.getClientArea();
			event.gc.drawOval(0, 0, rect.width - 1, rect.height - 1);
		});

		Rectangle clientArea = shell.getClientArea();

		/** this will open the window and will make it visible */
		shell.open();

		/**
		 * this loop is to maintain the window open until a dispose event is received -
		 * usually a close event
		 */
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
