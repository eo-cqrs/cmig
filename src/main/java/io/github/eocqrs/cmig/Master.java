package io.github.eocqrs.cmig;

import com.jcabi.xml.XMLDocument;
import org.cactoos.Scalar;
import org.cactoos.io.ResourceOf;

/**
 * Master.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public final class Master implements Scalar<String> {

  private final String dir;
  private final String name;

  /**
   * Ctor.
   *
   * @param dr Dir name
   * @param nm File name
   */
  public Master(final String dr, final String nm) {
    this.dir = dr;
    this.name = nm;
  }

  @Override
  public String value() throws Exception {
    return new XMLDocument(
      new ResourceOf(
        "%s/%s".formatted(
          this.dir,
          this.name
        )
      ).stream()
    ).toString();
  }
}
