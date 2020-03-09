package com.github.callmewaggs.posconsole.processor.payment.policies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.callmewaggs.posconsole.domain.Category;
import com.github.callmewaggs.posconsole.domain.Menu;
import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuantityDiscountPolicyTest {

  private QuantityDiscountPolicy quantityDiscountPolicy;

  @BeforeEach
  public void setup() {
    this.quantityDiscountPolicy = new QuantityDiscountPolicy();
  }

  @Test
  public void isSatisfiedTest() {
    // Arrange
    List<Order> orderList = new ArrayList<>();
    Menu menu = mock(Menu.class);
    when(menu.getCategory()).thenReturn(Category.CHICKEN);
    Order order = new Order(menu, 10);
    orderList.add(order);

    // Act
    boolean actual = quantityDiscountPolicy.isSatisfied(orderList, PaymentMethod.CREDIT_CARD);

    // Assert
    assertTrue(actual);
  }

  @Test
  public void getDiscountedAmountTest() {
    // Arrange
    Menu menu = mock(Menu.class);
    when(menu.getCategory()).thenReturn(Category.CHICKEN);
    Order order = new Order(menu, 10);
    List<Order> orderList = new ArrayList<>();
    orderList.add(order);
    quantityDiscountPolicy.isSatisfied(orderList, PaymentMethod.CREDIT_CARD);
    int amount = 10_000;

    // Act
    int actual = quantityDiscountPolicy.getDiscountedAmount(amount);

    // Assert
    int expected = 0;
    assertEquals(expected, actual);
  }
}
