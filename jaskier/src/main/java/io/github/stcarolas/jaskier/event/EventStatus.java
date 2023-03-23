package io.github.stcarolas.jaskier.event;

public enum EventStatus {
    SUCCESS("success"), FAIL("-");

    private final String status;

    EventStatus(String status) {
        this.status = status;
    }
    
}
