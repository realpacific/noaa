package com.realpacific.projectnoaa.printers;

import com.realpacific.projectnoaa.entities.Record;

import java.util.List;

public interface RecordPrinter {
    void print(List<Record> records);
}
