package com.realpacific.projectnoaa.readers;

public interface Reader<T> {
    T read(String displayMessage);
}
