
package rcpproject.handlers;

import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;

public class CloseWindowAddon {
	/**
	 * Trim window id.
	 */
	private static final String WINDOW_ID = "org.eclipse.e4.window.main";
	private static final String EXIT_CMD = "org.eclipse.ui.file.exit";

	@Inject
	@Optional
	public void applicationStarted(
			@EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) final MApplication pApplication,
			final EModelService pModelService, final IApplicationContext pApplicationContext,
			final ECommandService pCommandService, final EHandlerService pHandlerService, Display display) {
		// TODO Modify the UIEvents.UILifeCycle.APP_STARTUP_COMPLETE EventTopic
		// to a certain event you want to listen to.
		final MWindow lWindow = (MWindow) pModelService.find(WINDOW_ID, pApplication);
		lWindow.getContext().set(IWindowCloseHandler.class, new IWindowCloseHandler() {

			/**
			 * Closes the application
			 */
			@Override
			public boolean close(final MWindow pWindow) {
				final boolean lConfirmed = false;
				final Command lCmd = pCommandService.getCommand(EXIT_CMD);
				final ParameterizedCommand lParameterizedCmd = new ParameterizedCommand(lCmd, null);
				if (pHandlerService.canExecute(lParameterizedCmd)) {
					pHandlerService.executeHandler(lParameterizedCmd);
				}

				return lConfirmed;
			}
		});

	}

	private void setLocation(MApplication pApplication, Display display) {
		MTrimmedWindow window = (MTrimmedWindow) pApplication.getChildren().get(0);
		Monitor monitor = display.getPrimaryMonitor();
		Rectangle monitorRect = monitor.getBounds();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = new Rectangle(window.getX(), window.getY(), window.getWidth(), window.getHeight());
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		window.setX(x);
		window.setY(y);
	}
}
