package com.github.callmewaggs.posconsole.processor;

import com.github.callmewaggs.posconsole.exception.QuitExecutionException;

public interface POSProcessor {

  void run() throws QuitExecutionException;
}
