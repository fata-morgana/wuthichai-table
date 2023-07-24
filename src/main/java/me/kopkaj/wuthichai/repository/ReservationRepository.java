package me.kopkaj.wuthichai.repository;

import me.kopkaj.wuthichai.model.Table;

import java.util.List;

public interface ReservationRepository {
    public int makeReservation(List<Table> tables);

    public List<Table> getReservationTables(int reservationId);

    public void cancelReservation(int reservationId);
}
