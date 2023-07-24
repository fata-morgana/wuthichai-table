package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.exception.ApplicationException;
import me.kopkaj.wuthichai.model.Table;

import java.util.List;
import java.util.stream.IntStream;

public class TableRepositoryInMemory implements TableRepository {
    private static Integer TABLE_CAPACITY = 4;

    private static boolean isInitialized;

    private static TableRepositoryInMemory instance;

    private int numberOfAvailableTables;

    private List<Table> tables;

    private TableRepositoryInMemory() {
        numberOfAvailableTables = 0;
    }

    public static synchronized TableRepositoryInMemory getInstance() {
        if (instance == null) {
            instance = new TableRepositoryInMemory();
        }
        return instance;
    }

    public void initTables(List<Table> tables) {
        if (isInitialized) {
            throw new ApplicationException("This method can be called only once.");
        }
        isInitialized = true;
        this.tables = tables;
        numberOfAvailableTables = tables.size();
    }

    public void reserve(int tableId) {
        if (!isInitialized) {
            throw new ApplicationException("Need to initialize with maxTable first by call method initTables(int capacity)");
        }
        numberOfAvailableTables--;
        tables.get(tableId).setAvailable(false);
    }

    public void cancel(int tableId) {
        if (!isInitialized) {
            throw new ApplicationException("Need to initialize with maxTable first by call method initTables(int capacity)");
        }
        numberOfAvailableTables++;
        tables.get(tableId).setAvailable(true);
    }

    public int numberOfAvailableTables() {
        return numberOfAvailableTables;
    }

    public List<Table> getAvailableTables() {
        return tables.stream().filter(t -> t.isAvailable()).toList();
    }

    protected static synchronized void reset() {
        instance = null;
        isInitialized = false;
    }
}
