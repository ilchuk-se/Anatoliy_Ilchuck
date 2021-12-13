package com.epam.homework4.cinemawebapp.businesslogic;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface OptionalChecker<T> {
    T getValueIfPresent(Optional<T> optional, String exceptionMessage) throws NoSuchElementException;
}
