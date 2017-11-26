package com.kotwicki.githubservice.api;

import com.kotwicki.githubservice.integration.github.GithubClient;
import com.kotwicki.githubservice.integration.github.GithubRepoResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class GithubRepositoryService {

    private final GithubClient githubClient;

    public GithubRepositoryService(final GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public Mono<GithubRepository> findRepository(final String owner, final String name) {
        return githubClient
                .findRepository(owner, name)
                .map(GITHUB_REPOSITORY_MAPPER)
                .onErrorMap(ApiException::new);
    }

    private static Function<GithubRepoResponse, GithubRepository> GITHUB_REPOSITORY_MAPPER = response -> new GithubRepository(
            response.fullName,
            response.description,
            response.cloneUrl,
            response.stars,
            response.createdAt);
}