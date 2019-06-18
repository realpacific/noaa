package com.realpacific.projectnoaa.printers;

import java.util.List;

public abstract class Printer<T> {
    public abstract void print(List<T> records);
}
