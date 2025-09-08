package com.example.Ecommerce;

import com.example.Ecommerce.events.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EventLogger {

    private static final String FILE_PATH = "events.ndjson";
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static void logEvent(Event event) {
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created events.ndjson at: " + file.getAbsolutePath());
            }

            String jsonEvent = mapper.writeValueAsString(event);

            try (FileWriter fw = new FileWriter(file, true)) {
                fw.write(jsonEvent + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
