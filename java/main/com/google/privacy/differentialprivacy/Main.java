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

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    if (args == null || args.length == 0) {
      throw new IllegalArgumentException(
          "The scenario should be set as a first argument. "
              + "Accepted values: "
              + Arrays.toString(Scenario.values()));
    }

    Scenario scenario = Scenario.valueOf(args[0]);
    // TODO: add more examples
    switch (scenario) {
      case COUNT_RIDES_PER_STATION:
        CountRidesPerStation.run();
        break;
    }
  }

  private Main() {}

  enum Scenario {
    COUNT_RIDES_PER_STATION,
  }
}
