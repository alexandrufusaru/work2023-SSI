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

public class DeletePriceDialog {
	private Display display;

	public DeletePriceDialog(Display display) {
		super();
		this.display = display;
	}

	public void run() {
		Shell shell = new Shell(display);
		shell.setText("Delete price");

		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("Nume articol: ");

		Text text1 = new Text(shell, SWT.BORDER);
		text1.setLayoutData(new RowData(100, SWT.DEFAULT));

		Label label2 = new Label(shell, SWT.NONE);
		label2.setText("Nume magazin: ");

		Text text2 = new Text(shell, SWT.BORDER);
		text2.setLayoutData(new RowData(100, SWT.DEFAULT));

		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("Submit");

		DatabaseFunctions df = new DatabaseFunctions();
		ok.addSelectionListener(widgetSelectedAdapter(e -> df.removePrice(text1.getText(), text2.getText())));

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