package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class SWTApplication {

	public static void main(String[] args) {
		SWTApplication app = new SWTApplication();
		app.run();
	}

	private static void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Application");
		shell.setLocation(250, 250);
		shell.setMinimumSize(600, 400);
		// Font font = new Font("Arial", 10, 12);
		// JLabel label = new JLabel("cacao");
		// label.setFont(font);

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("Administration");

		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);

		MenuItem item = new MenuItem(submenu, SWT.PUSH);
		item.addListener(SWT.Selection, e -> {
			new ArcticlesDialog(display).open();
		});
		item.setText("Articles");
		shell.setSize(200, 200);
		shell.open();

		MenuItem item2 = new MenuItem(submenu, SWT.PUSH);
		item2.addListener(SWT.Selection, e -> {
			new StoresDialog(display).run();
		});
		item2.setText("Stores");
		shell.setSize(200, 200);
		shell.open();

		MenuItem item3 = new MenuItem(submenu, SWT.PUSH);
		item3.addListener(SWT.Selection, e -> {
			new PricesDialog(display).run();
			;
		});
		item3.setText("Prices");
		shell.setSize(200, 200);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}