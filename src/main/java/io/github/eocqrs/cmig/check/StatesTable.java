package io.github.eocqrs.cmig.check;

import org.cactoos.Text;

/**
 * States Table Specification.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class StatesTable implements Text {

  @Override
  public String asString() throws Exception {
    return
      """
        CREATE TABLE cmig.states
        (
        id PRIMARY KEY,
        author TEXT,
        sha TEXT,
        seen TIMESTAMP
        );
        """;
  }
}
