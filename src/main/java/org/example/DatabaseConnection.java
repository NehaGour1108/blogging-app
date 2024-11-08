package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {
    private static final Map<Integer, DataSource> shardDataSources = new HashMap<>();

    static {
        // Shard 1 configuration for H2
        BasicDataSource ds1 = new BasicDataSource();
        ds1.setUrl("jdbc:h2:mem:db_shard1;DB_CLOSE_DELAY=-1;MODE=MYSQL");
        ds1.setUsername("sa");
        ds1.setPassword("");
        shardDataSources.put(0, ds1);

        // Shard 2 configuration for H2
        BasicDataSource ds2 = new BasicDataSource();
        ds2.setUrl("jdbc:h2:mem:db_shard2;DB_CLOSE_DELAY=-1;MODE=MYSQL");
        ds2.setUsername("sa");
        ds2.setPassword("");
        shardDataSources.put(1, ds2);
    }

    public static DataSource getDataSource(int shardId) {
        return shardDataSources.get(shardId);
    }
}
