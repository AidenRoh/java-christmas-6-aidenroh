package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.exception.IllegalDateException;
import christmas.exception.IllegalMenuException;
import christmas.util.Convertor;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UI Level 예외 테스트")
class UIValidatorTest {

	@Nested
	@DisplayName("UI 날짜 테스트")
	class UILevelBookingDateTest {

		@ParameterizedTest(name = "{0}을 입력 받으면 예외가 발생한다")
		@ValueSource(strings = {"", "a", "-1"})
		void 날짜와_무관한_입력값에_대한_UI_예외테스트(String argument) {
			assertThatThrownBy(() -> UIValidator.validateBookingDate(argument))
					.isInstanceOf(IllegalDateException.class);
		}
	}

	@Nested
	@DisplayName("UI 메뉴 테스트")
	class UIMenuValidatorTest {

		private static Stream<Arguments> invalidMenuParam() {
			return Stream.of(
					arguments("아이스크림-a"),
					arguments("아이스크림-22"),
					arguments("아이스크림-0"),
					arguments("아이스크림-1,아이스크림-1"),
					arguments("아이스크림-1,,아이스크림-1"),
					arguments("아이스크림"),
					arguments("아이스크림-")
			);
		}

		@ParameterizedTest(name = "{0}을 입력 받으면 예외가 발생한다")
		@ValueSource(strings = {"", ",아이스크림-1", "아이스크림-1,"})
		void 메뉴와_무관한_입력값에_대한_UI_예외테스트(String argument) {
			assertThatThrownBy(() -> UIValidator.validateBookingDate(argument))
					.isInstanceOf(IllegalDateException.class);
		}

		@ParameterizedTest(name = "{0}을 입력 받으면 예외가 발생한다")
		@MethodSource("invalidMenuParam")
		void 입력값에_컨버팅_과정에서_발생할_UI_예외테스트(String argument) {
			assertThatThrownBy(() -> Convertor.InputToStringMap(argument))
					.isInstanceOf(IllegalMenuException.class);
		}
	}

}