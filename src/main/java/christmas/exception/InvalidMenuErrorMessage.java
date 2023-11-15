package christmas.exception;

import java.util.function.Supplier;

public enum InvalidMenuErrorMessage {

	EMPTY_INPUT("입력값이 없습니다."),
	NON_NUMERIC("주문 수량은 숫자만 가능합니다."),
	BEVERAGE_ONLY_ORDER("음료만 주문할 수 없습니다."),
	MAXIMUM_AMOUNT_OF_ORDER("주문 총수량은 20을 넘을 수 없습니다."),
	NON_EXIST_MENU("메뉴판에 존재하지 않은 메뉴입니다."),
	DUPLICATE_MENU("중복된 메뉴가 있습니다."),
	INVALID_MENU_AMOUNT("메뉴 수량은 1 이자상 20 이하여야 합니다."),
	BLANK_EXIST("두 쉼표(,) 사이에 빈 값이 존재합니다."),
	BAD_DELIMITER_COMMA("메뉴는 쉼표(,)로 시작하거나 끝낼 수 없습니다."),
	BAD_DELIMITER_HYPHEN("메뉴는 쉼표(-)로 시작하거나 끝낼 수 없습니다."),
	INVALID_FORMAT("메뉴 형식(메뉴-수량)에 맞지 않습니다.");

	private final String message;

	InvalidMenuErrorMessage(String message) {
		this.message = message;
	}

	public void findErrorBy(Supplier<Boolean> condition) {
		if (condition.get()) {
			throw new IllegalMenuException(this.message);
		}
	}
}
