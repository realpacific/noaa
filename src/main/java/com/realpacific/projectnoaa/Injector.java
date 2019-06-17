package com.realpacific.projectnoaa;

import com.realpacific.projectnoaa.services.RecordService;
import com.realpacific.projectnoaa.services.imp.RecordServiceImp;

public class Injector {
    private static RecordService recordService;

    public static RecordService getRecordService() {
        if (recordService == null) {
            recordService = new RecordServiceImp();
        }
        return recordService;
    }
}
