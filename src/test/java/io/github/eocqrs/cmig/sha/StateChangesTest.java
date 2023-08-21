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

package io.github.eocqrs.cmig.sha;

import io.github.eocqrs.cmig.meta.Names;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test suite for {@link StateChanges}.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
final class StateChangesTest {

  @Test
  void readsContentsInRightFormat() throws Exception {
    final List<String> value = new StateChanges(
      new Names(
        "cmig/master.xml", "1"
      )
    ).value();
    MatcherAssert.assertThat(
      "Contents in right format",
      value,
      new IsEqual<>(
        new ListOf<>(
          """
            CREATE KEYSPACE queryDatasets
                WITH REPLICATION = {
                    'class' : 'NetworkTopologyStrategy',
                    'datacenter1' : 1
                    };""",
          """
            CREATE TABLE querydatasets.queries
            (
                id    UUID PRIMARY KEY,
                query TEXT,
                seen  TIMESTAMP
            );"""
        )
      )
    );
  }
}
