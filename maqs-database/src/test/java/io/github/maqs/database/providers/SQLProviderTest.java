/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.database.providers;

import io.github.maqs.base.BaseGenericTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SQLProviderTest extends BaseGenericTest {

  @Test
  public void testGetDataSource() {
    SQLProvider sqlProvider = new SQLProvider("http://127.0.0.1");
    Assert.assertNotNull(sqlProvider.getDataSource());
  }

  @Test
  public void testGetDialect() {
    SQLProvider sqlProvider = new SQLProvider("http://127.0.0.1");
    Assert.assertEquals(sqlProvider.getDialect(), "org.hibernate.dialect.SQLServerDialect");
  }
}