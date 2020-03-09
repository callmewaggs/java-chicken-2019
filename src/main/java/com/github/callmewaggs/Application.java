package com.github.callmewaggs;

import com.github.callmewaggs.posconsole.POSCommand;
import com.github.callmewaggs.posconsole.POSMachine;
import com.github.callmewaggs.posconsole.processor.POSOrderingProcessor;
import com.github.callmewaggs.posconsole.processor.POSPaymentProcessor;
import com.github.callmewaggs.posconsole.processor.POSProcessor;
import com.github.callmewaggs.posconsole.processor.POSQuitProcessor;
import com.github.callmewaggs.posconsole.processor.payment.DiscountAgency;
import com.github.callmewaggs.posconsole.processor.payment.policies.DiscountPolicy;
import com.github.callmewaggs.posconsole.processor.payment.policies.PaymentMethodDiscountPolicy;
import com.github.callmewaggs.posconsole.processor.payment.policies.QuantityDiscountPolicy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

  public static void main(String[] args) {
    Map<POSCommand, POSProcessor> processors = getProcessors();
    POSMachine posMachine = new POSMachine(processors);
    posMachine.start();
  }

  private static Map<POSCommand, POSProcessor> getProcessors() {
    POSOrderingProcessor posOrderingProcessor = new POSOrderingProcessor();
    DiscountAgency discountAgency = createDiscountAgency();
    POSPaymentProcessor posPaymentProcessor = new POSPaymentProcessor(discountAgency);
    POSQuitProcessor posQuitProcessor = new POSQuitProcessor();

    return createProcessorMapping(posOrderingProcessor, posPaymentProcessor, posQuitProcessor);
  }

  private static Map<POSCommand, POSProcessor> createProcessorMapping(
      POSOrderingProcessor posOrderingProcessor,
      POSPaymentProcessor posPaymentProcessor,
      POSQuitProcessor posQuitProcessor) {
    Map<POSCommand, POSProcessor> processorMapping = new HashMap<>();
    processorMapping.put(POSCommand.ORDERING, posOrderingProcessor);
    processorMapping.put(POSCommand.PAYMENT, posPaymentProcessor);
    processorMapping.put(POSCommand.QUIT, posQuitProcessor);
    return processorMapping;
  }

  private static DiscountAgency createDiscountAgency() {
    List<DiscountPolicy> duplicableDiscountPolicies = Arrays
        .asList(new PaymentMethodDiscountPolicy());
    List<DiscountPolicy> unduplicableDiscountPolicies = Arrays.asList(new QuantityDiscountPolicy());
    DiscountAgency discountAgency =
        new DiscountAgency(duplicableDiscountPolicies, unduplicableDiscountPolicies);
    return discountAgency;
  }
}
