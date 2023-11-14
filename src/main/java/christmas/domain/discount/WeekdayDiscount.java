package christmas.domain.discount;

import static christmas.enums.Discount.WEEKDAY_DISCOUNT;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.enums.Discount;
import christmas.enums.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class WeekdayDiscount implements DiscountPolicy {

	private static final List<DayOfWeek> WEEKDAY = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);

	@Override
	public Map<Discount, Integer> discount(Map<Menu, Integer> order,
										   Map<Discount, Integer> discountDetail) {
		int discountAmount = calculateDiscountAmount(order);
		discountDetail.put(WEEKDAY_DISCOUNT, discountAmount);
		return discountDetail;
	}

	@Override
	public boolean checkAvailability(LocalDate date) {
		return WEEKDAY.contains(date.getDayOfWeek());
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
				.equals(WEEKDAY_DISCOUNT.getTargetCourse());
	}

	private int calculateDiscountAmount(Map.Entry<Menu, Integer> userOrder) {
		int menuAmount = userOrder.getValue();
		return menuAmount * WEEKDAY_DISCOUNT.getDiscountAmount();
	}
}
