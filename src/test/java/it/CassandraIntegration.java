/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023-2024 Aliaksei Bialiauski, EO-CQRS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package it;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Cassandra for Integration Testing.
 *
 * @since 0.0.0
 * @checkstyle VisibilityModifierCheck (30 lines)
 */
@SuppressWarnings(
    {
        "JTCOP.RuleCorrectTestName",
        "PMD.ProhibitPublicStaticMethods",
        "PMD.AbstractClassWithoutAbstractMethod"
    }
)
public abstract class CassandraIntegration {

    /**
     * Cassandra container.
     */
    protected static final CassandraContainer<?> CASSANDRA =
        new CassandraContainer<>(
            DockerImageName.parse("cassandra:3.11.15")
        ).withExposedPorts(9042);

    /**
     * Host.
     */
    protected static String host;

    /**
     * Stops container.
     */
    @AfterAll
    public static void tearDown() {
        CassandraIntegration.CASSANDRA.stop();
    }

    /**
     * Starts container.
     */
    @BeforeAll
    static void beforeAll() {
        CassandraIntegration.CASSANDRA.start();
        CassandraIntegration.host =
            CassandraIntegration.CASSANDRA.getHost();
    }
}
