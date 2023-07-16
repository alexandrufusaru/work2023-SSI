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

import minitema2.Article;
import minitema2.DatabaseFunctions;

public class ArcticlesDialog {
	private Display display;

	public ArcticlesDialog(Display display) {
		super();
		this.display = display;
	}

	public void open() {
		Shell shell = new Shell(display);
		shell.setText("Articles");
		shell.setLayout(new GridLayout());
		shell.setMinimumSize(400, 400);

		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 50;
		table.setLayoutData(data);

		String[] titles = { "Name" };
		for (String title : titles) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(title);
		}

		DatabaseFunctions df = new DatabaseFunctions();
		List<Article> articles = df.getAllArticles();

		for (Article a : articles) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, a.getName());
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