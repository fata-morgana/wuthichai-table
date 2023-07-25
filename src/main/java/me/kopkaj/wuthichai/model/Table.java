package me.kopkaj.wuthichai.model;

public class Table {
    private final Integer tableId;

    private final Integer capacity;

    private Boolean isAvailable;

    public Table(Integer tableId, Integer capacity, Boolean isAvailable) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
    }

    public Integer getTableId() {
        return tableId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}