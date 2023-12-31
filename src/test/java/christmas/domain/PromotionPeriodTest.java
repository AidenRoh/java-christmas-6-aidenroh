package christmas.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.exception.IllegalDateException;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[EventPeriod] 이벤트 날짜 테스트")
class PromotionPeriodTest {
	private static Stream<Arguments> eventDateParam() {
		return Stream.of(
				arguments(1, LocalDate.of(2023, 12, 1))
		);
	}

	@ParameterizedTest(name = "숫자 {0}은 {1}로 반환")
	@MethodSource("eventDateParam")
	void integer_입력값을_받을_때_입력값에_맞는_날짜를_반환합니다(int input, LocalDate expectDate) {
		//given
		PromotionPeriod testingInput = PromotionPeriod.valueOf(input);
		//when
		LocalDate testingDate = testingInput.getDate();
		//then
		Assertions.assertThat(testingDate).isEqualTo(expectDate);
	}

	@Test
	void 잘못된_입력값에_대한_도메인_예외테스트() {
		//given
		int testingInt = 35;
		//when
		Assertions.assertThatThrownBy(() -> PromotionPeriod.valueOf(testingInt))
				//then
				.isInstanceOf(IllegalDateException.class);
	}
}