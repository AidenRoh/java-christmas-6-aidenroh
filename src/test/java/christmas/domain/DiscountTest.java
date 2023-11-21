package christmas.domain;

import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.constant.Discount;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[discount] 할인 이벤트 테스트")
class DiscountTest {
	/**
	 * 테스트 목록 1. 10000 원 이하의 값이 나올시 적용할 수 있는 할인 정책이 없다 2. 날짜에 맞는 할인 정책이 들어온다
	 */
	private static Stream<Arguments> discountPoliciesParam() {
		return Stream.of(
				arguments(
						named("주말", PromotionPeriod.valueOf(1)),
						named("만 원 이하의 메뉴", OrderDetail.of(Map.of("양송이수프", 1))),
						named("0개의 할인", Map.of())
				),
				arguments(
						named("주말", PromotionPeriod.valueOf(1)),
						named("바비큐립", OrderDetail.of(Map.of("바비큐립", 1))),
						named("주말, 크리스마스 할인",
								Map.of(Discount.WEEKEND_DISCOUNT, 2023,
										Discount.CHRISTMAS_DISCOUNT, 1000))
				),
				arguments(
						named("평일/스페셜데이", PromotionPeriod.valueOf(3)),
						named("초코케이크", OrderDetail.of(Map.of("초코케이크", 2))),
						named("평일, 스페셜, 크리스마스 할인",
								Map.of(Discount.WEEKDAY_DISCOUNT, 4046,
										Discount.SPECIAL_DISCOUNT, 1000,
										Discount.CHRISTMAS_DISCOUNT, 1200))
				),
				arguments(
						named("평일", PromotionPeriod.valueOf(26)),
						named("초코케이크", OrderDetail.of(Map.of("초코케이크", 3))),
						named("평일 할인", Map.of(Discount.WEEKDAY_DISCOUNT, 6069))
				)
		);
	}

	@ParameterizedTest(name = "{0}에 {1} 주문 시 {2}이 적용")
	@MethodSource("discountPoliciesParam")
	void 만_원_이상으로_주문_시_날짜에_맞는_할인정책을_적용한다(
			PromotionPeriod date,
			OrderDetail orderMenu,
			Map<Discount, Integer> expectDiscount) {
		//given
		DiscountCenter discountCenter = new DiscountCenter(
				new WeekdayDiscount(),
				new WeekendDiscount(),
				new SpecialDiscount(),
				new ChristmasDiscount());
		//when
		DiscountDetail discountDetail = discountCenter.createDiscountDetail(orderMenu, date);
		//then
		Assertions.assertThat(discountDetail.discountDetail()).isEqualTo(expectDiscount);
	}
}