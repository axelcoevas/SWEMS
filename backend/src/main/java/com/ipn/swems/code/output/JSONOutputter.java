/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.swems.code.output;

import com.ipn.swems.code.input.Code;
import com.ipn.swems.code.input.Method;
import com.ipn.swems.code.input.Project;
import com.ipn.swems.code.input.Type;
import com.ipn.swems.code.input.Package;
import com.ipn.swems.code.metrics.Metric;
import org.w3c.dom.Document;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.*;
import javax.lang.model.element.Element;

/**
 *
 * @author axel_
 */
public class JSONOutputter {

    public JSONObject output(Project project) {
        JSONObject doc = new JSONObject();

        JSONObject projectBuilder = new JSONObject();

        addAttributes(project, projectBuilder);
        addMetricsForNode(project, projectBuilder);

        JSONArray packagesArray = new JSONArray();

        for (Package packageNode : sortChildren(project.getPackages())) {
            JSONObject packageElement = new JSONObject();
            packageElement.put("name", packageNode.getName());

            addAttributes(packageNode, packageElement);
            addMetricsForNode(packageNode, packageElement);

            JSONArray classesArray = new JSONArray();

            for (Type classNode : sortChildren(packageNode.getTypes())) {
                JSONObject classElement = new JSONObject();
                classElement.put("name", classNode.getName());

                addAttributes(classNode, classElement);
                addMetricsForNode(classNode, classElement);

                JSONArray methodsArray = new JSONArray();

                for (Method methodNode : sortChildren(classNode.getMethods())) {
                    JSONObject methodElement = new JSONObject();
                    methodElement.put("name", methodNode.getName());

                    addAttributes(methodNode, methodElement);
                    addMetricsForNode(methodNode, methodElement);

                    methodsArray.add(methodElement);
                }
                classElement.put("Methods", methodsArray);
                classesArray.add(classElement);
            }
            packageElement.put("Classes", classesArray);
            packagesArray.add(packageElement);
        }

        doc.put("Packages", packagesArray);
        doc.put("Project", projectBuilder);

        return doc;
    }

    private <T extends Code> List<T> sortChildren(Collection<T> children) {
        return children.stream().sorted(new Comparator<Code>() {
            @Override
            public int compare(Code o1, Code o2) {
                return o1.getName().compareTo(o2.getName());
            }

        }).collect(Collectors.toList());
    }

    private void addAttributes(Code classNode, JSONObject object) {
        for (Map.Entry<String, String> attribute : classNode.getAttributes().entrySet()) {
            object.put(attribute.getKey(), attribute.getValue());
        }
    }

    private void addMetricsForNode(Code node, JSONObject object) {
        JSONArray metricsContainer = new JSONArray();

        Set<Metric> metrics = node.getMetrics();
        List<Metric> sortedMetrics = metrics.stream().sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                .collect(Collectors.toList());
        for (Metric metric : sortedMetrics) {
            JSONObject metricsElement = new JSONObject();

            metricsElement.put("name", metric.getName());
            metricsElement.put("description", metric.getDescription());
            metricsElement.put("value", metric.getFormattedValue());

            metricsContainer.add(metricsElement);
        }
        object.put("Metrics", metricsContainer);
    }
}
