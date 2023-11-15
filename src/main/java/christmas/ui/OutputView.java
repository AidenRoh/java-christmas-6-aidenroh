package christmas.ui;

import christmas.domain.Benefit;
import christmas.domain.GiftDetail;
import christmas.domain.OrderDetail;
import christmas.domain.constant.Menu;
import christmas.exception.ChristmasPromotionException;
import java.util.Map;
import java.util.StringJoiner;

public final class OutputView extends ConsoleWriter {

	private static final String NONE = "없음";

	public void printInitialization() {
		UIMessage.EVENT_PLANNER_INITIALIZATION.accept(this::println);
	}

	public void printAnnounceBenefit(int date) {
		this.print(
				UIMessage.PREVIEW_BENEFIT.apply(text -> text.formatted(date))
		);
	}

	public void print(OrderDetail orderDetail) {
		UIMessage.MENU.accept(this::lineSpacePrintln);
		this.print(menuFormat(orderDetail.getOrderDetail()));
		UIMessage.ORIGINAL_PAYMENT.accept(this::lineSpacePrintln);
		this.print(orderDetail.calculateTotalOrderAmount());
	}

	public void print(GiftDetail giftDetail) {
		UIMessage.GIFT.accept(this::lineSpacePrintln);
		if (giftDetail.checkEmpty()) {
			this.print(NONE);
			return;
		}
		this.print(menuFormat(giftDetail.getGiftGoods()));
	}

	public void print(OrderDetail orderDetail, Benefit benefit) {
		UIMessage.BENEFIT_DETAIL.accept(this::lineSpacePrintln);
		this.print(benefitFormat(benefit.generateBenefitDetails()));

		UIMessage.BENEFIT_AMOUNT.accept(this::lineSpacePrintln);
		this.printf("%,d원", benefit.generateTotalBenefitAmount());

		UIMessage.ACTUAL_PAYMENT.accept(this::lineSpacePrintln);
		this.printf("%,d원",
				benefit.generateFinalPayment(orderDetail.calculateTotalOrderAmount()));

		UIMessage.BADGE_EVENT.accept(this::lineSpacePrintln);
		this.print(benefit.generateBadge().getBadgeName());
	}

	public void print(ChristmasPromotionException error) {
		this.lineSpacePrintln(error);
	}

	private String menuFormat(Map<Menu, Integer> map) {
		String format = "%s %d개";
		StringJoiner joiner = new StringJoiner("\n");

		map.forEach((key, value) -> {
			joiner.add(format.formatted(key.getItem(), value));
		});
		return joiner.toString();
	}

	private String benefitFormat(Map<String, Integer> map) {
		if (map.isEmpty()) {
			return NONE;
		}
		String format = "%s: -%d원";
		StringJoiner joiner = new StringJoiner("\n");

		map.forEach((key, value) -> {
			joiner.add(format.formatted(key, value));
		});
		return joiner.toString();
	}
}
