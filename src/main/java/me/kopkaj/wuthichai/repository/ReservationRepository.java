package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.model.Table;

import java.util.List;

public interface ReservationRepository {
    /**
     * Initiate reservation repository.
     */
    void initReservation();

    /**
     * Reserve the specific tables
     *
     * @param tables tables to reserve
     * @return reservationId
     */
    public int makeReservation(List<Table> tables);

    /**
     * Get tables related to this <i>reservationId</i>
     *
     * @param reservationId reserveId
     * @return related tables
     */
    public List<Table> getReservationTables(int reservationId);

    /**
     * Cancel reservation of the <i>reservationId</i>
     *
     * @param reservationId reserveId
     */
    public void cancelReservation(int reservationId);
}
