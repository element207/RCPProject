
package rcpproject.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import rcpproject.dialogs.custom.MyTitleAreaDialog;
import rcpproject.handlers.EnterCredentialsHandler;
import rcpproject.handlers.ExitHandler;
import rcpproject.handlers.WizardHandler;

@SuppressWarnings("restriction")
public class KeyBindingPart {
	@Inject
	EHandlerService handlerService;

	@Inject
	ECommandService commandService;

	public static final String S_CMD_EXIT_ID = "org.eclipse.ui.file.exit";
	public static final String S_CMD_LOGIN_ID = "rcpproject.command.login";
	public static final String S_CMD_WIZARD_ID = "rcpproject.command.wizard";

	@Inject
	public KeyBindingPart() {

	}

	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Button btnKeybinding = new Button(parent, SWT.NONE);
		btnKeybinding.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnKeybinding.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				MyTitleAreaDialog dialog = new MyTitleAreaDialog(parent.getShell());
				dialog.create();
				if (dialog.open() == Window.OK) {
					System.out.println(dialog.getFirstName());
					System.out.println(dialog.getLastName());
				}

				callCMD(S_CMD_EXIT_ID);
			}
		});

		btnKeybinding.setText("KeyBinding");
		new Label(parent, SWT.NONE);

		Button btnLogin = new Button(parent, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callCMD(S_CMD_LOGIN_ID);
			}
		});
		btnLogin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLogin.setText("Login");
		new Label(parent, SWT.NONE);

		Button btnWizard = new Button(parent, SWT.NONE);
		btnWizard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callCMD(S_CMD_WIZARD_ID);
			}
		});
		btnWizard.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnWizard.setText("Wizard");

	}

	private void callCMD(String cmdid) {
		try {
			Command command = commandService.getCommand(cmdid);
			if (!command.isDefined())
				return;
			ParameterizedCommand exitCommand = commandService.createCommand(cmdid, null);
			if (cmdid.equals(S_CMD_EXIT_ID))
				handlerService.activateHandler(cmdid, new ExitHandler());
			else if (cmdid.equals(S_CMD_LOGIN_ID))
				handlerService.activateHandler(cmdid, new EnterCredentialsHandler());
			else if (cmdid.endsWith(S_CMD_WIZARD_ID))
				handlerService.activateHandler(cmdid, new WizardHandler());
			if (!handlerService.canExecute(exitCommand))
				return;
			handlerService.executeHandler(exitCommand);
		} catch (Exception ex) {
			throw new RuntimeException("command with id " + cmdid + " not found");
		}
	}

}