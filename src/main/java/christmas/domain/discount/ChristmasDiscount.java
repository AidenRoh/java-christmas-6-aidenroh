package christmas.domain.discount;

import static christmas.enums.Discount.CHRISTMAS_DISCOUNT;

import christmas.enums.Discount;
import christmas.enums.Menu;
import java.time.LocalDate;
import java.util.Map;

public class ChristmasDiscount implements DiscountPolicy {
	private static final LocalDate EVENT_START_DAY = LocalDate.of(2023, 12, 1);
	private static final LocalDate EVENT_END_DAY = LocalDate.of(2023, 12, 25);
	private static final int EXCLUDE_FIRST_DAY = 1;

	private int discountIncrement = 0;

	@Override
	public Map<Discount, Integer> discount(Map<Menu, Integer> order,
										   Map<Discount, Integer> discountDetail) {
		int discountAmount = CHRISTMAS_DISCOUNT.getDiscountAmount() + discountIncrement;
		discountDetail.put(CHRISTMAS_DISCOUNT, discountAmount);
		return discountDetail;
	}

	@Override
	public boolean checkAvailability(LocalDate date) {
		setDiscountIncrement(date);
		if (date.isEqual(EVENT_START_DAY) || date.isEqual(EVENT_END_DAY)) {
			return true;
		}
		return EVENT_START_DAY.isBefore(date) && date.isAfter(EVENT_END_DAY);
	}

	private void setDiscountIncrement(LocalDate date) {
		this.discountIncrement = (date.getDayOfMonth() - EXCLUDE_FIRST_DAY) * 100;
	}
}
