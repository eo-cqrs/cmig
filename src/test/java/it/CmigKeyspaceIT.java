/*
 *  Copyright (c) 2023 Aliaksei Bialiauski, EO-CQRS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it;

import io.github.eocqrs.cmig.check.CmigKeyspace;
import io.github.eocqrs.cmig.check.StatesTable;
import io.github.eocqrs.cmig.session.Cassandra;
import io.github.eocqrs.cmig.session.InText;
import io.github.eocqrs.cmig.session.Simple;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

/**
 * Integration test for {@link CmigKeyspace} and {@link StatesTable}.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
final class CmigKeyspaceIT extends CassandraIntegration {

  @Test
  void createsCmigKeyspaceAndTable() throws Exception {
    final Cassandra cassandra = new Simple(
      CassandraIntegration.HOST,
      CassandraIntegration.CASSANDRA.getMappedPort(9042)
    );
    new InText(
      new CmigKeyspace("1"),
      cassandra
    ).apply();
    new InText(
      new StatesTable(),
      cassandra
    ).apply();
    MatcherAssert.assertThat(
      "Query to CMIG keyspace applied",
      cassandra.value().execute(
        "SELECT * FROM cmig.states"
      ).wasApplied(),
      new IsEqual<>(true)
    );
  }
}
