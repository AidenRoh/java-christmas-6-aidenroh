package christmas.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.constant.Menu;
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

	/**
	 * 1. 음료만 주문한 경우 예외를 터트린다 2. 메뉴판에 없는 메뉴인 경우 예외를 터트린다 3. 정상적으로 주문했을 때, 주어진 자료형과 같은지 4. 정상적으로 주문했을 때, 총가격이 맞는지 5. 주문
	 * 수가 20개 이상인 경우
	 */
	private static Stream<Arguments> successParam() {
		return Stream.of(
				arguments(Map.of("양송이수프", 2), Map.of(Menu.MUSHROOM_SOUP, 2)),
				arguments(Map.of("아이스크림", 2), Map.of(Menu.ICE_CREAM, 2)),
				arguments(Map.of("바비큐립", 2), Map.of(Menu.BBQ_RIB, 2))
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
}