package com.ipn.swems.code.metrics;

import com.ipn.swems.code.input.Code;
import com.ipn.swems.code.util.ProjectMetadata;

import java.util.Set;

public interface Calculator<T extends Code> {

    Set<Metric> calculate(T t);

}
