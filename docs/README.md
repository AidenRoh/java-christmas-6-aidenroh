![wooteco](https://github.com/AidenRoh/wooteco-precourse/assets/124841119/08c069a4-a7b0-41c1-884f-dfc6af17d373)

# 프리코스 4주차 미션: 크리스마스 프로모션

![Generic badge](https://img.shields.io/badge/precourse-week3-blue.svg)
![Generic badge](https://img.shields.io/badge/unitTest-9-green.svg)

> 우아한테크코스 6기 4주차 미션, 크리스마스 프로모션을 구현한 저장소입니다.

<br>

---

## 목차

- [패키지 구조](#패키지-구조)
- [서비스 흐름](#서비스-흐름)
- [기능 목록](#기능-목록)

<br>

---

## 패키지 구조

<br>

```

📦 christmas
├─ controller
│  └─————————————— ChristmasEventController
├─ domain
│  ├─ constant
│  │  ├─————————————— Badge(Enum)
│  │  ├─————————————— Discount(Enum)
│  │  └─————————————— Menu(Enum)
│  ├─ discoun
│  │  ├─————————————— ChristmasDiscoun
│  │  ├─————————————— SpecialDiscount
│  │  ├─————————————— WeekdayDiscount
│  │  ├─————————————— WeekendDiscount
│  │  └─————————————— DiscountPolicy(Interface)
│  ├─————————————— Benefit
│  ├─————————————— DiscountCenter
│  ├─————————————— DiscountDetail
│  ├─————————————— EventPeriod
│  ├─————————————— GiftDetail
│  └─————————————— OrderDetail
├─ exception
│  ├─————————————— ChristmasEventException(Abstract)
│  ├─————————————— InvalidDateException
│  ├─————————————— InvalidMenuException
│  ├─————————————— InvalidBookingErrorMessage(Enum)
│  └─————————————— InvalidMenuErrorMessage(Enum)
├─ ui
│  ├─————————————— ConsoleWriter
│  ├─————————————— InputView
│  ├─————————————— OutputView
│  ├─————————————— ValidateInputReceiver
│  └─————————————— UIMessage(Enum)
├─ util
│  └─————————————— Convertor
└─ validation
   ├─————————————— DomainValidator
   └─————————————— UIValidator

```

## 서비스 흐름

<br>

---

## 기능 목록

> 입력 순서로 기능 목록이 나열되어 있습니다. [서비스 흐름](#서비스-흐름)
>
> 해당 프로젝트는 이벤트 정책 변화에 유연한 어플리케이션을 목표로 하고 있습니다.

### 1️⃣ 예약 날짜 설정

- 필요한 기능

    - [x] **[이벤트 기간 설정]** : 프로모션 시작일과 기간을 설정할 수 있다.
    - [x] **[날짜 정보]** : 설정된 기간의 날짜 정보(날짜, 요일)를 확인할 수 있다.
    - [x] **[주어진 설정 요구사항]** : 시작일 2023년 12월 1일, 31일동안 프로모션을 진행한다.

<br>

- 해당 기능과 관련된 검증 목록

    - **[입력값 없음]** : 입력하지 않은 경우
    - **[숫자가 아닌 입력값]** : 입력값이 숫자가 아닌 경우
    - **[음수]** : 입력값이 음수인 경우
    - **[프로모션 기간과 맞지 않은 날짜]** : 입력한 숫자가 프로모션 기간에 벗어난 경우

<br>

##### ⚠️ 예외 처리 ⚠️

- [x] ``IllegalArgumentException``를 상속한 **[ChristmasPromotionException]** 을 활용한다.
- [x] 예외 발생 시, ``ChristmasPromotionException`` 를 상속한 **[InvalidDateException]** 을 활용한다.
- [x] **[InvalidDateException]** 은``유효하지 않은 날짜입니다. 다시 입력해 주세요.``를 출력하고 아래의 오류 원인을 추가 출력한다.
    - **[EMPTY_INPUT]** : ``InvalidDateException``을 호출하고 `입력값이 없습니다.`를 추가 출력한다.
    - **[NON_NUMERIC]** : ``InvalidDateException``을 호출하고 `숫자만 입력 가능합니다.`를 추가 출력한다.
    - **[NEGATIVE_NUMBER]** : ``InvalidDateException``을 호출하고 `음수는 사용할 수 없습니다.`를 추가 출력한다.
    - **[OUT_OF_PROMOTION_PERIOD]** : ``InvalidDateException``을 호출하고 `예약 날짜는 1일에서 31일 사이입니다.`를 추가 출력한다.
    - **[입력 재요청]** : 예외가 발생한 입력 지점으로 돌아와 입력 재요청한다.

<br>

### 2️⃣ 주문할 메뉴 설정

- 필요한 기능

    - [x] **[주문 메뉴 설정]** : ``메뉴-수량``형식으로 주문을 설정할 수 있다.
    - [x] **[주문 총금액]** : 주문 총금액을 확인할 수 있다.
    - [x] **[주문표]** : 메뉴와 수량이 있는 주문표를 생성할 수 있다.

<br>

- 해당 기능과 관련된 검증 목록

    - **[입력값 없음]** : 입력하지 않은 경우
    - **[숫자가 아닌 주문량]** : 입력값이 숫자가 아닌 경우
    - **[음료만 있는 주문]** : 입력값이 음수인 경우
    - **[주문 최대치(20)를 벗어남]** : 주문 최대량 20을 벗어난 주문량
    - **[메뉴판에 없는 음식]** : 메뉴 목록에 없는 입력값
    - **[중복된 메뉴]** : 중복으로 주문한 메뉴
    - **[주문 형식: (,) 사이 빈 값]** : 두 쉽표 사이에 빈 값
    - **[주문 형식: (,) 로 시작하거나 끝남]** : 쉼표로 시작하거나 끝나는 입력값
    - **[주문 형식: 메뉴 - 수량 형식에 어긋남]** : 메뉴-수량 형식에 벗어난 입력값
    - **[주문 형식: 수량에 1미만 20초과된 숫자]** : 잘못된 수량 입력값

<br>

##### ⚠️ 예외 처리 ⚠️

- [x] ``IllegalArgumentException``를 상속한 **[ChristmasPromotionException]** 을 활용한다.
- [x] 예외 발생 시, ``ChristmasPromotionException`` 를 상속한 **[InvalidMenuException]** 을 활용한다.
- [x] **[InvalidMenuException]** 은``유효하지 않은 주문입니다. 다시 입력해 주세요.``를 출력하고 아래의 오류 원인을 추가 출력한다.
    - **[EMPTY_INPUT]** : ``InvalidMenuException``을 호출하고 `입력값이 없습니다.`를 추가 출력한다.
    - **[NON_NUMERIC]** : ``InvalidMenuException``을 호출하고 `숫자만 입력 가능합니다.`를 추가 출력한다.
    - **[BEVERAGE_ONLY_ORDER]** : ``InvalidMenuException``을 호출하고 `음료만 주문할 수 없습니다.`를 출력한다.
    - **[MAXIMUM_AMOUNT_OF_ORDER]** : ``InvalidMenuException``을 호출하고 `주문 총수량은 20을 넘을 수 없습니다.`를 출력한다.
    - **[NON_EXIST_MENU]** : ``InvalidMenuException``을 호출하고 `메뉴판에 존재하지 않은 메뉴입니다.`를 출력한다.
    - **[DUPLICATE_MENU]** : ``InvalidMenuException``을 호출하고 `중복된 메뉴가 있습니다.`를 출력한다.
    - **[BLANK_EXIST]** : ``InvalidMenuException``을 호출하고 `두 쉼표(,) 사이에 빈 값이 존재합니다.`를 출력한다.
    - **[BAD_DELIMITER]** : ``InvalidMenuException``을 호출하고 `메뉴는 쉼표(,)로 시작하거나 끝낼 수 없습니다.`를 출력한다.
    - **[INVALID_FORMAT]** : ``InvalidMenuException``을 호출하고 `메뉴 형식(메뉴-수량)에 맞지 않습니다.`를 출력한다.
    - **[INVALID_MENU_AMOUNT]** : ``InvalidMenuException``을 호출하고 `메뉴 수량은 1 이자상 20 이하여야 합니다.`를 출력한다.
    - **[입력 재요청]** : 예외가 발생한 입력 지점으로 돌아와 입력 재요청한다.

<br>

### 3️⃣ 할인

- 필요한 기능

    - [x] **[날짜에 맞는 할인 정책]** : 날짜에 맞는 할인 정책이 적용된다.
    - [x] **[할인이 적용되는 메뉴 타입]** : 메뉴 타입에 맞는 할인 정책이 적용된다.
    - [x] **[할인표]** : 할인 종류와 할인 적용 가격이 있는 할인표를 생성한다.
    - [x] **[할인 금액 계산]** : 적용된 할인 가격의 총액을 계산한다.

### 4️⃣ 증정품

- 필요한 기능

    - [x] **[증정품 설정]** : 증정품과 증정 수량을 설정할 수 있다.
    - [x] **[증정 조건]** : 증정 조건이 맞는지 확인한다.
    - [x] **[증정품 가격]** : 증정품 가격이 출력된다.

### 5️⃣ 배지

- 필요한 기능

    - [x] **[배지 조건]** : 5000원, 10000원, 20000원을 기준으로 배지를 생성한다.

### 6️⃣ 혜택

- 필요한 기능

    - [x] **[증정 정보]** : 증정 정보를 가져온다.
    - [x] **[혜택 내역]** : 할인 정보와 증정 정보를 통합해 혜택 종류와 금액 정보를 가져온다.
    - [x] **[총혜택 금액]** : 혜택 정보의 총 합계를 계산한다.
    - [x] **[할인 후 예상 결제 금액]** : 주문 정보와 총혜택 금액의 차액을 계산한다.
    - [x] **[배지 정보]** : 총혜택 금액에 맞는 배지 정보를 가져온다.



