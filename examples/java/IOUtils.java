//
// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package com.google.privacy.differentialprivacy.example;

import static java.nio.charset.StandardCharsets.UTF_8;
import static com.google.common.collect.ImmutableSet.toImmutableSet;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** Reads visitors' data and prints statistics. */
class IOUtils {

  private static final String CSV_ITEM_SEPARATOR = ",";
  private static final String CSV_HOUR_COUNT_WRITE_TEMPLATE = "%s,%d\n";

  private IOUtils() {}

  static ImmutableSet<Ride> readRides(String fileName) {
    try {
      List<String> rideAsText =
          Resources.readLines(Resources.getResource(fileName), UTF_8);

      return rideAsText.stream()
        .skip(1)
        .map(IOUtils::convertLineToRide)
        .collect(toImmutableSet());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**      0             1           2            3                   4                      5                    6                        7                    8                 9                    10                    11       12          13        14
   * starttime","start station id","start station name",,"bikeid","usertype","birth year","gender"
   * -73.99400398,35305,"Subscriber",1996,2
   */

  private static Ride convertLineToRide(String rideAsText) {
    Iterator<String> splitRide = Splitter.on(CSV_ITEM_SEPARATOR).split(rideAsText).iterator();

    String station = splitRide.next();
    int stationId = Integer.parseInt(station.equals("NULL") ? "0" : station);

    LocalTime startedAt = LocalTime.parse(splitRide.next() + ":00:00");

    Gender gender = splitRide.next().equals("MALE") ? Gender.MALE : Gender.FEMALE;

    String userType = splitRide.next();

    LocalDate birthYear = LocalDate.of(Integer.parseInt(splitRide.next()), 1, 1);

    Ride ride = Ride.create(
      splitRide.toString(),
      stationId,
      startedAt,
      userType,
      birthYear,
      gender
    );

    System.out.println(ride.id());

    return ride;
  }

  static void writeCountsPerStation(Map<String, Integer> counts, String fileName) {
    try (PrintWriter pw = new PrintWriter(new File(fileName), UTF_8.name())) {
      pw.write("identifier,count\n");
      counts.forEach((
          stationId, count) -> pw.write(String.format(CSV_HOUR_COUNT_WRITE_TEMPLATE, stationId, count)));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
