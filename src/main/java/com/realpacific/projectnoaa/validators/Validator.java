package com.realpacific.projectnoaa.validators;

import com.realpacific.projectnoaa.entities.Station;

public interface Validator<T> {
    boolean isValid(T object);
}
