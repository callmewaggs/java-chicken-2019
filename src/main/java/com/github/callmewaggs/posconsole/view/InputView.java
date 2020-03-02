package com.github.callmewaggs.posconsole.view;

import java.util.Scanner;

public class InputView {

  private static final Scanner scanner = new Scanner(System.in);

  public static int inputTableNumberToOrder() {
    System.out.println("## 주문할 테이블을 선택하세요.");
    return scanner.nextInt();
  }

  public static int inputTableNumberToPayment() {
    System.out.println("## 결제할 테이블을 선택하세요.");
    return scanner.nextInt();
  }

  public static int inputCommandNumber() {
    System.out.println("## 원하는 기능을 선택하세요.");
    return scanner.nextInt();
  }

  public static int inputMenuNumber() {
    System.out.println("## 등록할 메뉴를 선택하세요.");
    return scanner.nextInt();
  }

  public static int inputMenuQuantity() {
    System.out.println("## 메뉴의 수량을 입력하세요.");
    return scanner.nextInt();
  }

  public static int inputPaymentMethod(String[] paymentMethods) {
    System.out.println("## 1번 테이블의 결제를 진행합니다.");
    String joined = String.join(", ", paymentMethods);
    System.out.println("## " + joined);
    return scanner.nextInt();
  }
}
