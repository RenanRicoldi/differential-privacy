package com.google.privacy.differentialprivacy;

import com.google.auto.value.AutoValue;
import java.time.LocalTime;

/** Stores data about single Ride of a user. */
@AutoValue
abstract class Ride {

  static Ride create(String rideId, String stationId) {
    return new AutoValue_Ride(rideId, stationId);
  }

  abstract String rideId();

  abstract String stationId();
}
