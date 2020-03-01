package com.github.callmewaggs.posconsole.processor;

import com.github.callmewaggs.posconsole.POSCommand;
import com.github.callmewaggs.posconsole.exception.QuitExecutionException;
import com.github.callmewaggs.posconsole.view.InputView;
import com.github.callmewaggs.posconsole.view.OutputView;
import java.util.Map;

public class POSStartProcessor implements POSProcessor {

  private Map<POSCommand, POSProcessor> processorMapping;
  private boolean runnable;

  public POSStartProcessor(Map<POSCommand, POSProcessor> processorMapping) {
    this.processorMapping = processorMapping;
    this.runnable = true;
  }

  @Override
  public void run() {
    while (runnable) {
      startMainFunction();
    }
  }

  private void startMainFunction() {
    try {
      OutputView.printMainCommands(POSCommand.values());
      POSCommand command = getCommand();
      executeProcessor(command);
    } catch (Exception e) {
      verifyQuitCondition(e);
      OutputView.printMessage(e.getMessage());
    }
  }

  private void verifyQuitCondition(Exception e) {
    if (e instanceof QuitExecutionException) {
      runnable = false;
    }
  }

  private void executeProcessor(POSCommand command) throws QuitExecutionException {
    POSProcessor processor = processorMapping.get(command);
    processor.run();
  }

  private POSCommand getCommand() {
    final int commandNumber = InputView.inputCommandNumber();
    return POSCommand.valueOf(commandNumber);
  }
}
