package me.kopkaj.wuthichai.service;

import me.kopkaj.wuthichai.exception.ReservationException;
import me.kopkaj.wuthichai.model.Table;
import me.kopkaj.wuthichai.repository.ReservationRepository;
import me.kopkaj.wuthichai.repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

public class TableServiceSimpleReservationTest {
    private final TableRepository tableRepository = mock(TableRepository.class);

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);

    private final TableServiceSimpleReservation service = spy(new TableServiceSimpleReservation(tableRepository, reservationRepository));

    @Test
    public void testPrepareTablesToInitialize() {
        List<Table> tables = service.prepareTablesToInitialize(3);
        assertEquals(0, tables.get(0).getTableId());
        assertEquals(4, tables.get(0).getCapacity());
        assertEquals(true, tables.get(0).isAvailable());
        assertEquals(1, tables.get(1).getTableId());
        assertEquals(4, tables.get(1).getCapacity());
        assertEquals(true, tables.get(1).isAvailable());
        assertEquals(2, tables.get(2).getTableId());
        assertEquals(4, tables.get(2).getCapacity());
        assertEquals(true, tables.get(2).isAvailable());
    }

    @Test
    public void testFindAvailableTablesFor() {
        List<Table> table1 = new ArrayList<Table>() {{
            add(new Table(0, 4, true));
        }};
        when(tableRepository.getAvailableTables()).thenReturn(table1);
        assertEquals(1, service.findAvailableTablesFor(1).size());
        List<Table> table2 = new ArrayList<Table>() {{
            add(new Table(0, 4, true));
            add(new Table(1, 4, true));
        }};
        when(tableRepository.getAvailableTables()).thenReturn(table2);
        assertEquals(2, service.findAvailableTablesFor(5).size());
        assertEquals(2, service.findAvailableTablesFor(8).size());
    }
}
