/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023-2024 Aliaksei Bialiauski, EO-CQRS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
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
import java.util.List;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

/**
 * Test suite for {@link StateChanges}.
 *
 * @since 0.0.0
 * @checkstyle StringLiteralsConcatenationCheck (40 lines)
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
                    "CREATE KEYSPACE queryDatasets\n"
                    + "    WITH REPLICATION = {\n"
                    + "        'class' : 'NetworkTopologyStrategy',\n"
                    + "        'datacenter1' : 1\n"
                    + "        };",
                    "CREATE TABLE querydatasets.queries\n"
                    + "(\n"
                    + "    id    UUID PRIMARY KEY,\n"
                    + "    query TEXT,\n"
                    + "    seen  TIMESTAMP\n"
                    + ");"
                )
            )
        );
    }
}
