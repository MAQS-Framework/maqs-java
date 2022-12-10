/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package com.maqs.database.providers;

import com.maqs.database.DatabaseConfig;
import com.maqs.database.constants.DataProviderType;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
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
