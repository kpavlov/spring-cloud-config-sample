package spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${common.copyright}")
    String copyright;

    @Value("${product.title}")
    String productTitle;

    @Value("${product.version}")
    String productVersion;

    @Value("${config.stage}")
    String stage;

    @Value("${config.node}")
    String node;

    @Value("${network.timeout}")
    int networkTimeout;

    @Value("${stage.property}")
    String stageProperty;

    @Value("${node.property}")
    String nodeProperty;

    public String getProduct() {
        return String.format("%s v.%s, %s", productTitle, productVersion, copyright);
    }

    public String getEnvironment() {
        return String.format("Node %s@%s", node, stage);
    }

    public String getSettings() {
        return String.format("NetworkTimeout: %s\nStageProperty: %s\nNodeProperty: %s", networkTimeout, stageProperty, nodeProperty);
    }
}
