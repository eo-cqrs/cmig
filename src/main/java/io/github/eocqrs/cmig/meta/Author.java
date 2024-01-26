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
package io.github.eocqrs.cmig.meta;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.cactoos.Text;
import org.cactoos.io.ResourceOf;

/**
 * Author of State.
 *
 * @since 0.0.0
 */
public final class Author implements Text {

    /**
     * XML.
     */
    private final XML xml;

    /**
     * State ID.
     */
    private final String identifier;

    /**
     * Ctor.
     *
     * @param doc XML
     * @param idn State ID
     */
    public Author(final XML doc, final String idn) {
        this.xml = doc;
        this.identifier = idn;
    }

    /**
     * Ctor.
     *
     * @param name File name
     * @param idn State ID
     * @throws Exception if something went wrong
     */
    public Author(final String name, final String idn)
        throws Exception {
        this(
            new XMLDocument(
                new ResourceOf(
                    name
                ).stream()
            ),
            idn
        );
    }

    @Override
    public String asString() {
        return this.xml.xpath(
            "/states/changeState[@id='%s']/@author"
                .formatted(
                    this.identifier
                )
        ).get(0);
    }
}
