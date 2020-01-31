package com.capitanperegrina.boarraceanalyzer.swt.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.capitanperegrina.boarraceanalyzer.swt.component.listeners.menu.MenuExitSelectionAdapter;
import com.capitanperegrina.boarraceanalyzer.swt.component.listeners.menu.MenuOpenGPXListener;
import com.capitanperegrina.boarraceanalyzer.swt.component.listeners.menu.MenuSaveGPXListener;

public class MenuPrincipal {

	public MenuPrincipal(final Display d, final Shell s) {
		Menu m = new Menu(s, SWT.BAR);

		// create a file menu and add an exit item
		final MenuItem file = new MenuItem(m, SWT.CASCADE);
		file.setText("&File");
		
		final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		
		final MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
		openItem.setText("&Open\tCTRL+O");
		openItem.setAccelerator(SWT.CTRL + 'O');
		
		final MenuItem saveItem = new MenuItem(filemenu, SWT.PUSH);
		saveItem.setText("&Save\tCTRL+S");
		saveItem.setAccelerator(SWT.CTRL + 'S');
		
		final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		
		final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("E&xit");
		
		openItem.addSelectionListener(new MenuOpenGPXListener(s));
		saveItem.addSelectionListener(new MenuSaveGPXListener(s));
		exitItem.addSelectionListener(new MenuExitSelectionAdapter(s));
		
		s.setMenuBar(m);
	}
}
