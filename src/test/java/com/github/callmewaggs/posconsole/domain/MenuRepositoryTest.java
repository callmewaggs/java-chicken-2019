package com.github.callmewaggs.posconsole.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuRepositoryTest {

  @DisplayName("메뉴 번호에 해당하는 메뉴를 찾는다.")
  @Test
  public void find_menu_by_number() {
    // Arrange
    int number = 1;

    // Act
    Menu actual = MenuRepository.findMenuByNumber(number);

    // Assert
    assertNotNull(actual);
  }

  @DisplayName("메뉴 번호에 해당하는 메뉴가 없다면 예외를 던진다.")
  @Test
  public void exception_thrown_if_there_is_no_matched_menu_when_find_menu_by_number() {
    // Arrange
    int nonExistMenuNumber = -1;

    // Act
    IllegalArgumentException actual =
        assertThrows(
            IllegalArgumentException.class,
            () -> MenuRepository.findMenuByNumber(nonExistMenuNumber));

    // Assert
    assertEquals("일치하는 메뉴가 없습니다. 다시 시도해 주세요.", actual.getMessage());
  }

  @Test
  public void menus_test() {
    List<Menu> menus = MenuRepository.menus();
    assertEquals(8, menus.size());
  }
}
