package christmas.domain.discount;

import static christmas.enums.Discount.WEEKEND_DISCOUNT;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import christmas.enums.Discount;
import christmas.enums.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class WeekendDiscount implements DiscountPolicy {
	private static final List<DayOfWeek> WEEKEND = List.of(FRIDAY, SATURDAY);

	@Override
	public Map<Discount, Integer> discount(Map<Menu, Integer> order,
										   Map<Discount, Integer> discountDetail) {
		int discountAmount = calculateDiscountAmount(order);
		discountDetail.put(WEEKEND_DISCOUNT, discountAmount);
		return discountDetail;
	}

	@Override
	public boolean checkAvailability(LocalDate date) {
		return WEEKEND.contains(date.getDayOfWeek());
	}

	private int calculateDiscountAmount(Map<Menu, Integer> order) {
		return order.entrySet().stream()
				.filter(this::isTargetCourse)
				.mapToInt(this::calculateDiscountAmount)
				.sum();
	}

	private boolean isTargetCourse(Map.Entry<Menu, Integer> userOrder) {
		return userOrder.getKey()
				.getCourseType()
				.equals(WEEKEND_DISCOUNT.getTargetCourse());
	}

	private int calculateDiscountAmount(Map.Entry<Menu, Integer> userOrder) {
		int menuAmount = userOrder.getValue();
		return menuAmount * WEEKEND_DISCOUNT.getDiscountAmount();
	}
}
