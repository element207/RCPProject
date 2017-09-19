package rcpproject.wizards;

import org.eclipse.jface.wizard.Wizard;

public class TestWizard extends Wizard {
	boolean finish = false;
	TestWizardPage1 page1 = new TestWizardPage1("pageName1");
	TestWizardPage2 page2 = new TestWizardPage2("pageName2");

	public TestWizard() {
		// TODO Auto-generated constructor stub
		setWindowTitle("new Wizard");
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		addPage(page1);
		addPage(page2);
	}

	@Override
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() == page2)
			return true;
		// TODO Auto-generated method stub
		return finish;
	}

}
