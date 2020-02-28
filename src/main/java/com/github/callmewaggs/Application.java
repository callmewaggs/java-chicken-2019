package com.github.callmewaggs;

import com.github.callmewaggs.domain.Menu;
import com.github.callmewaggs.domain.MenuRepository;
import com.github.callmewaggs.domain.Table;
import com.github.callmewaggs.domain.TableRepository;
import com.github.callmewaggs.view.InputView;
import com.github.callmewaggs.view.OutputView;
import java.util.List;

public class Application {

  public static void main(String[] args) {
    final List<Table> tables = TableRepository.tables();
    OutputView.printTables(tables);

    final int tableNumber = InputView.inputTableNumber();

    final List<Menu> menus = MenuRepository.menus();
    OutputView.printMenus(menus);
  }
}
