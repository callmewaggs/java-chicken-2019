package com.github.callmewaggs.posconsole;

import java.util.HashMap;
import java.util.Map;

public enum POSCommand {
  ORDERING(1, "주문등록"),
  PAYMENT(2, "결제하기"),
  QUIT(3, "프로그램 종료");

  private static Map<Integer, POSCommand> commandMapping = new HashMap<>();

  static {
    for (POSCommand posCommand : POSCommand.values()) {
      commandMapping.put(posCommand.number, posCommand);
    }
  }

  private int number;
  private String command;

  POSCommand(int number, String command) {
    this.number = number;
    this.command = command;
  }

  public static POSCommand valueOf(int number) {
    if (!commandMapping.containsKey(number)) {
      throw new IllegalArgumentException("잘못된 기능이 입력되었습니다. 다시 확인해주세요.");
    }
    return commandMapping.get(number);
  }

  @Override
  public String toString() {
    return number + " - " + command;
  }
}
