package io.searchbox.jest.sample.configuration;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;

/**
 * @author ferhat
 */
@Configuration
public class SpringConfiguration {

    public
    @Bean
    ClientConfig clientConfig() {
        String connectionUrl = StringUtils.isNotBlank(System.getenv("SEARCHBOX_URL")) ?
                System.getenv("SEARCHBOX_URL") : "http://api.searchbox.io/replace-me-with-your-api-key";

        // String connectionUrl = "http://localhost:9200"

        ClientConfig clientConfig = new ClientConfig();
        LinkedHashSet<String> servers = new LinkedHashSet<String>();
        servers.add(connectionUrl);
        clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);
        clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED, false);
        return clientConfig;
    }

    public
    @Bean
    JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig());
        return factory.getObject();
    }
}
