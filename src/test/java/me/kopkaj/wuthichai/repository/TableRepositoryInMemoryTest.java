package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableRepositoryInMemoryTest {

    private TableRepositoryInMemory repository;

    @BeforeEach
    public void setup() {
        TableRepositoryInMemory.reset();
        repository = TableRepositoryInMemory.getInstance();
        repository.initTables(10);
    }

    @Test
    public void testInitializeTwice() {
        assertThrows(ApplicationException.class, () -> repository.initTables(10));
    }

    @Test
    public void testNumberOfAvailableTable() {
        assertEquals(10, repository.numberOfAvailableTable());
    }

    @Test
    public void testReserveAndCancel() {
        int reservationId1 = repository.reserve(1);
        assertEquals(9, repository.numberOfAvailableTable());
        assertEquals(1, reservationId1);

        int reservationId2 = repository.reserve(2);
        assertEquals(7, repository.numberOfAvailableTable());
        assertEquals(2, reservationId2);

        repository.cancel(1);
        assertEquals(8, repository.numberOfAvailableTable());

        repository.cancel(2);
        assertEquals(10, repository.numberOfAvailableTable());

    }
}
