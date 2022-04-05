package com.google.privacy.differentialprivacy;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toMap;

import com.google.common.collect.ImmutableSortedMap;
import com.google.privacy.differentialprivacy.Count;
import java.util.Collection;
import java.util.HashMap;
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
    // Map<Integer, Integer> privateCounts = getPrivateCounts(dailyRides);

    IOUtils.writeCountsPerStation(nonPrivateCounts, NON_PRIVATE_OUTPUT);
    // IOUtils.writeCountsPerStation(privateCounts, PRIVATE_OUTPUT);
  }

  private static ImmutableSortedMap<String, Integer> getNonPrivateCounts(
      Collection<Ride> rides) {
    Map<String, Integer> counts = new TreeMap<>();

    rides.forEach(r -> {
      String station = r.stationId();
      // Validate that the visit happened during one of valid hours.
      int numberOfRides = counts.get(station);
      int newCount = numberOfRides > 0 ? numberOfRides + 1 : 1;
      counts.put(station, newCount);
    });

    return ImmutableSortedMap.copyOf(counts);
  }

 
  // private static ImmutableSortedMap<Integer, Integer> getPrivateCounts(Collection<Ride> visits) {
  //   // Construct DP Count objects which will be used to calculate DP counts
  //   // one Count is created for every work hour.
  //   Map<Integer, Count> dpCounts = new HashMap<>();
  //   for (int i = OPENING_HOUR; i <= CLOSING_HOUR; i++) {
  //     Count dpCount = Count.builder()
  //         .epsilon(LN_3)
  //         .maxPartitionsContributed(1)
  //         .build();
  //     dpCounts.put(i, dpCount);
  //   }

  //   // Go through all visits and update Counts at the corresponding hours.
  //   visits.forEach(v -> dpCounts.get(v.entryTime().getHour()).increment());

  //   // Trigger DP logic to produce anonymized counts of visits.
  //   return ImmutableSortedMap.copyOf(
  //       dpCounts.entrySet().stream()
  //       .collect(
  //           toMap(Map.Entry::getKey, e -> (int) e.getValue().computeResult())));
  // }
}