package me.kopkaj.wuthichai.repository;

/**
 * A class to do CRUD operation for table
 */
public interface TableRepository {
    /**
     * Initiate empty tables with a specific number.
     *
     * @param tableCapacity number of all tables available
     */
    void initTables(int tableCapacity);

    /**
     * Reserve table with a specific number. The number of remaining table will be deducted by <i>tableNum</i>
     *
     * @param tableNum number of table to reserve
     * @return reservationId
     */
    int reserve(int tableNum);

    /**
     * Cancel table with a specific reservationId
     *
     * @param reservationId ID of reservation to cancel
     */
    void cancel(int reservationId);

    /**
     *
     * @return number of available tables where it can be reserved
     */
    int numberOfAvailableTable();
}
