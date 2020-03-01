package com.github.callmewaggs.posconsole.domain;

public class Order {

  private Menu menu;
  private int quantity;

  public Order(Menu menu, int quantity) {
    this.menu = menu;
    this.quantity = quantity;
  }

  public Menu getMenu() {
    return menu;
  }

  public int getQuantity() {
    return quantity;
  }
}
