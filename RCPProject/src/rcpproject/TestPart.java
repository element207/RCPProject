package rcpproject;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

import com.xyz.article.wizards.HolidayWizard;

public class TestPart {

	public TestPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
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
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the coolbar manager.
	 * 
	 * @return the coolbar manager
	 */
	protected CoolBarManager createCoolBarManager(int style) {
		CoolBarManager coolBarManager = new CoolBarManager(style);
		return coolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */

	/**
	 * Return the initial size of the window.
	 */
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

	private void showWizard() {
		HolidayWizard wizard = new HolidayWizard();
		WizardDialog dialog = new WizardDialog(null, wizard);
		dialog.create();
		dialog.open();
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}

}
