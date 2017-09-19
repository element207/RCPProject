package rcpproject.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import rcpproject.KeyBindingPart;

public class TestWizardPage2 extends WizardPage {

	protected TestWizardPage2(String pageName) {
		super(pageName);
		setTitle("title");
		setDescription("description");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent, SWT.NONE);
		KeyBindingPart part = new KeyBindingPart();
		part.createControls(container);
		setControl(container);

	}

}
