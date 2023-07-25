package me.kopkaj.wuthichai.service;

import me.kopkaj.wuthichai.exception.ReservationException;
import me.kopkaj.wuthichai.model.Table;
import me.kopkaj.wuthichai.repository.ReservationRepository;
import me.kopkaj.wuthichai.repository.TableRepository;

import java.util.List;

public abstract class TableServiceBase implements  TableService {

    private final TableRepository tableRepository;

    private final ReservationRepository reservationRepository;

    public TableServiceBase(TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void initializeTables(int capacity) {
        List<Table> tables = prepareTablesToInitialize(capacity);
        tableRepository.initTables(tables);
        reservationRepository.initReservation();
    }

    protected abstract List<Table> prepareTablesToInitialize(int capacity);

    public int makeReservation(int guestNumber) {
        List<Table> tables = findAvailableTablesFor(guestNumber);
        if(tables == null || tables.isEmpty()) {
            throw new ReservationException("Cannot find tables available for this reservation. Please try again later.");
        }
        int reservationId = reservationRepository.makeReservation(tables);
        tables.forEach(table -> tableRepository.reserve(table.getTableId()));
        return reservationId;
    }

    protected abstract List<Table> findAvailableTablesFor(int guestNumber);

    @Override
    public void cancelReservation(int reservationId) {
        List<Table> reservedTable = reservationRepository.getReservationTables(reservationId);
        reservedTable.forEach(table -> tableRepository.cancel(table.getTableId()));
        reservationRepository.cancelReservation(reservationId);
    }

    protected TableRepository getTableRepository() {
        return tableRepository;
    }

    protected ReservationRepository getReservationRepository() {
        return reservationRepository;
    }
}
