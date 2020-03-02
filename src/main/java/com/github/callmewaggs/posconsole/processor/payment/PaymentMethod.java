package com.github.callmewaggs.posconsole.processor.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum PaymentMethod {
  CREDIT_CARD(1, "신용카드"),
  CASH(2, "현금");

  private static Map<Integer, PaymentMethod> paymentMethodMapping = new HashMap<>();

  static {
    for (PaymentMethod paymentMethod : PaymentMethod.values()) {
      paymentMethodMapping.put(paymentMethod.number, paymentMethod);
    }
  }

  private int number;
  private String method;

  PaymentMethod(int number, String method) {
    this.number = number;
    this.method = method;
  }

  public static PaymentMethod valueOf(int number) {
    if (!paymentMethodMapping.containsKey(number)) {
      throw new IllegalArgumentException("잘못된 결제 수단이 입력되었습니다. 다시 확인해주세요.");
    }
    return paymentMethodMapping.get(number);
  }

  public static String[] allMethods() {
    return Stream.of(PaymentMethod.values()).map(PaymentMethod::toString).toArray(String[]::new);
  }

  @Override
  public String toString() {
    return this.method + " " + this.number + "번";
  }
}
