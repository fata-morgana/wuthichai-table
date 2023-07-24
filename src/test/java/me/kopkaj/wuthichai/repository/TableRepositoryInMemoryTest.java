package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.exception.ApplicationException;
import me.kopkaj.wuthichai.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class TableRepositoryInMemoryTest {
    private static final Integer tableNumber = 10;
    private static final Integer tableCapacity = 4;

    private TableRepositoryInMemory repository;

    @BeforeEach
    public void setup() {
        TableRepositoryInMemory.reset();
        List<Table> tables = IntStream.range(0, tableNumber).mapToObj(i -> new Table(i, tableCapacity, true)).toList();
        repository = TableRepositoryInMemory.getInstance();
        repository.initTables(tables);
    }

    @Test
    public void testInitializeTwice() {
        assertThrows(ApplicationException.class, () -> repository.initTables(new ArrayList<>()));
    }

    @Test
    public void testNumberOfAvailableTable() {
        assertEquals(10, repository.numberOfAvailableTables());
    }

    @Test
    public void testReserveAndCancel() {
        repository.reserve(1);
        assertEquals(9, repository.numberOfAvailableTables());
        assertEquals(9, repository.getAvailableTables().size());
        assertEquals(0, repository.getAvailableTables().get(0).getTableId());
        assertEquals(2, repository.getAvailableTables().get(1).getTableId());

        repository.reserve(2);
        assertEquals(8, repository.numberOfAvailableTables());
        assertEquals(8, repository.getAvailableTables().size());
        assertEquals(0, repository.getAvailableTables().get(0).getTableId());
        assertEquals(3, repository.getAvailableTables().get(1).getTableId());

        repository.cancel(1);
        assertEquals(9, repository.numberOfAvailableTables());
        assertEquals(9, repository.getAvailableTables().size());
        assertEquals(0, repository.getAvailableTables().get(0).getTableId());
        assertEquals(1, repository.getAvailableTables().get(1).getTableId());
        assertEquals(3, repository.getAvailableTables().get(2).getTableId());

        repository.cancel(2);
        assertEquals(10, repository.numberOfAvailableTables());
        assertEquals(10, repository.getAvailableTables().size());
        assertEquals(0, repository.getAvailableTables().get(0).getTableId());
        assertEquals(1, repository.getAvailableTables().get(1).getTableId());
        assertEquals(2, repository.getAvailableTables().get(2).getTableId());
    }


    @Test
    public void testReserveOnUnInitializedRepo() {
        TableRepositoryInMemory.reset();
        assertThrows(ApplicationException.class, () -> repository.reserve(1));
    }

    @Test
    public void testCancelOnUnInitializedRepo() {
        TableRepositoryInMemory.reset();
        assertThrows(ApplicationException.class, () -> repository.cancel(1));
    }
}
