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

package io.github.eocqrs.cmig;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import io.github.eocqrs.cmig.meta.Names;
import io.github.eocqrs.cmig.session.Cassandra;
import io.github.eocqrs.cmig.session.InFile;
import org.cactoos.Scalar;
import org.cactoos.io.ResourceOf;

/**
 * Master files.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class Master implements Scalar<String> {

  /**
   * XML.
   */
  private final XML xml;
  /**
   * Cassandra.
   */
  private final Cassandra cassandra;

  /**
   * Ctor.
   *
   * @param doc XML
   * @param cs  Cassandra
   */
  public Master(final XML doc, final Cassandra cs) {
    this.xml = doc;
    this.cassandra = cs;
  }

  /**
   * Ctor.
   *
   * @param nm File name
   * @param cs Cassandra
   * @throws Exception if something went wrong
   */
  public Master(
    final String nm,
    final Cassandra cs
  ) throws Exception {
    this(
      new XMLDocument(
        new ResourceOf(
          "cmig/%s"
            .formatted(nm)
        ).stream()
      ),
      cs
    );
  }

  @Override
  public String value() throws Exception {
    new Names(this.xml).value()
      .forEach(file -> {
          try {
            new InFile(
              this.cassandra, "cmig/%s".formatted(file)
            ).apply();
          } catch (final Exception ex) {
            throw new IllegalStateException(
              "Schema migration cannot be executed",
              ex
            );
          }
        }
      );
    return "c8be525311cfd5f5ac7bf1c7d41a61fd82ae5e384b9b7b490358c1cb038c46c9";
  }
}
