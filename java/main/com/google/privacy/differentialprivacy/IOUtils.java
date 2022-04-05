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

package com.google.privacy.differentialprivacy;

import static java.nio.charset.StandardCharsets.UTF_8;
import static com.google.common.collect.ImmutableSet.toImmutableSet;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** Reads visitors' data and prints statistics. */
class IOUtils {

  private static final String CSV_ITEM_SEPARATOR = ",";
  private static final DateTimeFormatter TIME_FORMATTER =
      new DateTimeFormatterBuilder()
          // case insensitive
          .parseCaseInsensitive()
          // pattern
          .appendPattern("h:mm:ss a")
          // set Locale that uses "AM" and "PM"
          .toFormatter(Locale.ENGLISH);
  private static final String CSV_HOUR_COUNT_WRITE_TEMPLATE = "%s,%d\n";

  private IOUtils() {}

  static ImmutableSet<Ride> readRides(String file) {
    try {
      List<String> rideAsText =
          Resources.readLines(Resources.getResource(file), UTF_8);

      return rideAsText.stream()
          .skip(1)
          .map(IOUtils::convertLineToRide)
          .collect(toImmutableSet());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }


  private static Ride convertLineToRide(String rideAsText) {
    Iterator<String> splitRide = Splitter.on(CSV_ITEM_SEPARATOR).split(rideAsText).iterator();

    String rideId = splitRide.next();

    for(int i = 0; i < 3; i++) {
      splitRide.next();
    }

    String stationId = splitRide.next();

    return Ride.create(rideId, stationId);
  }

  static void writeCountsPerStation(Map<String, Integer> counts, String file) {
    try (PrintWriter pw = new PrintWriter(new File(file), UTF_8.name())) {
      counts.forEach((
          stationId, count) -> pw.write(String.format(CSV_HOUR_COUNT_WRITE_TEMPLATE, stationId, count)));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
