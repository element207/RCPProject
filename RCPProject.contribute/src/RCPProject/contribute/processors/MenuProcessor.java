package RCPProject.contribute.processors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;

import RCPProject.contribute.handlers.ExitHandlerWithCheck;

public class MenuProcessor {
	// the menu is injected based on the parameter
	// defined in the extension point
	@Inject
	@Named("rcpproject.menu.file")
	private MMenu menu;

	@Execute
	public void execute() {
		// starting processor
		// remove the old exit menu entry
		if (menu != null && menu.getChildren() != null) {
			List<MMenuElement> list = new ArrayList<MMenuElement>();
			for (MMenuElement element : menu.getChildren()) {
				System.out.println(element.getLabel());
				// Use ID instead of label as label is later translated
				if (element.getElementId() != null) {
					if (element.getElementId().contains("exit")) {
						list.add(element);
					}
				}
			}
			menu.getChildren().removeAll(list);
		}
		// now add a new menu entry
		MDirectMenuItem menuItem = MMenuFactory.INSTANCE.createDirectMenuItem();
		menuItem.setElementId("RCPProject.contribute.exit");
		menuItem.setLabel("Another Exit");
		menuItem.setContributionURI("bundleclass://" + "RCPProject.contribute/" + ExitHandlerWithCheck.class.getName());
		menu.getChildren().add(menuItem);
	}
}