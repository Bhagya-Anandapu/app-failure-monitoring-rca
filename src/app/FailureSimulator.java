package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FailureSimulator {
    private static final String LOG_FILE="logs/application.log";

    public static void main(String[] args) {
        log("INFO","APP","Application started");
        try{
            simulateFailure();
            log("INFO","APP","Execution completed successfully");
        } catch(ArithmeticException e){
            log("ERROR","APPLICATION",e.getMessage());
        } catch(IOException e){
            log("ERROR","FILE_SYSTEM",e.getMessage());
        } catch(SQLException e){
            log("ERROR","DATABASE",e.getMessage());
        } catch(Exception e){
            log("ERROR","UNKNOWN",e.getMessage());
        }
        log("INFO","APP","Application ended");
    }

    private static void simulateFailure() throws Exception {
    String failureType = "DB"; // change this to APP / FILE / DB

    switch (failureType) {
        case "APP":
            simulateAppFailure();
            break;
        case "FILE":
            simulateFileFailure();
            break;
        case "DB":
            retryDbFailure(3);;
            break;
        default:
            throw new RuntimeException("Unknown failure type");
    }
}
    private static void simulateAppFailure() {
    int a = 10;
    int b = 0;
    int result = a / b;
}
private static void simulateFileFailure() throws IOException {
    String filePath = "data/input.txt";
    FileReader reader = new FileReader(filePath);
    reader.close();
}

    private static void simulateDbFailure() throws SQLException{
        String url="jdbc:mysql://localhost:3306/nonexistentdb";
        String user = "root";
        String password="wrongpassword";
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.close();

    }

    private static void retryDbFailure(int maxRetries){

        int attempt=1;

        while(attempt<=maxRetries){
            try{
                log("INFO","DATABASE","DB connrction attempt"+attempt);
                simulateDbFailure();
                log("INFO","DATABASE","DB connection successful");
                return;
            } catch (SQLException e) {
                log("ERROR","DATABASE","ATTEMPT"+attempt+"DB connection failed on attempt " +e.getMessage() );
                attempt++;
            }
        }
        log("ERROR","DATABASE","All retry attempts failed");
    }

    private static void log(String level, String component, String message) {
    try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
        writer.write("{"
                + "\"timestamp\":\"" + LocalDateTime.now() + "\","
                + "\"level\":\"" + level + "\","
                + "\"component\":\"" + component + "\","
                + "\"message\":\"" + message + "\""
                + "}\n");
    } catch (IOException e) {
        System.out.println("Logging failed");
    }
}

}