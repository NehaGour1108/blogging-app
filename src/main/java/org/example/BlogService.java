package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BlogService {

    public BlogService() {
        // Create tables in both shards
        createTableInShard(0);
        createTableInShard(1);
    }

    private void createTableInShard(int shardId) {
        DataSource dataSource = DatabaseConnection.getDataSource(shardId);
        try (Connection connection = dataSource.getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS Blog (" +
                    "blogId VARCHAR(255) PRIMARY KEY, " +
                    "userId VARCHAR(255), " +
                    "title VARCHAR(255), " +
                    "content TEXT)";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Table Blog created in shard " + shardId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBlog(int userId, String title, String content) {
        // Get the appropriate shard for the user
        int shardId = ShardManager.getShardForUser(userId);
        DataSource dataSource = DatabaseConnection.getDataSource(shardId);

        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO Blog (blogId, userId, title, content) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                Blog blog = new Blog(String.valueOf(userId), title, content);
                stmt.setString(1, blog.getBlogId());
                stmt.setString(2, blog.getUserId());
                stmt.setString(3, blog.getTitle());
                stmt.setString(4, blog.getContent());
                stmt.executeUpdate();
                System.out.println("Blog created: " + blog.getBlogId() + "shardId " + shardId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
