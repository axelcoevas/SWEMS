package com.ipn.swems.code.metrics.calculators;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.tuple.Pair;
import com.ipn.swems.code.input.Method;
import com.ipn.swems.code.input.Type;
import com.ipn.swems.code.metrics.Calculator;
import com.ipn.swems.code.metrics.Metric;
import com.ipn.swems.code.metrics.value.NumericValue;
import com.ipn.swems.code.util.CalculationUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LackOfCohesionMethodsCalculator implements Calculator<Type> {
    @Override
    public Set<Metric> calculate(Type type) {
        List<FieldDeclaration> fieldDeclarations = type.getSource().getFields();
        Set<VariableDeclarator> variables = new HashSet<>();

        fieldDeclarations.stream().map(FieldDeclaration::getVariables).forEach(variables::addAll);

        List<MethodDeclaration> methods = type.getMethods().stream().map(Method::getSource).collect(Collectors.toList());

        NumericValue total = NumericValue.ZERO;

        for (VariableDeclarator variable : variables) {
            int numberOfMethodsAccessingVariable = 0;
            for (MethodDeclaration method : methods) {
                if (CalculationUtils.isFieldAccessedWithinMethod.getUnchecked(Pair.of(method, variable))) {
                    numberOfMethodsAccessingVariable++;
                }
            }

            total = total.plus(NumericValue.of(numberOfMethodsAccessingVariable));
        }


        try {
            NumericValue numberOfMethods = NumericValue.of(methods.size());
            NumericValue numberOfVariables = NumericValue.of(variables.size());

            NumericValue averageNumberOfMethodsAccessingEachVariable = total.divide(numberOfVariables);

            NumericValue numberOfMethodsAsRational = numberOfMethods.divide(NumericValue.ONE);
            NumericValue numerator = averageNumberOfMethodsAccessingEachVariable.minus(numberOfMethodsAsRational);

            NumericValue denominator = NumericValue.ONE.minus(numberOfMethodsAsRational);

            NumericValue lackOfCohesionMethods = numerator.divide(denominator);
            return ImmutableSet.of(Metric.of("LCOM*", "Lack of Cohesion Methods (H-S)", lackOfCohesionMethods));
        } catch (ArithmeticException e) {
            return ImmutableSet.of();
        }


    }

}
