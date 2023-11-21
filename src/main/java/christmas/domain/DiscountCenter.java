package christmas.domain;

import christmas.domain.constant.Discount;
import christmas.domain.discount.DiscountPolicy;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DiscountCenter {

	private final List<DiscountPolicy> discountPolicies;

	public DiscountCenter(DiscountPolicy... discountPolicies) {
		this.discountPolicies = List.of(discountPolicies);
	}

	public DiscountDetail createDiscountDetail(OrderDetail orderMenu, PromotionPeriod reservationDate) {
		Map<Discount, Integer> discountDetail = new EnumMap<>(Discount.class);
		List<DiscountPolicy> viablePolicies = retainViablePolicies(reservationDate);

		if (viablePolicies.isEmpty() || (orderMenu.calculateTotalOrderAmount() < 10000)) {
			return new DiscountDetail(discountDetail);
		}
		viablePolicies.forEach(discountPolicy -> discountPolicy.discount(orderMenu.getOrderDetail(), discountDetail));
		return new DiscountDetail(discountDetail);
	}

	private List<DiscountPolicy> retainViablePolicies(PromotionPeriod reservation) {
		LocalDate date = reservation.getDate();
		List<DiscountPolicy> viablePolicies = this.discountPolicies.stream()
				.filter(policy -> policy.checkAvailability(date))
				.toList();
		return viablePolicies;
	}
}
