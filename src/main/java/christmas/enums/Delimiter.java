package christmas.enums;

public enum Delimiter {

	DISCOUNT_SUFFIX("할인"),
	ERROR_PREFIX("[ERROR]"),
	GIFT_GOODS("증정 이벤트"),
	COMMA(","),
	HYPHEN("-");

	private final String delimiter;

	Delimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getDelimiter() {
		return this.delimiter;
	}
}
