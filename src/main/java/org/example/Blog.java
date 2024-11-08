package org.example;

public class Blog {
    private String blogId;
    private String userId;
    private String title;
    private String content;

    public Blog(String userId, String title, String content) {
        this.blogId = BlogIdGenerator.generateBlogId(userId);
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public String getBlogId() {
        return blogId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
