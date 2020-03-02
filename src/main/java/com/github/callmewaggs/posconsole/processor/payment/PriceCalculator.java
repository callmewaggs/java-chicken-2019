package com.github.callmewaggs.posconsole.processor.payment;

import com.github.callmewaggs.posconsole.domain.Order;
import java.util.List;

public class PriceCalculator {

  public int calculate(List<Order> orderList, PaymentMethod paymentMethod) {
    int amount = 0;
    for (Order order : orderList) {
      amount += order.getAmount();
    }
    return amount;
  }
}
