package com.github.callmewaggs.posconsole;

import com.github.callmewaggs.posconsole.exception.QuitExecutionException;
import com.github.callmewaggs.posconsole.processor.POSProcessor;
import com.github.callmewaggs.posconsole.view.InputView;
import com.github.callmewaggs.posconsole.view.OutputView;
import java.util.Map;

public class POSMachine {

  private boolean runnable;
  private Map<POSCommand, POSProcessor> processorMapping;

  public POSMachine(Map<POSCommand, POSProcessor> processorMapping) {
    this.runnable = true;
    this.processorMapping = processorMapping;
  }

  public void start() {
    while (runnable) {
      OutputView.printMainCommands(POSCommand.values());
      try {
        POSCommand command = getCommand();
        POSProcessor processor = getProcessor(command);
        processor.run();
      } catch (Exception e) {
        OutputView.printMessage(e.getMessage());
        if (e instanceof QuitExecutionException) {
          runnable = false;
        }
      }
    }
  }

  private POSProcessor getProcessor(POSCommand command) {
    return processorMapping.get(command);
  }

  private POSCommand getCommand() {
    final int commandNumber = InputView.inputCommandNumber();
    return POSCommand.valueOf(commandNumber);
  }

}
