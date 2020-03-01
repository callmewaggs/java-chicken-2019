package com.github.callmewaggs.posconsole.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

  @DisplayName("메뉴는 번호, 이름, 카테고리, 가격이 입력되어야 생성된다.")
  @Test
  public void create_menu_test() {
    // Arrange
    int number = 1;
    String name = "메뉴";
    Category category = Category.CHICKEN;
    int price = 10_000;

    // Act
    Menu actual = new Menu(number, name, category, price);

    // Assert
    assertEquals(number, actual.getNumber());
    assertEquals(name, actual.getName());
    assertEquals(category, actual.getCategory());
    assertEquals(price, actual.getPrice());
  }

  @Test
  public void toString_test() {
    // Arrange
    int number = 1;
    String name = "메뉴";
    Category category = Category.CHICKEN;
    int price = 10_000;

    // Act
    Menu actual = new Menu(number, name, category, price);

    // Assert
    assertEquals("[치킨] 1 - 메뉴 : 10000원", actual.toString());
  }
}
