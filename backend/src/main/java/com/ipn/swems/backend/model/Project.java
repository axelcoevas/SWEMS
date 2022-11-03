package com.ipn.swems.backend.model;

import javax.json.JsonObject;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author axel_
 */
@Document(collection = "projects")
public class Project {

    @Id
    private String projectId;
    private String userId;
    private JSONObject results;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public JSONObject getResults() {
        return results;
    }

    public void setResults(JSONObject results) {
        this.results = results;
    }

}
