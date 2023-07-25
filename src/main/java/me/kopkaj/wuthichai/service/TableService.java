package me.kopkaj.wuthichai.service;

public interface TableService {

    void initializeTables(int capacity);
    /**
     * Reserve table(s) for number of guest <i>guestNumber</i>. Mark all reserved table(s) as unavailable until they're free
     *
     * @param guestNumber number of guest to reserve for one reservation
     * @return reservationId
     */
    int makeReservation(int guestNumber);

    /**
     * Cancel a reservation. Free all tables that related with this reservationId
     *
     * @param reservationId reservationId to cancel
     */
    void cancelReservation(int reservationId);
}
