package com.realpacific.projectnoaa.readers;

public class DummyReader implements Reader<String> {
    private String value;

    public DummyReader(String value) {
        this.value = value;
    }

    @Override
    public String read(String displayMessage) {
        return value;
    }
}
