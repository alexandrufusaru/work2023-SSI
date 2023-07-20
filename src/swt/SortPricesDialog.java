package swt;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import minitema2.DatabaseFunctions;
import minitema2.Price;

public class SortPricesDialog {
	private Display display;

	public SortPricesDialog(Display display) {
		super();
		this.display = display;
	}

	public void open() {
		Shell shell = new Shell(display);
		shell.setText("Prices");
		shell.setLayout(new GridLayout());
		shell.setMinimumSize(400, 400);

		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 50;
		table.setLayoutData(data);

		String[] titles = { "Name Article", "Name Store", "Value" };
		for (String title : titles) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(title);
		}

		DatabaseFunctions df = new DatabaseFunctions();
		List<Price> prices = df.getSortedPrices();

		for (Price p : prices) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, p.getArticle().getName());
			item.setText(1, Integer.toString(p.getStore().getId()));
			item.setText(2, Integer.toString(p.getPrice()));
		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}

		Button addbtn = new Button(shell, SWT.PUSH);
		addbtn.setText("Add");
		addbtn.addSelectionListener(widgetSelectedAdapter(e -> new AddPriceDialog(display).run()));

		Button sortbtn = new Button(shell, SWT.PUSH);
		sortbtn.setText("Sort");
		sortbtn.addSelectionListener(widgetSelectedAdapter(e -> new SortPricesDialog(display).open()));
		sortbtn.addSelectionListener(widgetSelectedAdapter(e -> shell.close()));

		Button delbtn = new Button(shell, SWT.PUSH);
		delbtn.setText("Delete");
		delbtn.addSelectionListener(widgetSelectedAdapter(e -> new DeletePriceDialog(display)));

		Button refreshbtn = new Button(shell, SWT.PUSH);
		refreshbtn.setText("Refresh");
		refreshbtn.addSelectionListener(widgetSelectedAdapter(e -> new SortPricesDialog(display).open()));
		refreshbtn.addSelectionListener(widgetSelectedAdapter(e -> shell.close()));

		shell.pack();
		shell.open();
		/*
		 * while (!shell.isDisposed()) { if (!display.readAndDispatch())
		 * display.sleep(); } display.dispose();
		 */
	}

}