# java-chicken-2019

## 기능 요구사항
 * 치킨집 사장님이 사용하는 간단한 포스(POS) 프로그램을 구현한다.   주문등록, 결제하기, 프로그램 종료 기능을 가진다.
 * 메뉴 기본 정보가 주어지며 메뉴 번호, 종류, 이름, 가격을 가진다.
 * 테이블 기본 정보가 주어지며 테이블 번호를 가진다.
 * 한 테이블에서 주문할 수 있는 한 메뉴의 최대 수량은 99개이다.
 * 주문이 등록된 테이블은 결제가 이루어지기 전까지 테이블 목록에 별도로 표시한다.
 * 주문 내역에 대한 계산을 할 때는 결제 유형에 따라 할인율이 달라진다.
   - 치킨 종류 메뉴의 수량 합이 10개가 넘는 경우 10,000원씩 할인된다.
     - e.g. 10개는 10,000원 할인, 20개는 20,000원 할인
   - 현금 결제는 5%가 할인되며 할인된 금액에서 한 번 더 할인이 가능하다.
 * 주문 혹은 결제가 불가능한 경우 그 이유를 보여 주고, 다시 주문 혹은 결제가 가능하도록 해야 한다.
 * 최종 결제 금액을 보여준다.
 
## 기능 목록
 * 샤용자에게 기능을 입력받고 해당 기능을 실행해야 한다.
 * 주문 등록의 경우 사용자에게 테이블 번호, 메뉴, 수량을 입력받는다.
   - 주문은 메뉴와 수량을 알고 있다.
 * 이 때, 지원하지 않는 번호가 입력된 경우 예외를 던진다.
 * 한 테이블에서 한 메뉴에 대해 99개를 초과하여 주문하려는 경우 예외를 던진다.
 * 주문이 등록된 테이블은 결제가 이루어지기 전까지 테이블 목록에 별도로 표시한다.
 * 결제 하의 경우 결제할 테이블을 선택하면 해당 테이블의 주문 내역을 보여주고 결제를 진행한다.
 * 이 때, 지원하지 않는 번호가 입력되거나, 입력한 테이블에 주문 내역이 없는 경우 예외를 던진다.
 * 결제는 주문 내역과 결제 수단을 고려하여 최종 결제할 금액을 보여준다.