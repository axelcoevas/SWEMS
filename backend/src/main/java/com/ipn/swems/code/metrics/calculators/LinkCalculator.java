package com.ipn.swems.code.metrics.calculators;

import com.google.common.collect.ImmutableSet;
import com.google.common.graph.Graph;
import com.ipn.swems.code.input.Type;
import com.ipn.swems.code.metrics.Calculator;
import com.ipn.swems.code.metrics.Metric;

import java.util.Set;

public class LinkCalculator implements Calculator<Type> {

    @Override
    public Set<Metric> calculate(Type type) {

        Graph<Type> uses = type.getParentPackage().getParentProject().getMetadata().getClientGraph();

        Set<Type> links = uses.successors(type);

        return ImmutableSet.of(
                Metric.of("NOL", "Number of Links", links.size())
        );
    }
}
