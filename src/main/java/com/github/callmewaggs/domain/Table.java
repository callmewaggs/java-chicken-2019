package com.github.callmewaggs.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {

  private final int number;
  private List<Order> orderList;

  public Table(final int number) {
    this.number = number;
    this.orderList = new ArrayList<>();
  }

  public int getNumber() {
    return number;
  }

  public List<Order> getOrderList() {
    return orderList;
  }

  @Override
  public String toString() {
    return Integer.toString(number);
  }

  public void addOrder(Order order) {
    verifyOrder(order);
    orderList.add(order);
  }

  private void verifyOrder(Order order) {
    int totalQuantityOfOrderedMenu =
        orderList.stream()
            .filter(ordered -> ordered.getMenu().equals(order.getMenu()))
            .mapToInt(Order::getQuantity)
            .sum();
    if (totalQuantityOfOrderedMenu + order.getQuantity() > Menu.MAX_ORDER_QUANTITY_PER_TABLE) {
      throw new IllegalArgumentException("테이블 당 하나의 메뉴는 99개 까지만 주문 가능합니다.");
    }
  }

  public void initOrderList() {
    this.orderList = new ArrayList<>();
  }
}
