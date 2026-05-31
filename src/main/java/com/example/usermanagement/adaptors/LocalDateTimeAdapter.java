package com.example.usermanagement.adaptors;


import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter
        extends TypeAdapter<LocalDateTime> {

    @Override
    public void write(
            JsonWriter out,
            LocalDateTime value
    ) throws IOException {

        if (value == null) {
            out.nullValue();
            return;
        }

        out.value(value.toString());
    }

    @Override
    public LocalDateTime read(
            JsonReader in
    ) throws IOException {

        String value = in.nextString();

        return LocalDateTime.parse(value);
    }
}
