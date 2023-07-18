package swt;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import minitema2.DatabaseFunctions;

public class AddDialog {
	private Display display;
	private String mesaj;
	private String function;

	public AddDialog(Display display, String mesaj) {
		super();
		this.display = display;
		this.mesaj = mesaj;
	}

	public void run() {
		Shell shell = new Shell(display);
		shell.setText("Add " + mesaj);

		Label label = new Label(shell, SWT.NONE);
		label.setText("Nume " + mesaj + ": ");

		Text text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new RowData(100, SWT.DEFAULT));

		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("Submit");

		DatabaseFunctions df = new DatabaseFunctions();
		switch (mesaj) {
		case "article":
			ok.addSelectionListener(widgetSelectedAdapter(e -> df.saveArticle(text.getText())));
			break;
		case "store":
			ok.addSelectionListener(widgetSelectedAdapter(e -> df.saveStore(text.getText())));
		}

		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setText("Cancel");
		cancel.addSelectionListener(widgetSelectedAdapter(e -> shell.close()));
		shell.setDefaultButton(cancel);

		shell.setLayout(new RowLayout());
		shell.pack();
		shell.open();

		/*
		 * while (!shell.isDisposed()) { if (!display.readAndDispatch())
		 * display.sleep(); } display.dispose();
		 */
	}
}