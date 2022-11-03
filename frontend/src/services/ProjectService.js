import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080/api/v1/projects";

class ProjectService {
    getProjects() {
        return axios.get(USER_API_BASE_URL);
    }

    createProject() {
        return axios.post(USER_API_BASE_URL);
    }

    getProjectById(projectId) {
        return axios.get(USER_API_BASE_URL + "/" + projectId);
    }

    updateProject(project, projectId) {
        return axios.put(USER_API_BASE_URL + "/" + projectId, project);
    }

    deleteProject(projectId) {
        return axios.delete(USER_API_BASE_URL + "/" + projectId);
    }
}

export default new ProjectService();
