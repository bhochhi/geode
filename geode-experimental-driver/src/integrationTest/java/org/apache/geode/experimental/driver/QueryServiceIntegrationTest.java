/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.geode.experimental.driver;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import org.apache.geode.test.junit.categories.ClientServerTest;

@Category({ClientServerTest.class})
public class QueryServiceIntegrationTest extends IntegrationTestBase {

  @Test
  public void testQuery() throws IOException {
    serverRegion.put("key1", "value1");
    serverRegion.put("key2", "value2");

    QueryService service = driver.getQueryService();

    Query<String> query = service.newQuery("select value from /region value order by value");
    final List<String> results = query.execute();

    assertEquals(Arrays.asList("value1", "value2"), results);
  }
}
