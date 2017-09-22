
package rcpproject.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class DynamicPartHandler {
	@Execute
	public void execute(EPartService service) {
		// create part based on part descriptor with the
		// given ID
		MPart part = service.createPart("rcpproject.partdescriptor.dynamic");
		// part service does not allow to define the container
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=390971
		service.showPart(part, PartState.ACTIVATE);
	}
}