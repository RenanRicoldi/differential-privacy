#
# Copyright 2019 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

package(
    default_visibility = [
        "//algorithms:__subpackages__",
    ],
)

cc_library(
    name = "algorithm",
    hdrs = ["algorithm.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "algorithm_test",
    size = "small",
    srcs = ["algorithm_test.cc"],
    deps = [
        ":algorithm",
        "//base/testing:status_matchers",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_test(
    name = "algorithm-stochastic-dp_test",
    timeout = "eternal",
    srcs = ["algorithm-stochastic-dp_test.cc"],
    shard_count = 16,
    deps = [
        ":approx-bounds",
        ":bounded-mean",
        ":bounded-standard-deviation",
        ":bounded-sum",
        ":bounded-variance",
        ":count",
        ":numerical-mechanisms-testing",
        ":order-statistics",
        ":util",
        "//testing:sequence",
        "//testing:stochastic_tester",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "binary-search",
    hdrs = ["binary-search.h"],
    deps = [
        ":algorithm",
        ":numerical-mechanisms",
        "//base:percentile",
        "//proto:util-lib",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "binary-search_test",
    size = "small",
    srcs = ["binary-search_test.cc"],
    deps = [
        ":algorithm",
        ":binary-search",
        ":numerical-mechanisms",
        ":numerical-mechanisms-testing",
        ":util",
        "//base/testing:status_matchers",
        "//proto:util-lib",
        "@com_google_absl//absl/random:distributions",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "order-statistics",
    hdrs = ["order-statistics.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":binary-search",
        ":bounded-algorithm",
        ":numerical-mechanisms",
        "//base:percentile",
        "@com_google_absl//absl/base:core_headers",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "order-statistics_test",
    size = "small",
    srcs = ["order-statistics_test.cc"],
    deps = [
        ":numerical-mechanisms-testing",
        ":order-statistics",
        ":util",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/random:distributions",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "bounded-sum",
    hdrs = ["bounded-sum.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        ":bounded-algorithm",
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "bounded-sum_test",
    size = "small",
    srcs = ["bounded-sum_test.cc"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        ":bounded-sum",
        ":numerical-mechanisms",
        ":numerical-mechanisms-testing",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "bounded-mean",
    hdrs = ["bounded-mean.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        ":bounded-algorithm",
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "bounded-mean_test",
    size = "small",
    srcs = ["bounded-mean_test.cc"],
    deps = [
        ":approx-bounds",
        ":bounded-mean",
        ":numerical-mechanisms",
        ":numerical-mechanisms-testing",
        "//base:logging",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "bounded-variance",
    hdrs = ["bounded-variance.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        ":bounded-algorithm",
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "bounded-variance_test",
    size = "small",
    srcs = ["bounded-variance_test.cc"],
    deps = [
        ":approx-bounds",
        ":bounded-variance",
        ":numerical-mechanisms",
        ":numerical-mechanisms-testing",
        "//base:logging",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/random:distributions",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "bounded-standard-deviation",
    hdrs = ["bounded-standard-deviation.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        ":bounded-algorithm",
        ":bounded-variance",
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "bounded-standard-deviation_test",
    size = "small",
    srcs = ["bounded-standard-deviation_test.cc"],
    deps = [
        ":approx-bounds",
        ":bounded-standard-deviation",
        ":distributions",
        ":numerical-mechanisms-testing",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/random:distributions",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "count",
    hdrs = ["count.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "count_test",
    size = "small",
    srcs = ["count_test.cc"],
    deps = [
        ":count",
        ":numerical-mechanisms",
        ":numerical-mechanisms-testing",
        "//base:logging",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_differential_privacy//proto:data_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "util",
    srcs = ["util.cc"],
    hdrs = ["util.h"],
    visibility = [
        "//testing:__pkg__",
    ],
    deps = [
        "//base:logging",
        "@com_google_absl//absl/base:core_headers",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "util_test",
    size = "small",
    srcs = ["util_test.cc"],
    deps = [
        ":distributions",
        ":numerical-mechanisms-testing",
        ":util",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/status",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "distributions",
    srcs = ["distributions.cc"],
    hdrs = ["distributions.h"],
    visibility = ["//visibility:private"],
    deps = [
        ":rand",
        ":util",
        "//base:logging",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/random",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "distributions_test",
    size = "medium",
    srcs = ["distributions_test.cc"],
    shard_count = 4,
    deps = [
        ":distributions",
        ":numerical-mechanisms-testing",
        ":util",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/strings",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "numerical-mechanisms",
    hdrs = ["numerical-mechanisms.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":distributions",
        ":rand",
        ":util",
        "//base:logging",
        "@com_google_absl//absl/base:core_headers",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_absl//absl/strings",
        "@com_google_absl//absl/types:optional",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_differential_privacy//proto:numerical_mechanism_cc_proto",
    ],
)

cc_test(
    name = "numerical-mechanisms_test",
    size = "medium",
    srcs = ["numerical-mechanisms_test.cc"],
    deps = [
        ":distributions",
        ":numerical-mechanisms",
        "//base/testing:status_matchers",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "numerical-mechanisms-testing",
    testonly = 1,
    hdrs = ["numerical-mechanisms-testing.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":distributions",
        ":numerical-mechanisms",
        "//base:logging",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/random",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_googletest//:gtest",
    ],
)

cc_test(
    name = "numerical-mechanisms-testing_test",
    size = "small",
    srcs = ["numerical-mechanisms-testing_test.cc"],
    deps = [
        ":numerical-mechanisms-testing",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "approx-bounds",
    hdrs = ["approx-bounds.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":numerical-mechanisms",
        ":util",
        "//base:logging",
        "//proto:util-lib",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "approx-bounds_test",
    size = "small",
    srcs = ["approx-bounds_test.cc"],
    deps = [
        ":approx-bounds",
        ":numerical-mechanisms",
        ":numerical-mechanisms-testing",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/memory",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "bounded-algorithm",
    hdrs = ["bounded-algorithm.h"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "bounded-algorithm_test",
    size = "small",
    srcs = ["bounded-algorithm_test.cc"],
    deps = [
        ":algorithm",
        ":approx-bounds",
        ":bounded-algorithm",
        "//base/testing:status_matchers",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "rand",
    srcs = ["rand.cc"],
    hdrs = ["rand.h"],
    visibility = [
        "//testing:__pkg__",
    ],
    deps = [
        "//base:logging",
        "@boringssl//:crypto",
        "@com_google_absl//absl/base:core_headers",
        "@com_google_absl//absl/numeric:bits",
        "@com_google_absl//absl/synchronization",
    ],
)

cc_test(
    name = "rand_test",
    size = "small",
    srcs = ["rand_test.cc"],
    deps = [
        ":rand",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_test(
    name = "distributions_benchmark_test",
    timeout = "long",
    srcs = ["distributions_benchmark_test.cc"],
    deps = [
        ":distributions",
        "@com_google_absl//absl/strings:str_format",
        "@com_google_benchmark//:benchmark_main",
    ],
)

cc_library(
    name = "partition-selection",
    hdrs = ["partition-selection.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":distributions",
        ":numerical-mechanisms",
        ":rand",
        ":util",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "partition-selection_test",
    srcs = ["partition-selection_test.cc"],
    shard_count = 2,
    deps = [
        ":numerical-mechanisms-testing",
        ":partition-selection",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "quantile-tree",
    hdrs = ["quantile-tree.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":bounded-algorithm",
        ":numerical-mechanisms",
        "//algorithms/internal:count-tree",
        "//proto:util-lib",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:status",
        "@com_google_cc_differential_privacy//base:statusor",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_differential_privacy//proto:summary_cc_proto",
    ],
)

cc_test(
    name = "quantile-tree_test",
    timeout = "eternal",
    srcs = ["quantile-tree_test.cc"],
    shard_count = 16,
    deps = [
        ":numerical-mechanisms-testing",
        ":quantile-tree",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/random",
        "@com_google_differential_privacy//proto:confidence_interval_cc_proto",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_library(
    name = "quantiles",
    hdrs = ["quantiles.h"],
    visibility = ["//visibility:public"],
    deps = [
        ":algorithm",
        ":bounded-algorithm",
        ":quantile-tree",
        "@com_google_absl//absl/status",
        "@com_google_cc_differential_privacy//base:statusor",
    ],
)

cc_test(
    name = "quantiles_test",
    srcs = ["quantiles_test.cc"],
    deps = [
        ":numerical-mechanisms-testing",
        ":quantiles",
        "//base/testing:proto_matchers",
        "//base/testing:status_matchers",
        "@com_google_absl//absl/random",
        "@com_google_googletest//:gtest_main",
    ],
)
