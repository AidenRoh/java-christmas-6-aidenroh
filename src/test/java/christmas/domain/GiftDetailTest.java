package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.Menu;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[GiftDetail] 증정 이벤트 테스트")
class GiftDetailTest {

	@Nested
	@DisplayName("주문 가격이 증정조건에 충족하면")
	class GiftSuccess {

		OrderDetail adequateAmountOrder = OrderDetail.of(Map.of("바비큐립", 3));

		@Test
		void 증정품인_샴페인이_제공된다() {
			//given
			GiftDetail giftDetail = new GiftDetail(adequateAmountOrder, Menu.CHAMPAGNE);
			Map<Menu, Integer> expectResult = Map.of(Menu.CHAMPAGNE, 1);
			//when
			Map<Menu, Integer> testingObject = giftDetail.getGiftGoods();
			//then
			assertThat(testingObject).isEqualTo(expectResult);
		}

		@Test
		void 증정품_가격과_같은_혜택금액을_확인할_수_있다() {
			//given
			GiftDetail giftDetail = new GiftDetail(adequateAmountOrder, Menu.CHAMPAGNE);
			int expectResult = Menu.CHAMPAGNE.getPrice();
			//when
			int testingObject = giftDetail.calculateGiftPrice();
			//then
			assertThat(testingObject).isEqualTo(expectResult);
		}
	}

	@Nested
	@DisplayName("주문 가격이 증정조건에 충족하지 않는다면")
	class GiftFail {

		OrderDetail inadequateAmountOrder = OrderDetail.of(Map.of("바비큐립", 1));

		@Test
		void 증정품이_제공되지_않는다() {
			//given
			GiftDetail giftDetail = new GiftDetail(inadequateAmountOrder, Menu.CHAMPAGNE);
			Map<Menu, Integer> expectResult = Map.of();
			//when
			Map<Menu, Integer> testingObject = giftDetail.getGiftGoods();
			//then
			assertThat(testingObject).isEqualTo(expectResult);
		}

		@Test
		void 증정품에_의한_혜택금액이_0_이다() {
			//given
			GiftDetail giftDetail = new GiftDetail(inadequateAmountOrder, Menu.CHAMPAGNE);
			int expectResult = 0;
			//when
			int testingObject = giftDetail.calculateGiftPrice();
			//then
			assertThat(testingObject).isEqualTo(expectResult);
		}
	}
}