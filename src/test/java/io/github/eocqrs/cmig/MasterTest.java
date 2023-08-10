package io.github.eocqrs.cmig;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

/**
 * Test suite for {@link Master}.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
final class MasterTest {

  @Test
  void readsXmlInRightFormat() throws Exception {
    MatcherAssert.assertThat(
      "XML in right format",
      new Master("cmig", "master.xml")
        .value(),
      new IsEqual<>(
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<files>\n" +
        "      \n    <file author=\"h1alexbel\" id=\"1\">\n" +
        "    001-initial.cql\n  </file>\n    \n</files>\n"
      )
    );
  }
}
