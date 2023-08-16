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

import lombok.SneakyThrows;
import org.cactoos.Text;
import org.cactoos.io.ResourceOf;
import ru.l3r8y.UnixizedOf;

/**
 * Contents Of.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class ContentsOf implements Text {

  /**
   * CMIG directory.
   */
  private final String cmig;

  /**
   * File name.
   */
  private final String file;

  /**
   * Ctor.
   *
   * @param cmg  CMIG directory
   * @param name File name
   */
  public ContentsOf(
    final String cmg,
    final String name
  ) {
    this.cmig = cmg;
    this.file = name;
  }

  @SneakyThrows
  @Override
  public String asString() {
    return new UnixizedOf(
      new ResourceOf(
        "%s/%s"
          .formatted(
            this.cmig,
            this.file
          )
      )
    ).asText()
      .asString();
  }
}
