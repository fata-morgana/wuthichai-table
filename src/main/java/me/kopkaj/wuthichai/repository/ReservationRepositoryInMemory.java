package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.exception.ReservationException;
import me.kopkaj.wuthichai.model.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReservationRepositoryInMemory implements ReservationRepository {

    private static AtomicInteger currentReservationId;

    private Map<Integer /* reservationId */, List<Table> /* reserved table */ > reservations;

    public ReservationRepositoryInMemory() {
        currentReservationId = new AtomicInteger(0);
        reservations = new HashMap<>();
    }

    @Override
    public int makeReservation(List<Table> tables) {
        int reservationId = currentReservationId.incrementAndGet();
        reservations.put(reservationId, tables);
        return reservationId;
    }

    @Override
    public List<Table> getReservationTables(int reservationId) {
        if (!reservations.containsKey(reservationId)) {
            throw new ReservationException("Cannot find reservationId: " + reservationId);
        }
        return reservations.get(reservationId);
    }

    @Override
    public void cancelReservation(int reservationId) {
        reservations.remove(reservationId);
    }
}
