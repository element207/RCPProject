package com.airbus.pf.at.wizard;

import java.util.Locale;

import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xyz.article.wizards.HolidayWizard;

public class PFWizard extends ApplicationWindow {

	/**
	 * Create the application window,
	 */
	public PFWizard() {
		super(null);
		createActions();
		addCoolBar(SWT.FLAT);
		addMenuBar();
		addStatusLine();

	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		Label lblNewLabel = new Label(container, SWT.WRAP);
		lblNewLabel.setBounds(65, 73, 475, 32);
		lblNewLabel.setText(
				"this is a first wizard this is a first wizard this is a first wizard this is a first wizard this is a first wizard this is a first wizard this is a first wizard this is a first wizard ");

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showWizard();
			}

		});
		btnNewButton.setBounds(595, 77, 122, 25);
		btnNewButton.setText("Strat a Wizard");

		Label lblThisIsA = new Label(container, SWT.WRAP);
		lblThisIsA.setText(
				"this is a first wizard this is a first wizard this is a first wizard this is a first wizard this is a first wizard this is a first wizard ");
		lblThisIsA.setBounds(65, 164, 475, 32);

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showWizard();
			}
		});
		button.setText("Strat a Wizard");
		button.setBounds(595, 168, 122, 25);

		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showWizard();
			}
		});
		button_1.setText("Strat a Wizard");
		button_1.setBounds(595, 271, 122, 25);

		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showWizard();
			}
		});
		button_2.setText("Strat a Wizard");
		button_2.setBounds(595, 382, 122, 25);

		DateTime dateTime = new DateTime(container, SWT.BORDER);
		dateTime.setBounds(65, 220, 80, 24);
		Locale.setDefault(Locale.CHINA);

		CDateTime cdt = new CDateTime(container, CDT.BORDER | CDT.SIMPLE);
		cdt.setPattern("yyyy/MM/dd");
		cdt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		cdt.setBounds(65, 245, 333, 219);

		// container.setBackgroundImage(SWTResourceManager.getImage(PFWizard.class,
		// "/com/airbus/pf/at/resources/images/splash.bmp"));

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the coolbar manager.
	 * 
	 * @return the coolbar manager
	 */
	@Override
	protected CoolBarManager createCoolBarManager(int style) {
		CoolBarManager coolBarManager = new CoolBarManager(style);
		return coolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			PFWizard window = new PFWizard();

			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Pixel Factory Wizard");
		newShell.setImage(SWTResourceManager.getImage(PFWizard.class, "/com/airbus/pf/at/wizard/wizard.png"));
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

	private void showWizard() {
		HolidayWizard wizard = new HolidayWizard();
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		dialog.open();
	}
}
