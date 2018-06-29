package com.AlexBoldi.SimplePokerTracker.Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileWriter {
    public static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //private static final String FILE_HEADER = "";

    public static void writeCsvFile(List<PokerSession> pokerSessions, String filename) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            for (PokerSession s : pokerSessions) {
                fileWriter.append(String.valueOf(s.getPokerSessionDate()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(s.getPokerSessionResult()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            System.out.println("CSV file was created successfully");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
            }
        }
    }

}
