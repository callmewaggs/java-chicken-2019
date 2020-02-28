package com.github.callmewaggs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

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
}
