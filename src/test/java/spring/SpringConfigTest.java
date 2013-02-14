package spring;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:spring-config.xml")
public class SpringConfigTest extends AbstractJUnit4SpringContextTests {

    private static final String NODE = "test";
    private static final String STAGE = "dev";
    private static final String PRODUCT_NAME = "theproduct";
    private static final String PRODUCT_VERSION = "1.0.0";

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("config.uri", "file:${user.dir}/conf");
        System.setProperty("config.stage", STAGE);
        System.setProperty("config.node", NODE);
        System.setProperty("product.name", PRODUCT_NAME);
        System.setProperty("product.version", PRODUCT_VERSION);
    }

    @Test
    public void testConfigProperties() {
        final Config config = applicationContext.getBean(Config.class);

        assertEquals("Node Name", NODE, config.node);
        assertEquals("Stage Name", STAGE, config.stage);
        assertEquals("Product Name", "My Sample Product", config.productTitle);
        assertEquals("Product Version", PRODUCT_VERSION, config.productVersion);
        assertEquals("Stage Property","Stage Property value for Dev Stage", config.stageProperty);
        assertEquals("Node Property", "Node property value for test@dev", config.nodeProperty);
        assertEquals("Overridden Property", 6, config.networkTimeout);
    }
}