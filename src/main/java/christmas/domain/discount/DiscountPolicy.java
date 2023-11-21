package christmas.domain.discount;

import christmas.domain.constant.Discount;
import christmas.domain.constant.Menu;
import java.time.LocalDate;
import java.util.Map;

public sealed interface DiscountPolicy permits ChristmasDiscount, SpecialDiscount, WeekendDiscount, WeekdayDiscount {

	Map<Discount, Integer> discount(Map<Menu, Integer> order, Map<Discount, Integer> discountDetail);

	boolean checkAvailability(LocalDate date);
}
