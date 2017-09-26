package rcpproject.lifecycle;

import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;

import rcpproject.dialogs.custom.PasswordDialog;

// for a extended example see
// https://bugs.eclipse.org/382224
public class Manager {
	@Inject
	@Preference(value = "user")
	private String user;

	@Inject
	@Preference(value = "password")
	private String password;

	@PostContextCreate
	void postContextCreate(IApplicationContext appContext, Display display, @Preference IEclipsePreferences prefs) {
		final Shell shell = new Shell(SWT.TOOL | SWT.NO_TRIM);
		PasswordDialog dialog = new PasswordDialog(shell);
		// close the static splash screen
		appContext.applicationRunning();
		// position the shell
		setLocation(display, shell);
		if (user != null) {
			if (password != null) {
				dialog.setUser(user);
				dialog.setPassword(password);
			} else {
				dialog.setUser(user);
			}
		}
		if (dialog.open() != Window.OK) {
			// close the application
			System.exit(-1);
		} else {
			prefs.put("user", dialog.getUser());
			prefs.put("password", dialog.getPassword());
			try {
				prefs.flush();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
	}

	private void setLocation(Display display, Shell shell) {
		Monitor monitor = display.getPrimaryMonitor();
		Rectangle monitorRect = monitor.getBounds();
		Rectangle shellRect = shell.getBounds();
		int x = monitorRect.x + (monitorRect.width - shellRect.width) / 2;
		int y = monitorRect.y + (monitorRect.height - shellRect.height) / 2;
		shell.setLocation(x, y);
	}
}