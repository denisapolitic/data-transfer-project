package org.datatransferproject.denisapolitic;

import org.datatransferproject.api.ApiMain;
import org.datatransferproject.api.launcher.Monitor;
import org.datatransferproject.transfer.WorkerMain;

import static org.datatransferproject.launcher.monitor.MonitorLoader.loadMonitor;

public class Main {
    public static void main(String[] args) {
//        WorkerMain workerMain = new WorkerMain();
//        workerMain.initialize();
        Monitor monitor = loadMonitor();
        ApiMain apiMain = new ApiMain(monitor);
        apiMain.initializeHttp();
        apiMain.start();
    }
}
