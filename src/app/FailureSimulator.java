package app;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FailureSimulator {
    private static final String LoG_FILE="logs/application.log";

    public static void main(String[] args) {
        log("Application started");
        try {
            simulateFailure();
        } catch (Exception e) {
            log("Error: " + e.getClass().getSimpleName()+" - " + e.getMessage());
            System.out.println("Application crashed.Check logs for details.");
        }
        log("Application ended");
    }

    private static void simulateFailure() throws IOException{
        readFile();
    }

    private static void readFile() throws IOException{
        String filePath="data/input.txt";
        FileReader reader=new FileReader(filePath);
        reader.close();
    }

    private static void log(String message){
        try(FileWriter writer=new FileWriter(LoG_FILE,true)){
            String timeStamp= LocalDateTime.now().toString();
            writer.write(timeStamp + " - " + message + "\n");
        } catch (IOException e) {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }
}