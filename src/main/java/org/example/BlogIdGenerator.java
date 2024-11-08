package org.example;

import java.util.UUID;

public class BlogIdGenerator {
    public static String generateBlogId(String userId) {
        return "BLOG-" + userId + "-" + UUID.randomUUID().toString();
    }
}
