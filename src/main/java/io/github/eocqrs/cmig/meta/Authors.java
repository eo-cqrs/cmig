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

package io.github.eocqrs.cmig.meta;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.cactoos.Scalar;
import org.cactoos.io.ResourceOf;

/**
 * Authors of State.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class Authors implements Scalar<String> {

  /**
   * XML.
   */
  private final XML xml;
  /**
   * State ID.
   */
  private final String id;

  /**
   * Ctor.
   *
   * @param doc XML
   * @param id  State ID
   */
  public Authors(final XML doc, final String id) {
    this.xml = doc;
    this.id = id;
  }

  /**
   * Ctor.
   *
   * @param name File name
   * @param id   State ID
   * @throws Exception if something went wrong
   */
  public Authors(final String name, final String id)
    throws Exception {
    this(
      new XMLDocument(
        new ResourceOf(
          name
        ).stream()
      ),
      id
    );
  }

  @Override
  public String value() throws Exception {
    return this.xml.xpath(
      "/states/changeState[@id='%s']/@author"
        .formatted(
          this.id
        )
    ).get(0);
  }
}
