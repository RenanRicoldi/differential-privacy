package com.google.privacy.differentialprivacy.example;

import com.google.auto.value.AutoValue;

import java.time.LocalDate;
import java.time.LocalTime;

/** Stores data about single Ride of a user. */
@AutoValue
abstract class Ride {

  static Ride create(String id, int stationId, LocalTime startTime, String userType, LocalDate birthYear, Gender gender) {
    return new AutoValue_Ride(id, stationId, startTime, userType, birthYear, gender);
  }

  abstract String id();

  abstract int stationId();

  abstract LocalTime startTime();

  abstract String userType();

  abstract LocalDate birthYear();

  abstract Gender gender();
}
