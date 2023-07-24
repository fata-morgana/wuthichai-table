package me.kopkaj.wuthichai.model;

import java.util.List;

public record Reservation(int reservationId, List<Table> tables) {}
