package com.example.usermanagement.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.usermanagement.adaptors.LocalDateAdapter;
import com.example.usermanagement.adaptors.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static final Gson gson = new GsonBuilder()

            .registerTypeAdapter(
                    LocalDate.class,
                    new LocalDateAdapter())

            .registerTypeAdapter(
                    LocalDateTime.class,
                    new LocalDateTimeAdapter())

            .create();
}