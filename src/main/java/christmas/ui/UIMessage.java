package christmas.ui;

import java.util.function.Consumer;
import java.util.function.Function;

public enum UIMessage {
	EVENT_PLANNER_INITIALIZATION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
	BOOKING_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
	ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
	PREVIEW_BENEFIT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
	MENU("<주문 메뉴>"),
	ORIGINAL_PAYMENT("<할인 전 총주문 금액>"),
	GIFT("<증정 메뉴>"),
	BENEFIT_DETAIL("<혜택 내역>"),
	BENEFIT_AMOUNT("<총혜택 금액>"),
	ACTUAL_PAYMENT("<할인 후 예상 결제 금액>"),
	BADGE_EVENT("<12월 이벤트 배지>");

	private final String view;

	UIMessage(String view) {
		this.view = view;
	}

	public void accept(Consumer<String> consumer) {
		consumer.accept(view);
	}

	public String apply(Function<String, String> function) {
		return function.apply(view);
	}
}
