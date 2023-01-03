/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.database.providers;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import io.github.maqs.database.DatabaseConfig;
import io.github.maqs.database.constants.DataProviderType;
import javax.sql.DataSource;

public class SQLProvider implements IDataSourceProvider {

  private static final DataProviderType dataProviderType = DataProviderType.SQL;

  public SQLProvider(String connectionString) {

  }

  @Override
  public DataSource getDataSource() {
    SQLServerDataSource dataSource = new SQLServerDataSource();
    dataSource.setPassword(DatabaseConfig.getDatabasePassword());
    dataSource.setURL(DatabaseConfig.getConnectionString());
    dataSource.setUser(DatabaseConfig.getDatabaseUser());
    dataSource.setDatabaseName(DatabaseConfig.getDatabaseName());
    return dataSource;
  }

  @Override
  public String getDialect() {
    return dataProviderType.getDialectString();
  }
}
