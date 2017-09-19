
package rcpproject.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import rcpproject.wizards.TestWizard;

public class WizardHandler {
	@Execute
	public void execute(Shell shell) {
		WizardDialog dialog = new WizardDialog(shell, new TestWizard());
		if (dialog.open() == WizardDialog.OK) {
			System.out.println("Wizard completed.");
		}
	}

}