package com.github.callmewaggs.posconsole.processor.payment.policies;

import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.List;

public class PaymentMethodDiscountPolicy implements DiscountPolicy {

  static double CASH_DISCOUNT_PERCENTAGE = 0.05;

  @Override
  public boolean isSatisfied(List<Order> orderList, PaymentMethod paymentMethod) {
    return paymentMethod.equals(PaymentMethod.CASH);
  }

  @Override
  public int getDiscountPrice(int amount) {
    return (int) (amount * CASH_DISCOUNT_PERCENTAGE);
  }
}
