package com.google.privacy.differentialprivacy.example;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;

import com.google.common.collect.ImmutableSortedMap;
import com.google.privacy.differentialprivacy.Count;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountRidesPerStation {
  private static final String NON_PRIVATE_OUTPUT = "non_private_counts_per_station.csv";
  private static final String PRIVATE_OUTPUT = "private_counts_per_station.csv";

  private static final double LN_3 = Math.log(3);

  private CountRidesPerStation() {}

  public static void run() {
    Collection<Ride> dailyRides = IOUtils.readRides(InputFilePath.RIDE_STATISTICS);

    Map<String, Integer> nonPrivateCounts = getNonPrivateCounts(dailyRides);
    IOUtils.writeCountsPerStation(nonPrivateCounts, NON_PRIVATE_OUTPUT);
    
    Map<String, Integer> privateCounts = getPrivateCounts(dailyRides);
    IOUtils.writeCountsPerStation(privateCounts, PRIVATE_OUTPUT);
  }

  private static String formatKey(int stationId, int hour, Gender gender) {
    return String.format("%07d-%02d-%s", stationId, hour, gender);
  }

  private static ImmutableSortedMap<String, Integer> getNonPrivateCounts(
      Collection<Ride> rides) {
    Map<String, Integer> counts = new TreeMap<>();

    rides.forEach(r -> {
      String key = formatKey(r.stationId(), r.startTime().getHour(), r.gender());

      int numberOfRides = 0;

      if(counts.containsKey(key)) {
        numberOfRides = counts.get(key);
      } else {
        for(Gender gender : Gender.values()) {
          for (int hour = 0; hour < 24; hour++) {
            counts.put(formatKey(r.stationId(), hour, gender), 0);
          }
        }
      }

      counts.put(key, numberOfRides + 1);
    });

    return ImmutableSortedMap.copyOf(counts);
  }

 
  private static ImmutableSortedMap<String, Integer> getPrivateCounts(Collection<Ride> rides) {
    Map<String, Count> dpCounts = new HashMap<>();

    rides.forEach(v -> {
      String key = formatKey(v.stationId(), v.startTime().getHour(), v.gender());

      if(!dpCounts.containsKey(key)) {
        for(Gender gender : Gender.values()) {
          for (int hour = 0; hour < 24; hour++) {
            Count dpCount = Count.builder()
              .epsilon(LN_3)
              .maxPartitionsContributed(1)
              .build();
      
            dpCounts.put(formatKey(v.stationId(), hour, gender), dpCount);
          }
        }
      }

      dpCounts.get(key).increment();
    });

    // Trigger DP logic to produce anonymized counts of rides.
    return ImmutableSortedMap.copyOf(
      dpCounts.entrySet().stream().sorted(Map.Entry.comparingByKey())
        .collect(
            toMap(Map.Entry::getKey, e -> {
              int result = (int) e.getValue().computeResult();

              return result < 0 ? 0 : result;
            })));
  }
}