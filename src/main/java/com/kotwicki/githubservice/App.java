package com.kotwicki.githubservice;

import com.kotwicki.githubservice.integration.github.DefaultGithubClient;
import com.kotwicki.githubservice.integration.github.GithubClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public GithubClient githubClient(@Value("${github.url}") final String githubUrl) {
        final WebClient webClient = WebClient.builder()
                .baseUrl(githubUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                .build();
        return new DefaultGithubClient(webClient);
    }
}
