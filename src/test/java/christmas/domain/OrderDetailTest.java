package christmas.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.constant.Menu;
import christmas.exception.IllegalMenuException;
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
@DisplayName("[OrderDetail] 상품 주문 테스트")
class OrderDetailTest {

	private static Stream<Arguments> successParam() {
		return Stream.of(
				arguments(Map.of("양송이수프", 2), Map.of(Menu.MUSHROOM_SOUP, 2)),
				arguments(Map.of("아이스크림", 2), Map.of(Menu.ICE_CREAM, 2)),
				arguments(Map.of("바비큐립", 2), Map.of(Menu.BBQ_RIB, 2))
		);
	}

	private static Stream<Arguments> failParam() {
		return Stream.of(
				arguments(Map.of("제로콜라", 5)),
				arguments(Map.of("양송이수프", 19, "제로콜라", 3)
				)
		);
	}

	@ParameterizedTest(name = "{0}으로 주문이 들어오면 {1}으로 생성")
	@MethodSource("successParam")
	void 정상적인_주문정보_객체를_생성한다(Map<String, Integer> input1, Map<Menu, Integer> expectResult) {
		//given
		Map<String, Integer> testInput = input1;
		//when
		OrderDetail testObject = OrderDetail.of(testInput);
		//then
		Assertions.assertThat(testObject.getOrderDetail()).isEqualTo(expectResult);
	}

	@ParameterizedTest(name = "{0}으로 주문이 들어오면 에외 발생")
	@MethodSource("failParam")
	void 비정상적인_입력값에_대한_도메인_예외테스트(Map<String, Integer> input) {
		//given
		Map<String, Integer> testInput = input;
		//when
		Assertions.assertThatThrownBy(() -> OrderDetail.of(testInput))
				//then
				.isInstanceOf(IllegalMenuException.class);
	}
}