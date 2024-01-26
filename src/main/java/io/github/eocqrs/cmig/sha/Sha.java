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

import com.jcabi.xml.XML;
import io.github.eocqrs.cmig.meta.Names;
import lombok.SneakyThrows;
import org.cactoos.Text;
import org.cactoos.bytes.Sha256DigestOf;
import org.cactoos.io.InputOf;
import org.cactoos.text.HexOf;
import org.cactoos.text.Normalized;

/**
 * SHA256 for State.
 *
 * @since 0.0.0
 */
public final class Sha implements Text {

    /**
     * State ID.
     */
    private final String identifier;

    /**
     * Master XML.
     */
    private final XML master;

    /**
     * Ctor.
     *
     * @param idn State ID
     * @param mst Master XML
     */
    public Sha(
        final String idn,
        final XML mst
    ) {
        this.identifier = idn;
        this.master = mst;
    }

    @SneakyThrows
    @Override
    public String asString() {
        return new HexOf(
            new Sha256DigestOf(
                new InputOf(
                    new Normalized(
                        new StateChanges(
                            new Names(
                                this.master,
                                this.identifier
                            )
                        ).value().toString()
                    )
                )
            )
        ).asString();
    }
}
