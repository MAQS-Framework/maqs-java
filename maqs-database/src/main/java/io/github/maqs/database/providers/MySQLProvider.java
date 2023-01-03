/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.database.providers;

import javax.sql.DataSource;

public class MySQLProvider implements IDataSourceProvider {
  public MySQLProvider(String connectionString) {

  }

  @Override
  public DataSource getDataSource() {
    return null;
  }

  @Override
  public String getDialect() {
    return null;
  }
}
