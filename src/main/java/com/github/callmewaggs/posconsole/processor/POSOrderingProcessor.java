package com.github.callmewaggs.posconsole.processor;

import com.github.callmewaggs.posconsole.domain.Menu;
import com.github.callmewaggs.posconsole.domain.MenuRepository;
import com.github.callmewaggs.posconsole.domain.Order;
import com.github.callmewaggs.posconsole.domain.Table;
import com.github.callmewaggs.posconsole.domain.TableRepository;
import com.github.callmewaggs.posconsole.view.InputView;
import com.github.callmewaggs.posconsole.view.OutputView;
import java.util.List;

public class POSOrderingProcessor implements POSProcessor {

  @Override
  public void run() {
    Table table = getInputAndFindTable();
    Menu menu = getInputAndFindMenu();
    final int quantity = getInputQuantity();
    Order order = new Order(menu, quantity);
    table.addOrder(order);
  }

  private Table getInputAndFindTable() {
    final List<Table> tables = TableRepository.tables();
    OutputView.printTables(tables);
    final int tableNumber = InputView.inputTableNumber();
    return TableRepository.findTableByNumber(tableNumber);
  }

  private Menu getInputAndFindMenu() {
    final List<Menu> menus = MenuRepository.menus();
    OutputView.printMenus(menus);
    final int menuNumber = InputView.inputMenuNumber();
    return MenuRepository.findMenuByNumber(menuNumber);
  }

  private int getInputQuantity() {
    return InputView.inputMenuQuantity();
  }
}
