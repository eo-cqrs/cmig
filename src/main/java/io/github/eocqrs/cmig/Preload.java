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

import io.github.eocqrs.cmig.check.CmigKeyspace;
import io.github.eocqrs.cmig.check.StatesTable;
import io.github.eocqrs.cmig.session.Cassandra;
import io.github.eocqrs.cmig.session.InText;
import org.cactoos.Scalar;

/**
 * Preload decorator for {@link Master}.
 *
 * @since 0.0.0
 */
public final class Preload implements Scalar<String> {

    /**
     * Scalar of Master.
     */
    private final Scalar<String> master;

    /**
     * Cassandra.
     */
    private final Cassandra cassandra;

    /**
     * Datacenter.
     */
    private final String datacenter;

    /**
     * Ctor.
     *
     * @param mstr Scalar of Master
     * @param csndra Cassandra
     * @param dcntr Datacenter
     */
    public Preload(
        final Scalar<String> mstr,
        final Cassandra csndra,
        final String dcntr
    ) {
        this.master = mstr;
        this.cassandra = csndra;
        this.datacenter = dcntr;
    }

    @Override
    public String value() throws Exception {
        new InText(
            new CmigKeyspace(this.datacenter),
            this.cassandra
        ).apply();
        new InText(
            new StatesTable(),
            this.cassandra
        ).apply();
        return this.master.value();
    }
}
