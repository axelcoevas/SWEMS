package com.ipn.swems.backend.service;

import java.util.Random;

/**
 *
 * @author axel_
 */
public class ValueGeneratorService {

    public String generateUserId() {
        return "user_" + new Random().nextLong();
    }

    public String generateProjectId() {
        return "project_" + new Random().nextLong();
    }
}
