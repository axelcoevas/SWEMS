package com.ipn.swems.code.metrics.calculators;

import com.google.common.collect.ImmutableSet;
import com.ipn.swems.code.input.Method;
import com.ipn.swems.code.input.Package;
import com.ipn.swems.code.input.Type;
import com.ipn.swems.code.metrics.Calculator;
import com.ipn.swems.code.metrics.Metric;
import com.ipn.swems.code.metrics.value.NumericValue;
import com.ipn.swems.code.metrics.value.NumericValueSummaryStatistics;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class PackageAggregatorCalculator implements Calculator<Package> {
    @Override
    public Set<Metric> calculate(Package aPackage) {


        Stream<Method> allMethods = aPackage.getTypes().stream().flatMap(t->t.getMethods().stream());
        NumericValueSummaryStatistics stats = methodMetrics(allMethods, "Ci")
                .collect(NumericValue.summarizingCollector());

        Stream<Type> allTypes = aPackage.getTypes().stream();
        NumericValueSummaryStatistics numberOfLinksSummary = typeMetrics(allTypes, "NOL")
                .collect(NumericValue.summarizingCollector());

        NumericValue classCategoricalRelationalCohesion = NumericValue.of(100).times(numberOfLinksSummary.getAverage());

        return ImmutableSet.of(
                Metric.of("PkgTCi", "Package Total System Complexity", stats.getSum()),
                Metric.of("PkgRCi", "Package Relative System Complexity", stats.getAverage()),
                Metric.of("CCRC", "Class Categorical Relational Cohesion", classCategoricalRelationalCohesion)

        );

    }

    private Stream<NumericValue> typeMetrics(Stream<Type> types, String metricName) {
        return types.flatMap(type -> {
            Optional<Metric> metric = type.getMetric(metricName);
            if(metric.isPresent()) {
                return Stream.of(metric.get().getValue());
            } else {
                return Stream.empty();
            }
        });
    }

    private Stream<NumericValue> methodMetrics(Stream<Method> methods, String metricName) {
        return methods.flatMap(type -> {
            Optional<Metric> metric = type.getMetric(metricName);
            if(metric.isPresent()) {
                return Stream.of(metric.get().getValue());
            } else {
                return Stream.empty();
            }
        });
    }
}
