package swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import minitema2.DatabaseFunctions;
import minitema2.Price;

public class PricesDialog {
	private Display display;

	public PricesDialog(Display display) {
		super();
		this.display = display;
	}

	public void run() {
		Shell shell = new Shell(display);
		shell.setText("Prices");
		shell.setLayout(new GridLayout());

		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);

		String[] titles = { "Article ID", "Store ID", "Value" };
		for (String title : titles) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(title);
		}

		DatabaseFunctions df = new DatabaseFunctions();
		List<Price> prices = df.getAllPrices();
		for (Price p : prices) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, p.getArticle().getName());
			item.setText(1, Integer.toString(p.getStore().getId()));
			item.setText(2, Integer.toString(p.getPrice()));
		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}