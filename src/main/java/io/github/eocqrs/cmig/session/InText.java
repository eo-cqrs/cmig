package io.github.eocqrs.cmig.session;

import org.cactoos.Text;

/**
 * CQL in plain text.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class InText implements Cql {

  /**
   * Text CQL query.
   */
  private final Text text;

  /**
   * Cassandra.
   */
  private final Cassandra cassandra;

  /**
   * Ctor.
   *
   * @param txt    Text
   * @param cssndr Cassandra
   */
  public InText(
    final Text txt,
    final Cassandra cssndr
  ) {
    this.text = txt;
    this.cassandra = cssndr;
  }

  @Override
  public void apply() throws Exception {
    this.cassandra.value().execute(
      this.text.asString()
    );
  }
}
