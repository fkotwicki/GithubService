package com.kotwicki.githubservice.integration.github;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class DefaultGithubClient implements GithubClient {

    private final WebClient webClient;

    public DefaultGithubClient(final WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<GithubRepoResponse> findRepository(final String owner, final String name) {
        return webClient
                .get()
                .uri("/repos/{owner}/{name}", owner, name)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == 404, clientResponse -> Mono.empty())
                .onStatus(HttpStatus::is4xxClientError, this::mapClientError)
                .bodyToMono(GithubRepoResponse.class);
    }

    private Mono<? extends Throwable> mapClientError(final ClientResponse response) {
        return response.bodyToMono(GithubClientErrorResponse.class)
                .flatMap(githubClientErrorResponse -> Mono.error(new GithubClientException(githubClientErrorResponse.message)));
    }
}
