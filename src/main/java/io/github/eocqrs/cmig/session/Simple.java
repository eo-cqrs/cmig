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
package io.github.eocqrs.cmig.session;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Simple Cassandra.
 *
 * @since 0.0.0
 */
public final class Simple implements Cassandra {

    /**
     * Cluster.
     */
    private final Cluster cluster;

    /**
     * Ctor.
     *
     * @param clstr Cluster
     */
    public Simple(final Cluster clstr) {
        this.cluster = clstr;
    }

    /**
     * Ctor.
     *
     * @param loc Location, a.k.a host
     * @param port Port
     */
    public Simple(final String loc, final int port) {
        this(
            Cluster.builder()
                .addContactPoint(loc)
                .withPort(port)
                .build()
        );
    }

    @Override
    public Session value() {
        return this.cluster.connect();
    }

    @Override
    public void close() {
        this.cluster.close();
    }
}
