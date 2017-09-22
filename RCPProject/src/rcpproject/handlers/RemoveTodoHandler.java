
package rcpproject.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import rcpproject.model.ITodoService;
import rcpproject.model.Todo;

public class RemoveTodoHandler {
	@Execute
	public void execute(ITodoService model, @Optional @Named(IServiceConstants.ACTIVE_SELECTION) Todo todo,
			Shell shell) {
		if (todo != null) {
			model.deleteTodo(todo.getId());
		} else {
			MessageDialog.openInformation(shell, "Deletion not possible", "No todo selected");
		}
	}

	@CanExecute
	public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Todo todo) {
		if (todo != null)
			return true;
		else
			return false;

	}

}