
# Blog Sharding Example

This project demonstrates a basic implementation of **sharding** using MySQL and Java. It simulates a simple blog system where blog posts are stored in two separate database shards based on the user ID. The system ensures that each blog has a unique ID and manages the distribution of data across multiple databases.

<img width="677" alt="Screenshot 2024-11-29 at 9 43 33 PM" src="https://github.com/user-attachments/assets/043b531f-1eb2-40bd-bed5-f2e4f405d999">

## Features

- **Sharded Database**: The blog data is partitioned across two shards based on the user ID.
- **UUID Generation**: Unique blog IDs are generated using a combination of user ID and a random UUID.
- **In-Memory H2 Databases**: The project uses H2 as an in-memory database for simulating two separate shards.
- **Basic CRUD Operations**: It supports the creation of blogs, ensuring data is inserted into the correct shard based on the user ID.

## Project Structure

- **`Blog.java`**: Defines the Blog class, with properties like `blogId`, `userId`, `title`, and `content`. It also generates a unique `blogId`.
- **`BlogIdGenerator.java`**: Contains the method `generateBlogId` to generate a unique blog ID by combining the `userId` and a random UUID.
- **`BlogService.java`**: Handles blog creation. It selects the correct shard based on the user ID and inserts blog data into the appropriate shard.
- **`DatabaseConnection.java`**: Manages database connections for the two shards.
- **`ShardManager.java`**: Contains the logic for determining which shard a blog should be stored in based on the user ID (simple modulo-based logic).
- **`Main.java`**: The entry point for the application, where blog creation is triggered and shard setup is initialized.

## Getting Started

To run this project locally, follow these steps:

### Prerequisites

Make sure you have the following installed on your machine:

- **Java 8 or higher**.
- **Maven** to build the project.

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/blog-sharding-example.git
   cd blog-sharding-example
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn exec:java
   ```

   This will initialize the `BlogService` and create blog posts for different users, stored across multiple shards.

## How It Works

1. **Sharding**: The project uses simple **modulo-based sharding**. For example, `userId % 2` determines whether the data will be stored in shard 0 or shard 1.
2. **Blog Creation**: When you create a blog for a user, the `BlogService` determines the correct shard and inserts the blog into the corresponding database.
3. **UUID Generation**: A unique `blogId` is created for every blog post by combining the `userId` with a randomly generated UUID.

## Example Usage

When you run the application, it will automatically create blogs for two users with the following details:

- **User ID 3** creates "Blog 1" and "Blog 2", which will be stored in **Shard 1**.
- The `blogId` for each blog will be a combination of the `userId` and a random UUID.

Output Example:
```plaintext
Table Blog created in shard 0
Table Blog created in shard 1
Blog created: BLOG-3-abc123 in shardId 1
Blog created: BLOG-3-def456 in shardId 1
```

## Technologies Used

- **Java 8**: The project is built using Java 8 for creating the backend logic.
- **H2 Database**: In-memory H2 databases simulate the sharded environment.
- **Apache Commons DBCP**: Used for managing database connections.
- **Maven**: For building and running the project.
