package com.github.callmewaggs.posconsole.processor.payment.policies;

import com.github.callmewaggs.posconsole.domain.Category;
import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.List;

public class QuantityDiscountPolicy implements DiscountPolicy {

  static int QUANTITY_DISCOUNT_PRICE = 10_000;
  static int QUANTITY_DISCOUNT_UNIT = 10;

  private int chickenQuantity;

  @Override
  public boolean isSatisfied(List<Order> orderList, PaymentMethod paymentMethod) {
    this.chickenQuantity =
        orderList.stream()
            .filter(order -> order.getMenu().getCategory().equals(Category.CHICKEN))
            .mapToInt(Order::getQuantity)
            .sum();
    return chickenQuantity >= QUANTITY_DISCOUNT_UNIT;
  }

  @Override
  public int getDiscountedAmount(int amount) {
    int originalAmount = amount;
    return originalAmount - (chickenQuantity / QUANTITY_DISCOUNT_UNIT * QUANTITY_DISCOUNT_PRICE);
  }
}
