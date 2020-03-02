package com.github.callmewaggs.posconsole.processor;

import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.domain.Table;
import com.github.callmewaggs.posconsole.domain.TableRepository;
import com.github.callmewaggs.posconsole.processor.payment.PaymentMethod;
import com.github.callmewaggs.posconsole.processor.payment.PriceCalculator;
import com.github.callmewaggs.posconsole.view.InputView;
import com.github.callmewaggs.posconsole.view.OutputView;
import java.util.List;

public class POSPaymentProcessor implements POSProcessor {

  private PriceCalculator priceCalculator;

  public POSPaymentProcessor(PriceCalculator priceCalculator) {
    this.priceCalculator = priceCalculator;
  }

  @Override
  public void run() {
    Table table = getInputAndFindTable();
    List<Order> orderList = table.getOrderList();
    OutputView.printOrderList(orderList);
    PaymentMethod paymentMethod = getInputPaymentMethod();
    int totalAmount = priceCalculator.calculate(orderList, paymentMethod);
    OutputView.printTotalAmount(totalAmount);
    table.initOrderList();
  }

  private PaymentMethod getInputPaymentMethod() {
    int paymentMethodNumber = InputView.inputPaymentMethod(PaymentMethod.allMethods());
    return PaymentMethod.valueOf(paymentMethodNumber);
  }

  private Table getInputAndFindTable() {
    final List<Table> tables = TableRepository.tables();
    OutputView.printTables(tables);
    final int tableNumber = InputView.inputTableNumberToPayment();
    Table found = TableRepository.findTableByNumber(tableNumber);
    if (!found.hasOrder()) {
      throw new IllegalArgumentException("주문이 등록되지 않은 테이블입니다. 다시 확인해주세요.");
    }
    return found;
  }
}
