package app;

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

    private static void simulateFailure(){
        int a=10;
        int b=0;

        int result=a/b; // This will throw ArithmeticException
        System.out.println("Result: " + result);
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