package com.ipn.swems.code.metrics.calculators;

import com.google.common.collect.ImmutableSet;
import com.ipn.swems.code.input.Type;
import com.ipn.swems.code.metrics.Calculator;
import com.ipn.swems.code.metrics.Metric;
import com.ipn.swems.code.metrics.value.NumericValue;

import java.util.Set;

public class WeightedMethodsCalculator implements Calculator<Type> {
    @Override
    public Set<Metric> calculate(Type method) {
        NumericValue total = method.getMethods().parallelStream().map(m -> m.getMetric("VG").get().getValue()).reduce(NumericValue.ZERO, NumericValue::plus);

        return ImmutableSet.of(Metric.of("WMC", "Weighted methods per Class", total));
    }
}
