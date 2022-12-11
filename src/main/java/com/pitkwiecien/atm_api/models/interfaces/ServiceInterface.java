package com.pitkwiecien.atm_api.models.interfaces;

public interface ServiceInterface {
    int verify();
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean verifyNotNulledParams();
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean verifyNotNulledObject();
}
