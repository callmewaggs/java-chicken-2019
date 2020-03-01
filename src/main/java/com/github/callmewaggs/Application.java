package com.github.callmewaggs;

import com.github.callmewaggs.posconsole.POSConsoleService;

public class Application {

  public static void main(String[] args) {
    POSConsoleService posConsoleService = new POSConsoleService();
    posConsoleService.start();
  }
}
