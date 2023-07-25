package me.kopkaj.wuthichai.service;

import me.kopkaj.wuthichai.exception.ReservationException;
import me.kopkaj.wuthichai.model.Table;
import me.kopkaj.wuthichai.repository.ReservationRepository;
import me.kopkaj.wuthichai.repository.TableRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TableBaseTest {
    private final TableRepository tableRepository = mock(TableRepository.class);

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);

    private final TableServiceBase service = spy(new TableServiceBase(tableRepository, reservationRepository) {
        @Override
        protected List<Table> prepareTablesToInitialize(int capacity) {
            return new ArrayList<>();
        }

        @Override
        protected List<Table> findAvailableTablesFor(int guestNumber) {
            return  new ArrayList<>();
        }
    });

    @Test
    public void testInitializeTable() {
        when(service.prepareTablesToInitialize(1)).thenReturn(new ArrayList<Table>() {{
            add(new Table(0, 4, true));
        }});
        service.initializeTables(1);
        verify(service, times(1)).prepareTablesToInitialize(1);
    }

    @Test
    public void testMakeReservationFail() {
        when(service.findAvailableTablesFor(1)).thenReturn(new ArrayList<>());
        assertThrows(ReservationException.class, () -> service.makeReservation(1));
        verify(service, times(1)).makeReservation(1);
    }

    @Test
    public void testMakeReservationPassed() {
        when(service.findAvailableTablesFor(1)).thenReturn(new ArrayList<>(){{
            add(new Table(0, 4, true));
        }});
        int reservationId1 = service.makeReservation(1);
        verify(service, times(1)).makeReservation(1);
        assertEquals(0, reservationId1);
        when(service.findAvailableTablesFor(1)).thenReturn(new ArrayList<>(){{
            add(new Table(1, 4, true));
        }});
        int reservationId2 = service.makeReservation(1);
        verify(service, times(2)).makeReservation(1);
        assertEquals(0, reservationId2);
    }

    @Test
    public void testCancelReservation() {
        when(reservationRepository.getReservationTables(1)).thenReturn(new ArrayList<>(){{
            add(new Table(0, 4, true));
            add(new Table(1, 4, true));
        }});
        doNothing().when(tableRepository).cancel(0);
        doNothing().when(tableRepository).cancel(2);
        service.cancelReservation(1);
        verify(reservationRepository, times(1)).cancelReservation(1);
        verify(tableRepository, times(1)).cancel(0);
        verify(tableRepository, times(1)).cancel(1);
    }
}
