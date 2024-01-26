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
package io.github.eocqrs.cmig;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import io.github.eocqrs.cmig.meta.Author;
import io.github.eocqrs.cmig.meta.Ids;
import io.github.eocqrs.cmig.meta.Names;
import io.github.eocqrs.cmig.session.Cassandra;
import io.github.eocqrs.cmig.session.InFile;
import io.github.eocqrs.cmig.session.InText;
import io.github.eocqrs.cmig.sha.Sha;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.cactoos.Scalar;
import org.cactoos.io.ResourceOf;
import org.cactoos.list.ListOf;
import org.cactoos.text.TextOf;

/**
 * Master.
 *
 * @since 0.0.0
 * @todo #32:45m/DEV Master is set of states inside XML document.
 * @todo #48:90m/DEV Decompose Master #value().
 * @todo #36:60m/DEV Implement PreparedCql to avoid cql injection.
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
public final class Master implements Scalar<String> {

    /**
     * XML.
     */
    private final XML self;

    /**
     * Cassandra.
     */
    private final Cassandra cassandra;

    /**
     * Ctor.
     *
     * @param doc XML document
     * @param csndra Cassandra
     */
    public Master(final XML doc, final Cassandra csndra) {
        this.self = doc;
        this.cassandra = csndra;
    }

    /**
     * Ctor.
     *
     * @param name XML master name
     * @param csndra Cassandra
     * @throws Exception if something went wrong
     */
    public Master(
        final String name,
        final Cassandra csndra
    ) throws Exception {
        this(
            new XMLDocument(
                new ResourceOf(name).stream()
            ),
            csndra
        );
    }

    @Override
    public String value() {
        final List<String> shas = new ListOf<>();
        new Ids(this.self)
            .value()
            .forEach(
                id -> {
                    final String author = new Author(this.self, id)
                        .asString();
                    final String sha = new Sha(
                        id, this.self
                    ).asString();
                    new InText(
                        new TextOf(
                            "INSERT INTO cmig.states(id, author, sha, seen) "
                            + "VALUES (%s, '%s', '%s', '%s');"
                                .formatted(
                                    id,
                                    author,
                                    sha,
                                    LocalDateTime.now().toInstant(
                                        ZoneOffset.UTC
                                    ).toEpochMilli()
                                )
                        ),
                        this.cassandra
                    ).apply();
                    shas.add(sha);
                    new Names(this.self, id)
                        .value()
                        .forEach(
                            name -> new InFile(
                                this.cassandra, "cmig/%s".formatted(name)
                            ).apply()
                        );
                });
        return shas.get(shas.size() - 1);
    }
}
