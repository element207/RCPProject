package rcpproject.service;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

import rcpproject.model.ITodoService;

public class TodoServiceContextFunction extends ContextFunction {
	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		// TODO Auto-generated method stub
		ITodoService todoService = ContextInjectionFactory.make(MyTodoServiceImpl.class, context);
		MApplication app = context.get(MApplication.class);
		IEclipseContext appCtx = app.getContext();
		appCtx.set(ITodoService.class, todoService);

		return todoService;
	}
}
