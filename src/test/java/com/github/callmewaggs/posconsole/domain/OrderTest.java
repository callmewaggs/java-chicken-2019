package com.github.callmewaggs.posconsole.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {

  @DisplayName("주문은 메뉴와 수량을 입력받아 생성된다.")
  @Test
  public void order_created_with_menu_and_quantity() {
    // Arrange
    Menu menu = mock(Menu.class);
    int quantity = 1;

    // Act
    Order order = new Order(menu, quantity);

    // Assert
    assertNotNull(order.getMenu());
    assertEquals(1, order.getQuantity());
  }

  @DisplayName("주문 총액은 메뉴의 금액과 수량을 곱한 값으로 결정된다.")
  @Test
  public void amount_is_price_of_the_menu_multiply_quantity() {
    // Arrange
    Menu menu = mock(Menu.class);
    when(menu.getPrice()).thenReturn(10_000);
    int quantity = 10;
    Order order = new Order(menu, quantity);

    // Act
    int actual = order.getAmount();

    // Assert
    assertEquals(100_000, actual);
  }

  @Test
  public void toStringTest() {
    // Arrange
    Menu menu = mock(Menu.class);
    when(menu.getName()).thenReturn("신메뉴");
    when(menu.getPrice()).thenReturn(10_000);
    int quantity = 10;
    Order order = new Order(menu, quantity);

    // Act
    String actual = order.toString();

    // Assert
    assertEquals("신메뉴 10 100000", actual);
  }
}
