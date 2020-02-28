package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TableRepositoryTest {

  @DisplayName("테이블 번호에 해당하는 테이블을 찾는다.")
  @Test
  public void find_table_by_number() {
    // Arrange
    int number = 1;

    // Act
    Table table = TableRepository.findTableByNumber(number);

    // Assert
    assertNotNull(table);
  }

  @DisplayName("테이블 번호에 해당하는 테이블이 없다면 예외를 던진다.")
  @Test
  public void exception_thrown_if_there_is_no_matched_table_when_find_table_by_number() {
    // Arrange
    int nonExistTableNumber = -1;

    // Act
    IllegalArgumentException actual =
        assertThrows(
            IllegalArgumentException.class,
            () -> TableRepository.findTableByNumber(nonExistTableNumber));

    // Assert
    assertEquals("일치하는 테이블이 없습니다. 다시 시도해 주세요.", actual.getMessage());
  }

  @Test
  public void tables_test() {
    List<Table> tables = TableRepository.tables();
    assertEquals(6, tables.size());
  }
}
