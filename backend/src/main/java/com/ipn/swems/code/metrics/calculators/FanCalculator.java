package com.ipn.swems.code.metrics.calculators;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.type.VoidType;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.Network;
import com.ipn.swems.code.input.Method;
import com.ipn.swems.code.metrics.Calculator;
import com.ipn.swems.code.metrics.Metric;
import com.ipn.swems.code.metrics.value.NumericValue;
import com.ipn.swems.code.util.Distinct;

import java.util.Set;

public class FanCalculator implements Calculator<Method> {

    @Override
    public synchronized Set<Metric> calculate(Method method) {

        Network<Method, Distinct<Expression>> methodCalls = method.getParentType().getParentPackage().getParentProject().getMetadata().getCallNetwork();

        Set<Method> methodsCalled = methodCalls.successors(method);

        int fanOut = 0;

        for (Method methodCalled : methodsCalled) {
            Set<Distinct<Expression>> calls = methodCalls.edgesConnecting(method, methodCalled);
            fanOut += calls.size();
        }

        Set<Method> methodsCalling = methodCalls.predecessors(method);

        int fanIn = 0;

        for (Method methodCalling : methodsCalling) {
            Set<Distinct<Expression>> calls = methodCalls.edgesConnecting(methodCalling, method);
            fanIn += calls.size();
        }

        int returns = method.getSource().getType() instanceof VoidType ? 0 : 1;
        int parameters = method.getSource().getParameters().size();
        int iovars = parameters + returns;

        NumericValue dataComplexity = NumericValue.of(iovars).divide(NumericValue.ONE.plus(NumericValue.of(fanOut)));
        NumericValue structuralComplexity = NumericValue.of(fanOut).pow(2);
        NumericValue systemComplexity = dataComplexity.plus(structuralComplexity.divide(NumericValue.ONE));

        return ImmutableSet.of(
                Metric.of("Fout", "Fan-out", fanOut),
                Metric.of("Fin", "Fan-in", fanIn),
                Metric.of("Si", "Structural Complexity", structuralComplexity),
                Metric.of("IOVars", "Input/Output Variables", iovars),
                Metric.of("Di", "Data Complexity", dataComplexity),
                Metric.of("Ci", "System Complexity", systemComplexity)
        );


    }

}
