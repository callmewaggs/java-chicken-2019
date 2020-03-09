package com.github.callmewaggs.posconsole.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {

  private final int number;
  private List<Order> orderList;

  public Table(final int number) {
    this.number = number;
    this.orderList = new ArrayList<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Table)) {
      return false;
    }
    Table table = (Table) o;
    return getNumber() == table.getNumber() &&
        getOrderList().equals(table.getOrderList());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNumber(), getOrderList());
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

  public boolean hasOrder() {
    return !orderList.isEmpty();
  }

  public void initOrderList() {
    this.orderList = new ArrayList<>();
  }
}
