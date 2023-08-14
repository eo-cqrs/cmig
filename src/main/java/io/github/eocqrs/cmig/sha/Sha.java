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

import io.github.eocqrs.cmig.meta.Names;
import org.cactoos.Text;
import org.cactoos.bytes.Sha256DigestOf;
import org.cactoos.io.InputOf;
import org.cactoos.text.HexOf;

import java.util.List;

/**
 * SHA256 for State.
 *
 * @author Aliaksei Bialiauski (0.0.0)
 * @since 0.0.0
 */
public final class Sha implements Text {

  /**
   * State ID.
   */
  private final String id;

  /**
   * Master file.
   */
  private final String master;

  /**
   * CMIG directory.
   */
  private final String cmig;

  /**
   * Ctor.
   * @param id State ID
   * @param mst Master file
   * @param cmg CMIG directory
   */
  public Sha(
    final String id,
    final String mst,
    final String cmg
  ) {
    this.id = id;
    this.master = mst;
    this.cmig = cmg;
  }

  @Override
  public String asString() throws Exception {
    final List<String> contents = new StateChanges(
      new Names(
        this.master,
        this.id
      ),
      this.cmig
    ).value();
    return new HexOf(
      new Sha256DigestOf(
        new InputOf(
          contents.toString()
        )
      )
    ).asString();
  }
}
