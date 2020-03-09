package com.github.callmewaggs.posconsole.processor.payment.policies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultiDiscountPolicyTest {

  private MultiDiscountPolicy multiDiscountPolicy;
  private List<DiscountPolicy> duplicable;
  private List<DiscountPolicy> unduplicable;
  private DiscountPolicy policy1;
  private DiscountPolicy policy2;

  @BeforeEach
  public void setup() {
    this.policy1 = mock(DiscountPolicy.class);
    this.policy2 = mock(DiscountPolicy.class);
    this.duplicable = Arrays.asList(policy1);
    this.unduplicable = Arrays.asList(policy2);
    this.multiDiscountPolicy = new MultiDiscountPolicy(duplicable, unduplicable);
  }

  @Test
  public void isSatisfiedTest() {
    // Arrange
    when(policy1.isSatisfied(any(List.class), any(PaymentMethod.class))).thenReturn(true);
    when(policy2.isSatisfied(any(List.class), any(PaymentMethod.class))).thenReturn(false);

    // Act
    boolean actual = multiDiscountPolicy.isSatisfied(new ArrayList<>(), PaymentMethod.CASH);

    // Assert
    assertTrue(actual);
  }

  @Test
  public void getDiscountedAmountTest() {
    // Arrange
    when(policy1.isSatisfied(any(List.class), any(PaymentMethod.class))).thenReturn(true);
    when(policy1.getDiscountedAmount(any(Integer.class))).thenReturn(10_000);
    when(policy2.isSatisfied(any(List.class), any(PaymentMethod.class))).thenReturn(true);
    when(policy2.getDiscountedAmount(any(Integer.class))).thenReturn(10_000);
    multiDiscountPolicy.isSatisfied(new ArrayList<>(), PaymentMethod.CASH);
    int amount = 100_000;

    // Act
    int actual = multiDiscountPolicy.getDiscountedAmount(amount);

    // Assert
    assertEquals(10_000, actual);
  }
}
