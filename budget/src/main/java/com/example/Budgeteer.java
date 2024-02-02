package com.example;

import java.io.File;

public class Budgeteer {

    static String jarPath = Budgeteer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    static String directoryPath = new File(jarPath).getParent();

    static String pre_data_path = directoryPath + File.separator + "data.json";
    static String pre_meta_path = directoryPath + File.separator + "meta.json";

    public static String data_path = pre_data_path;
    public static String meta_path = pre_meta_path;

    public void addLog() {
        // Logic for adding log
        AddLog addLog = new AddLog();
        addLog.addLog(data_path, meta_path);
    }

    public void checkLog() {
        // Logic for checking log
        CheckLog checkLog = new CheckLog();
        checkLog.checkLog(data_path);
    }

    public void showLog() {
        // Logic for showing log
        ShowLog showLog = new ShowLog();
        showLog.showLog(data_path);
    }

    public void scanLog() {
        // Logic for scanning log
        ScanLog scanLog = new ScanLog();
        scanLog.scanLog(data_path);
    }

    public void reportLog() {
        // Logic for report log
        ReportLog reportLog = new ReportLog();
        reportLog.reportLog(data_path);
    }

    public void deleteLog() {
        // Logic for deleting log
        DeleteLog deleteLog = new DeleteLog();
        deleteLog.deleteLog(data_path);
    }
    
}

