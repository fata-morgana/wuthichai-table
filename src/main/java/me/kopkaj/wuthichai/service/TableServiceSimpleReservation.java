package me.kopkaj.wuthichai.service;

import me.kopkaj.wuthichai.model.Table;
import me.kopkaj.wuthichai.repository.ReservationRepository;
import me.kopkaj.wuthichai.repository.TableRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A strategy where each table can hold the same number of person and tables that make available to reserve can be any table with no constraint
 */
public class TableServiceSimpleReservation extends TableServiceBase {

    public static final int MAX_GUEST_PER_TABLE = 4;

    public TableServiceSimpleReservation(TableRepository tableRepository, ReservationRepository reservationRepository) {
        super(tableRepository, reservationRepository);
    }

    protected List<Table> prepareTablesToInitialize(int capacity) {
        return IntStream.range(0, capacity).mapToObj(i -> new Table(i, MAX_GUEST_PER_TABLE, true)).toList();
    }

    @Override
    protected List<Table> findAvailableTablesFor(int guestNumber) {
        int numberOfGuestSoFar = 0;
        List<Table> tablesAvailable = new ArrayList<>();
        for (Table each : getTableRepository().getAvailableTables()) {
            if (numberOfGuestSoFar >= guestNumber) {
                break;
            }
            if (each.isAvailable()) {
                numberOfGuestSoFar += each.getCapacity();
                tablesAvailable.add(each);
            }
        }
        return (numberOfGuestSoFar >= guestNumber) ? tablesAvailable : new ArrayList<>();
    }

}
