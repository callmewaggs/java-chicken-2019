package com.github.callmewaggs.posconsole.processor.payment.policies;

import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.List;

public interface DiscountPolicy {

  boolean isSatisfied(List<Order> orderList, PaymentMethod paymentMethod);

  int getDiscountPrice(int amount);
}
