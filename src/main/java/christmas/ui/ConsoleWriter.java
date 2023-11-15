package christmas.ui;

public sealed class ConsoleWriter permits InputView, OutputView {

	protected void print(final Object data) {
		System.out.print(data);
	}

	protected void println(final Object data) {
		System.out.println(data);
	}

	protected void lineSpacePrintln(final Object data) {
		this.lineSpace();
		this.println(data);
	}

	protected void printf(final String format, final Object... values) {
		System.out.printf(format, values);
	}

	private void lineSpace() {
		this.println(System.lineSeparator());
	}
}
