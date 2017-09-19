package rcpproject.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import rcpproject.TestPart;

public class TestWizardPage1 extends WizardPage {

	protected TestWizardPage1(String pageName) {
		super(pageName);
		setTitle("title");
		setDescription("description");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent, SWT.NONE);
		TestPart part = new TestPart();
		part.createControls(container);
		setControl(container);

	}

}
