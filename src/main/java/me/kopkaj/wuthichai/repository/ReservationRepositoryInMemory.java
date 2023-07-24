package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.exception.ApplicationException;
import me.kopkaj.wuthichai.exception.ReservationException;
import me.kopkaj.wuthichai.model.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReservationRepositoryInMemory implements ReservationRepository {

    private static boolean isInitialized;

    private static ReservationRepositoryInMemory instance;

    private static AtomicInteger currentReservationId;

    private Map<Integer /* reservationId */, List<Table> /* reserved table */ > reservations;

    public static synchronized ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepositoryInMemory();
        }
        return instance;
    }

    public void initReservation() {
        if (isInitialized) {
            throw new ApplicationException("This method can be called only once.");
        }
        isInitialized = true;
    }

    private ReservationRepositoryInMemory() {
        isInitialized = true;
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
        if (!isInitialized) {
            throw new ApplicationException("Need to initialize with maxTable first by call method initTables(int capacity)");
        }
        if (!reservations.containsKey(reservationId)) {
            throw new ReservationException("Cannot find reservationId: " + reservationId);
        }
        return reservations.get(reservationId);
    }

    @Override
    public void cancelReservation(int reservationId) {
        reservations.remove(reservationId);
    }

    protected static synchronized void reset() {
        instance = null;
        isInitialized = false;
    }
}
