package com.ipn.swems.code.output;

import com.ipn.swems.code.input.Project;

public interface Outputter<T> {
    T output(Project project);
}
