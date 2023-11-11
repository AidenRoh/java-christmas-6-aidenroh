package christmas.enums;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
	@DisplayName("메뉴에 없는 음식을 주문할 시 예외가 발생한다.")
	@Test
	void findMenu() {
		//given
		String nonMenuValue = "감자수프";
		//when
		assertThatThrownBy(() -> Menu.findMenu(nonMenuValue))
				//then
				.isInstanceOf(IllegalArgumentException.class);
	}
}