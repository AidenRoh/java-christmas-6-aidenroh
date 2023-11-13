package christmas.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventPeriodTest {
	private static Stream<Arguments> eventDateParam() {
		return Stream.of(
				arguments(1, LocalDate.of(2023, 12, 1))
		);
	}

	@ParameterizedTest(name = "숫자 {0}은 {1}로 반환")
	@MethodSource("eventDateParam")
	void integer_입력값을_받을_때_입력값에_맞는_날짜를_반환합니다(int input, LocalDate expectDate) {
		//given
		EventPeriod testingInput = EventPeriod.valueOf(input);
		//when
		LocalDate testingDate = testingInput.getDate();
		//then
		Assertions.assertThat(testingDate).isEqualTo(expectDate);
	}

	// 이벤트 기간과 맞지 않은 입력값에 대해 오류가 나온다.
	//1. 음수
	//2. 범위에 벗어난 숫자
	//3. 공
}