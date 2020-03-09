package com.github.callmewaggs.posconsole.processor.payment.policies;

import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MultiDiscountPolicy implements DiscountPolicy {

  private List<DiscountPolicy> duplicableDiscountPolicies;
  private List<DiscountPolicy> unduplicableDiscountPolicies;

  private List<DiscountPolicy> duplicableSatisfiedPolicies;
  private List<DiscountPolicy> unduplicableSatisfiedPolicies;

  public MultiDiscountPolicy(
      List<DiscountPolicy> duplicableDiscountPolicies,
      List<DiscountPolicy> unduplicableDiscountPolicies) {
    this.duplicableDiscountPolicies = duplicableDiscountPolicies;
    this.unduplicableDiscountPolicies = unduplicableDiscountPolicies;
  }

  @Override
  public boolean isSatisfied(List<Order> orderList, PaymentMethod paymentMethod) {
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

  @Override
  public int getDiscountedAmount(int amount) {
    List<DiscountPolicy> satisfied = extractSatisfiedPolicies(amount);

    int totalAmount = amount;
    for (DiscountPolicy policy : satisfied) {
      totalAmount = policy.getDiscountedAmount(totalAmount);
    }
    return totalAmount;
  }

  private List<DiscountPolicy> extractSatisfiedPolicies(int amount) {
    DiscountPolicy maxDiscountPolicy =
        unduplicableSatisfiedPolicies.stream()
            .max(Comparator.comparingInt(p -> p.getDiscountedAmount(amount)))
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
