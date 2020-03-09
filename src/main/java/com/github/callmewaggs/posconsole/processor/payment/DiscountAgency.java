package com.github.callmewaggs.posconsole.processor.payment;

import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.processor.payment.policies.DiscountPolicy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountAgency {

  private List<DiscountPolicy> duplicableDiscountPolicies;
  private List<DiscountPolicy> unduplicableDiscountPolicies;

  private List<DiscountPolicy> duplicableSatisfiedPolicies;
  private List<DiscountPolicy> unduplicableSatisfiedPolicies;

  public DiscountAgency(
      List<DiscountPolicy> duplicableDiscountPolicies,
      List<DiscountPolicy> unduplicableDiscountPolicies) {
    this.duplicableDiscountPolicies = duplicableDiscountPolicies;
    this.unduplicableDiscountPolicies = unduplicableDiscountPolicies;
  }

  public boolean isDiscountable(List<Order> orderList, PaymentMethod paymentMethod) {
    unduplicableSatisfiedPolicies =
        unduplicableDiscountPolicies.stream()
            .filter(p -> p.isSatisfied(orderList, paymentMethod))
            .collect(Collectors.toList());
    duplicableSatisfiedPolicies =
        duplicableDiscountPolicies.stream()
            .filter(p -> p.isSatisfied(orderList, paymentMethod))
            .collect(Collectors.toList());
    return duplicableSatisfiedPolicies.size() != 0 || unduplicableSatisfiedPolicies.size() != 0;
  }

  public int getDiscountedAmount(int amount) {
    List<DiscountPolicy> satisfied = extractSatisfiedPolicies(amount);

    int totalAmount = amount;
    for (DiscountPolicy policy : satisfied) {
      totalAmount -= policy.getDiscountPrice(totalAmount);
    }
    return totalAmount;
  }

  private List<DiscountPolicy> extractSatisfiedPolicies(int amount) {
    DiscountPolicy maxDiscountPolicy =
        unduplicableSatisfiedPolicies.stream()
            .max(Comparator.comparingInt(p -> p.getDiscountPrice(amount)))
            .orElse(null);

    List<DiscountPolicy> discountPolicies = new ArrayList<>();
    if (maxDiscountPolicy != null) {
      discountPolicies.add(maxDiscountPolicy);
    }

    if (!duplicableSatisfiedPolicies.isEmpty()) {
      discountPolicies.addAll(duplicableSatisfiedPolicies);
    }
    return discountPolicies;
  }
}
