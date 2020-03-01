package com.github.callmewaggs.posconsole;

import com.github.callmewaggs.posconsole.processor.POSProcessor;
import com.github.callmewaggs.posconsole.processor.POSQuitProcessor;
import com.github.callmewaggs.posconsole.processor.POSStartProcessor;
import java.util.HashMap;
import java.util.Map;

public class POSConsoleService {

  public void start() {
    Map<POSCommand, POSProcessor> processorMapping = new HashMap<>();
    processorMapping.put(POSCommand.QUIT, new POSQuitProcessor());
    POSStartProcessor posStartProcessor = new POSStartProcessor(processorMapping);
    posStartProcessor.run();
  }
}
