package me.kopkaj.wuthichai.controller;

import me.kopkaj.wuthichai.controller.model.*;
import me.kopkaj.wuthichai.service.TableService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller to handle various operation related to table
 */
@RestController
@RequestMapping("restaurant")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping(path = "init", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TableResponse> initializeTable(@RequestBody InitTableRequest request) {
        tableService.initializeTables(request.capacity());
        return ResponseEntity.ok(new TableResponse("OK"));
    }

    @PostMapping(path = "reserve", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReservationResponse> reserveTableFor(@RequestBody ReserveTableRequest request) {
        return ResponseEntity.ok(new ReservationResponse(tableService.makeReservation(request.guestNumber())));
    }

    @PostMapping(path = "cancel", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TableResponse> cancelReservation(@RequestBody CancelReservationRequest request) {
        tableService.cancelReservation(request.reservationId());
        return ResponseEntity.ok(new TableResponse("OK"));
    }
}
