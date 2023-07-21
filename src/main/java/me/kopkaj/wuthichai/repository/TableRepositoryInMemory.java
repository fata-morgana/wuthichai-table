package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.exception.ApplicationException;

import java.util.HashMap;
import java.util.Map;

public class TableRepositoryInMemory implements TableRepository {

    private boolean isInitialized;

    private static TableRepositoryInMemory instance;

    private int currentReservationId;

    private int numberOfAvailableTable;

    private final Map<Integer, Integer> reservationList;
    private TableRepositoryInMemory() {
        currentReservationId = 0;
        numberOfAvailableTable = 0;
        reservationList = new HashMap<>();
    }

    public static synchronized TableRepositoryInMemory getInstance() {
        if (instance == null) {
            instance = new TableRepositoryInMemory();
        }
        return instance;
    }

    public void initTables(int capacity) {
        if (isInitialized) {
            throw new ApplicationException("This method can be called only once.");
        }
        numberOfAvailableTable = capacity;
        isInitialized = true;
    }

    public int reserve(int tableNum) {
        if (!isInitialized) {
            throw new ApplicationException("Need to initialize with maxTable first by call method initTables(int capacity)");
        }
        int reservationId = currentReservationId + 1;
        reservationList.put(reservationId, tableNum);
        numberOfAvailableTable -= tableNum;
        return ++currentReservationId;
    }

    public void cancel(int reservationId) {
        if (!isInitialized) {
            throw new ApplicationException("Need to initialize with maxTable first by call method initTables(int capacity)");
        }
        int tableNum = reservationList.get(reservationId);
        numberOfAvailableTable += tableNum;
        reservationList.remove(reservationId);
    }

    public int numberOfAvailableTable() {
        return numberOfAvailableTable;
    }

    protected static synchronized void reset() {
        instance = null;
    }
}
