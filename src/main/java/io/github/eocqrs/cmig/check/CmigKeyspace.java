package io.github.eocqrs.cmig.check;

import org.cactoos.Text;

/**
 * CMIG Check Keyspace.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class CmigKeyspace implements Text {

  private final String dc;

  public CmigKeyspace(final String datacenter) {
    this.dc = datacenter;
  }

  @Override
  public String asString() throws Exception {
    return
      """
        CREATE KEYSPACE cmig 
        WITH REPLICATION = {
        'class': 'NetworkTopologyStrategy',
        'datacenter': %s
        };
        """.formatted(
        this.dc
      );
  }
}
