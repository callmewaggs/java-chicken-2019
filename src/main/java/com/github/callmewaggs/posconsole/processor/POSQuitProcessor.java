package com.github.callmewaggs.posconsole.processor;

import com.github.callmewaggs.posconsole.exception.QuitExecutionException;

public class POSQuitProcessor implements POSProcessor {

  @Override
  public void run() throws QuitExecutionException {
    throw new QuitExecutionException("프로그램을 종료합니다.");
  }
}
