package com.sungwoo.aps.response;

import lombok.Data;

import java.util.Collection;

@Data
public final class ParkingAreaResponse<T, E> {
    Collection<T> areas;
    Collection<E> gates;

    public ParkingAreaResponse(Collection<T> areas, Collection<E> gates) {
        this.areas = areas;
        this.gates = gates;
    }
}
