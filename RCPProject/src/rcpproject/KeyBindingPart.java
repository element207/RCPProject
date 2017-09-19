
package rcpproject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import rcpproject.handlers.ExitHandler;

@SuppressWarnings("restriction")
public class KeyBindingPart {
	@Inject
	EHandlerService handlerService;

	@Inject
	ECommandService commandService;

	public static final String S_CMD_EXIT_ID = "org.eclipse.ui.file.exit";

	@Inject
	public KeyBindingPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Button btnKeybinding = new Button(parent, SWT.NONE);
		btnKeybinding.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Command command = commandService.getCommand(S_CMD_EXIT_ID);
					if (!command.isDefined())
						return;
					ParameterizedCommand exitCommand = commandService.createCommand(S_CMD_EXIT_ID, null);
					handlerService.activateHandler(S_CMD_EXIT_ID, new ExitHandler());
					if (!handlerService.canExecute(exitCommand))
						return;
					handlerService.executeHandler(exitCommand);
				} catch (Exception ex) {
					throw new RuntimeException("command with id " + S_CMD_EXIT_ID + " not found");
				}
			}
		});

		btnKeybinding.setText("KeyBinding");

	}

}