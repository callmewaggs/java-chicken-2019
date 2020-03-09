package com.github.callmewaggs.posconsole.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TableTest {

  private Table table;

  @BeforeEach
  public void setup() {
    this.table = new Table(1);
  }

  @DisplayName("주문 내역을 테이블에 추가한다.")
  @Test
  public void add_order() {
    // Arrange
    Order order = mock(Order.class);

    // Act
    table.addOrder(order);

    // Assert
    assertEquals(1, table.getOrderList().size());
    assertTrue(table.hasOrder());
  }

  @DisplayName("주문 내역을 테이블에 추가할 때, 한 메뉴에 대해 99개를 초과하여 주문하려는 경우 예외를 던진다.")
  @Test
  public void exception_thrown_if_the_number_of_same_menu_exceed_99_when_add_order() {
    // Arrange
    Order order1 = mock(Order.class);
    Order order2 = mock(Order.class);

    when(order1.getMenu()).thenReturn(MenuRepository.findMenuByNumber(1));
    when(order1.getQuantity()).thenReturn(50);
    when(order2.getMenu()).thenReturn(MenuRepository.findMenuByNumber(1));
    when(order2.getQuantity()).thenReturn(50);

    table.addOrder(order1);

    // Act
    Exception actual = assertThrows(IllegalArgumentException.class, () -> table.addOrder(order2));

    // Assert
    assertEquals("테이블 당 하나의 메뉴는 99개 까지만 주문 가능합니다.", actual.getMessage());
  }

  @DisplayName("주문 내역을 초기화한다.")
  @Test
  public void init_order_list() {
    // Arrange
    Order order = mock(Order.class);
    table.addOrder(order);

    // Act
    table.initOrderList();

    // Assert
    assertEquals(0, table.getOrderList().size());
  }

  @Test
  public void toString_test() {
    assertEquals("1", table.toString());
  }
}
