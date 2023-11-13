package christmas.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BadgeTest {
	private static Stream<Arguments> badgeParam() {
		return Stream.of(
				arguments(5000, "별"),
				arguments(10000, "트리"),
				arguments(20000, "산타"),
				arguments(1000, "없음")
		);
	}

	@ParameterizedTest(name = "총혜택 금액이 {0}일 때, {1} 배지를 반환")
	@MethodSource("badgeParam")
	void 총혜택_금액에_맞는_배지를_없다면_없음을_반환합니다(int totalBenefitAmount, String badge) {
		//given
		int benefitAmount = totalBenefitAmount;
		//when
		Badge testingObject = Badge.getAvailableBadge(benefitAmount);
		//then
		Assertions.assertThat(testingObject.getBadgeName()).isEqualTo(badge);
	}
}