package christmas.domain.discount;

import static christmas.domain.constant.Discount.SPECIAL_DISCOUNT;

import christmas.domain.constant.Discount;
import christmas.domain.constant.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public final class SpecialDiscount implements DiscountPolicy {

	private static final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;
	private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);


	@Override
	public Map<Discount, Integer> discount(Map<Menu, Integer> order,
										   Map<Discount, Integer> discountDetail) {
		int discountAmount = SPECIAL_DISCOUNT.getDiscountAmount();
		discountDetail.put(SPECIAL_DISCOUNT, discountAmount);
		return discountDetail;
	}

	@Override
	public boolean checkAvailability(LocalDate date) {
		return (date.getDayOfWeek().equals(SUNDAY) || date.isEqual(CHRISTMAS));
	}
}
