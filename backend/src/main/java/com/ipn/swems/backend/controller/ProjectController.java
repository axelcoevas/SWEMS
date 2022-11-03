package com.ipn.swems.backend.controller;

import com.ipn.swems.backend.exception.ResourceNotFoundException;
import com.ipn.swems.backend.model.Project;
import com.ipn.swems.backend.repository.ProjectRepository;
import com.ipn.swems.backend.service.ValueGeneratorService;
import com.ipn.swems.code.executive.MetricsService;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author axel_
 */
@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/v1/")
public class ProjectController {

    private ValueGeneratorService valuegeneratorService = new ValueGeneratorService();
    private MetricsService metricsService = new MetricsService();

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) throws FileNotFoundException {
        String filePath = "C:\\Users\\axel_\\Documents\\School\\Trabajo Terminal\\Programas\\Ejemplos\\OnJava8-Examples\\control";
        JSONObject results = metricsService.measureMetrics(filePath);
        project.setProjectId(valuegeneratorService.generateProjectId());
        project.setResults(results);

        return projectRepository.save(project);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));
        return ResponseEntity.ok(project);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project projectDetails) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));

        project.setUserId(projectDetails.getUserId());
        project.setResults(projectDetails.getResults());
        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable String id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));

        projectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
