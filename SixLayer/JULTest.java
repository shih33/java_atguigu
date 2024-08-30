package logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class JULTest {
    private static final Logger logger = Logger.getLogger(JULTest.class.getName());

    public static void main(String[] args) {
        try {
            // Set up FileHandler
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Add handler to logger
            logger.addHandler(fileHandler);

            // Log a message
            logger.severe("This is a severe message");
            logger.warning("This is a warning message");

            // Example of logging an exception
            try {
                throw new IOException("Sample IOException");
            } catch (IOException e) {
                logger.log(java.util.logging.Level.SEVERE, "Exception occurred", e);
            }

        } catch (IOException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error occurred while setting up FileHandler", e);
        }
    }
}

