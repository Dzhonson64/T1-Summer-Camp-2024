package ru.t1.summer.camp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.t1.summer.camp.client.T1Client;


@Configuration
public class ClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://193.19.100.32:7000")
                .build();
    }

    @Bean
    public T1Client t1Client() {
        WebClientAdapter adapter = WebClientAdapter.create(webClient());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(T1Client.class);
    }
}
