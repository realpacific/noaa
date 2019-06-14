package com.realpacific.projectnoaa.parsers;

public interface Parser<T> {

    T parse(String text);
}
