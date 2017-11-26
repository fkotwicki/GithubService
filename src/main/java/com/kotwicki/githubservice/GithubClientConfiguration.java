package com.kotwicki.githubservice;

import com.kotwicki.githubservice.integration.github.DefaultGithubClient;
import com.kotwicki.githubservice.integration.github.GithubClient;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class GithubClientConfiguration {

    @Bean
    public GithubClient githubClient(@Value("${github.url}") final String githubUrl,
                                     @Value("${github.client.timeout}") final int timeout) {
        final ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(
                builder -> builder.afterNettyContextInit(
                        nettyContext -> nettyContext.addHandler(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
                )
        );

        final WebClient webClient = WebClient.builder()
                .clientConnector(clientHttpConnector)
                .baseUrl(githubUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                .build();

        return new DefaultGithubClient(webClient);
    }
}
