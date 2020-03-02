package com.github.callmewaggs.posconsole.view;

import com.github.callmewaggs.posconsole.POSCommand;
import com.github.callmewaggs.posconsole.domain.Menu;
import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.domain.Table;
import java.util.Arrays;
import java.util.List;

public class OutputView {

  private static final String TOP_LINE = "┌ ─ ┐";
  private static final String TABLE_FORMAT = "| %s |";
  private static final String BOTTOM_LINE = "└ ─ ┘";
  private static final String ORDERED_BOTTOM_LINE = "└ ₩ ┘";

  public static void printTables(final List<Table> tables) {
    System.out.println("## 테이블 목록");
    final int size = tables.size();
    printTopLine(TOP_LINE, size);
    printTableNumbers(tables);
    printBottomLine(BOTTOM_LINE, ORDERED_BOTTOM_LINE, tables);
  }

  public static void printMenus(final List<Menu> menus) {
    for (final Menu menu : menus) {
      System.out.println(menu);
    }
  }

  private static void printTopLine(final String line, final int count) {
    for (int index = 0; index < count; index++) {
      System.out.print(line);
    }
    System.out.println();
  }

  private static void printBottomLine(
      final String defaultLine, final String specialLine, List<Table> tables) {
    for (Table table : tables) {
      if (table.hasOrder()) {
        System.out.print(specialLine);
        continue;
      }
      System.out.print(defaultLine);
    }
    System.out.println();
  }

  private static void printTableNumbers(final List<Table> tables) {
    for (final Table table : tables) {
      System.out.printf(TABLE_FORMAT, table);
    }
    System.out.println();
  }

  public static void printMainCommands(POSCommand[] commands) {
    System.out.println("## 메인화면");
    Arrays.stream(commands).forEach(System.out::println);
  }

  public static void printMessage(String message) {
    System.out.println(message);
  }

  public static void printOrderList(List<Order> orderList) {
    System.out.println("## 주문내역");
    System.out.println("메뉴 수량 금액");
    for (Order order : orderList) {
      System.out.println(order.toString());
    }
  }

  public static void printTotalAmount(int totalAmount) {
    System.out.println("## 최종 결제할 금액");
    System.out.println(totalAmount + "원");
  }
}
