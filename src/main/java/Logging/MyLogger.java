package Logging;

import java.io.File;
import java.io.IOException;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {
    private static MyLogger myLogger;
    private static int threshold;
    private static boolean printToConsole;
    private static boolean printToConsoleTemp;

    private MyLogger() {
        printToConsole = false;
        printToConsoleTemp = false;
        threshold = 3;
    }

    public static MyLogger getMyLogger(){
        if(myLogger == null) {
            myLogger = new MyLogger();
        }
        return myLogger;
    }

    public void writeLog(String message, int level) {
        try (FileWriter fileWriter = new FileWriter(getLogFileName(), true)){
            String logEntry = formatLogEntry(message);

            if(level >= threshold) {
                fileWriter.write(logEntry);
            }

            if(printToConsole || printToConsoleTemp) {
                System.out.println(logEntry);
                printToConsoleTemp = false;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getLogFileName() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return "D:\\Programming Files\\Revature\\Erika-Kollier\\src\\main\\resources\\logs\\"+ today + ".log";
    }

    private String formatLogEntry(String message){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String stackInfo = stackTraceElements[3].toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("%s  [%s]  %s%n", timestamp, stackInfo, message);
    }

    public MyLogger console() {
        printToConsoleTemp = true;
        return myLogger;
    }

    public MyLogger threshold(int th) {
        threshold = th;
        return myLogger;
    }

    public static boolean isPrintToConsole() {
        return printToConsole;
    }

    public static void setPrintToConsole(boolean printToConsole) {
        MyLogger.printToConsole = printToConsole;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        MyLogger.threshold = threshold;
    }
}