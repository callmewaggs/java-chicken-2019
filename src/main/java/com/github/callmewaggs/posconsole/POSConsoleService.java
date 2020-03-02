package com.github.callmewaggs.posconsole;

import com.github.callmewaggs.posconsole.processor.POSOrderingProcessor;
import com.github.callmewaggs.posconsole.processor.POSPaymentProcessor;
import com.github.callmewaggs.posconsole.processor.POSProcessor;
import com.github.callmewaggs.posconsole.processor.POSQuitProcessor;
import com.github.callmewaggs.posconsole.processor.POSStartProcessor;
import com.github.callmewaggs.posconsole.processor.payment.PriceCalculator;
import java.util.HashMap;
import java.util.Map;

public class POSConsoleService {

  public void start() {
    Map<POSCommand, POSProcessor> processorMapping = new HashMap<>();

    POSOrderingProcessor posOrderingProcessor = new POSOrderingProcessor();
    PriceCalculator priceCalculator = new PriceCalculator();
    POSPaymentProcessor posPaymentProcessor = new POSPaymentProcessor(priceCalculator);
    POSQuitProcessor posQuitProcessor = new POSQuitProcessor();

    processorMapping.put(POSCommand.ORDERING, posOrderingProcessor);
    processorMapping.put(POSCommand.PAYMENT, posPaymentProcessor);
    processorMapping.put(POSCommand.QUIT, posQuitProcessor);

    POSStartProcessor posStartProcessor = new POSStartProcessor(processorMapping);
    posStartProcessor.run();
  }
}
