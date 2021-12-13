package com.epam.homework4.cinemawebapp.businesslogic;

import java.util.NoSuchElementException;
import java.util.Optional;

public final class OptionalCheckerImpl implements OptionalChecker{
    @Override
    public Object getValueIfPresent(Optional optional, String exceptionMessage) throws NoSuchElementException {
        if(optional.isEmpty()) throw new NoSuchElementException(exceptionMessage);
        return optional.get();
    }
}
