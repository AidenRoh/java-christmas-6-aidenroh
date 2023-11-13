package christmas.domain.discount;

import christmas.domain.DiscountDetail;
import christmas.domain.EventPeriod;
import christmas.domain.OrderDetail;
import christmas.enums.Discount;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DiscountCenter {
	private final List<DiscountPolicy> discountPolicies;

	public DiscountCenter(DiscountPolicy... discountPolicies) {
		this.discountPolicies = List.of(discountPolicies);
	}

	public DiscountDetail createDiscountDetail(OrderDetail orderMenu, EventPeriod reservationDate) {
		Map<Discount, Integer> discountDetail = new EnumMap<>(Discount.class);
		List<DiscountPolicy> viablePolicies = retainViablePolicies(reservationDate);

		if (viablePolicies.isEmpty() || (orderMenu.calculateTotalOrderAmount() <= 10000)) {
			return new DiscountDetail(discountDetail);
		}
		viablePolicies.forEach(discountPolicy -> discountPolicy.discount(orderMenu.getOrderDetail(), discountDetail));
		return new DiscountDetail(discountDetail);
	}

	private List<DiscountPolicy> retainViablePolicies(EventPeriod reservation) {
		LocalDate date = reservation.getDate();
		List<DiscountPolicy> viablePolicies = this.discountPolicies.stream()
				.filter(policy -> policy.checkAvailability(date))
				.toList();
		return viablePolicies;
	}
}
