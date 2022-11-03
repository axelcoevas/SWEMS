package com.ipn.swems.code.metrics.calculators;

import com.google.common.collect.ImmutableSet;
import com.ipn.swems.code.input.Package;
import com.ipn.swems.code.metrics.Calculator;
import com.ipn.swems.code.metrics.Metric;

import java.util.Set;

/**
 * Simply counts the number of classes in a package.  Only counts top-level classes, not inner or anonymous classes.
 *
 * @author Rod Hilton
 * @since 0.3
 */
public class NumberOfClassesCalculator implements Calculator<Package> {

    @Override
    public Set<Metric> calculate(Package aPackage) {
        return ImmutableSet.of(Metric.of("NOC", "Number of Classes", aPackage.getTypes().size()));
    }
}
