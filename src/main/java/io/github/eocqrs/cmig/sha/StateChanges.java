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

import io.github.eocqrs.cmig.meta.XpathList;
import lombok.SneakyThrows;
import org.cactoos.Scalar;
import org.cactoos.io.ResourceOf;
import ru.l3r8y.UnixizedOf;

import java.util.List;

/**
 * Changes inside State.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class StateChanges implements Scalar<List<String>> {

  /**
   * XPATH lists.
   */
  private final XpathList list;
  /**
   * CMIG directory.
   */
  private final String cmig;

  /**
   * Ctor.
   *
   * @param lst XPATH list
   * @param cmg CMIG directory
   */
  public StateChanges(final XpathList lst, final String cmg) {
    this.list = lst;
    this.cmig = cmg;
  }

  @Override
  public List<String> value() throws Exception {
    return this.list.value()
      .stream()
      .map(this::contentOf)
      .toList();
  }

  /*
   * @todo #37:30min/DEV Move to separate class
   * Create class ContentOf with tests.
   * This puzzle might be moved into unixized library.
   */
  @SneakyThrows
  private String contentOf(final String file) {
    return new UnixizedOf(
      new ResourceOf(
        "%s/%s"
          .formatted(
            this.cmig,
            file
          )
      )
    )
      .asText()
      .asString();
  }
}
