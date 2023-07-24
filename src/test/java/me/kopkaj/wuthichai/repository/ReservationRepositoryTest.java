package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.model.Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationRepositoryTest {
    private ReservationRepository repository = new ReservationRepositoryInMemory();
    private final List<Table> tables = new ArrayList<>() {{
        add(new Table(0, 4, true));
        add(new Table(1, 4, true));
        add(new Table(2, 4, true));
        add(new Table(3, 4, true));
    }};

    @Test
    public void testAll() {
        int reservationId1 = repository.makeReservation(new ArrayList<>() {{
            add(new Table(0, 4, true));
        }});
        List<Table> tables1 = repository.getReservationTables(reservationId1);
        assertEquals(0, tables1.get(0).getTableId());

        int reservationId2 = repository.makeReservation(new ArrayList<>() {{
            add(new Table(1, 4, true));
            add(new Table(2, 4, true));
        }});
        List<Table> tables2 = repository.getReservationTables(reservationId2);
        assertEquals(1, tables2.get(0).getTableId());
        assertEquals(2, tables2.get(1).getTableId());

        repository.cancelReservation(reservationId2);
        List<Table> tables3 = repository.getReservationTables(reservationId2);
        assertNull(tables3);
    }
}
