package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.model.Table;

import java.util.List;

/**
 * A class to do CRUD operation for table
 */
public interface TableRepository {
    /**
     * Initiate empty tables with a specific number.
     *
     * @param tables tables available for reservation
     */
    void initTables(List<Table> tables);

    /**
     * Reserve table with a specific tableId
     *
     * @param tableId ID of table to reserve
     */
    void reserve(int tableId);

    /**
     * Cancel table with a specific tableId
     *
     * @param tableId ID of table to cancel
     */
    void cancel(int tableId);

    /**
     *
     * @return number of available tables where it can be reserved
     */
    int numberOfAvailableTables();

    List<Table> getAvailableTables();
}
