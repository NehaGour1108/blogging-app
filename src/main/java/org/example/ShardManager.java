package org.example;

public class ShardManager {

    // Simple logic for sharding: modulo based on userId
    public static int getShardForUser(int userId) {
        return userId % 2; // 2 shards: 0 and 1
    }
}
