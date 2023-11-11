package christmas.domain.discount;

import christmas.enums.Discount;
import christmas.enums.Menu;
import java.time.LocalDate;
import java.util.Map;

public interface DiscountPolicy {
	Map<Discount, Integer> discount(Map<Menu, Integer> order, Map<Discount, Integer> discountDetail);

	boolean checkAvailability(LocalDate date);
}
