package com.github.callmewaggs.posconsole.processor.payment.policies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentMethodDiscountPolicyTest {

  private PaymentMethodDiscountPolicy paymentMethodDiscountPolicy;

  @BeforeEach
  public void setup() {
    this.paymentMethodDiscountPolicy = new PaymentMethodDiscountPolicy();
  }

  @Test
  public void isSatisfiedTest() {
    // Arrange
    PaymentMethod cash = PaymentMethod.CASH;

    // Act
    boolean actual = paymentMethodDiscountPolicy.isSatisfied(new ArrayList<>(), cash);

    // Assert
    assertTrue(actual);
  }

  @Test
  public void getDiscountedAmountTest() {
    // Arrange
    int amount = 10_000;

    // Actual
    int actual = paymentMethodDiscountPolicy.getDiscountPrice(amount);

    // Assert
    int expected = (int) (amount * PaymentMethodDiscountPolicy.CASH_DISCOUNT_PERCENTAGE);
    assertEquals(expected, actual);
  }
}
