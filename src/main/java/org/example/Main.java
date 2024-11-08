package org.example;

public class Main {
    public static void main(String[] args) {
        // Initialize the BlogService which will create tables in both shards
        BlogService blogService = new BlogService();

        // Create blogs for different users
        blogService.createBlog(3, "Blog 1", "This is the second blog content");
        blogService.createBlog(3, "Blog 2", "This is the third blog content");
    }
}
