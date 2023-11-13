package christmas.enums;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MenuTest {
	@Test
	void 메뉴에_없는_음식을_주문할_시_예외가_발생한다() {
		//given
		String nonMenuValue = "감자수프";
		//when
		assertThatThrownBy(() -> Menu.findMenu(nonMenuValue))
				//then
				.isInstanceOf(IllegalArgumentException.class);
	}
}