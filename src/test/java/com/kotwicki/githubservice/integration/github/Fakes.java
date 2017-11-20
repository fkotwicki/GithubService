package com.kotwicki.githubservice.integration.github;

import reactor.core.publisher.Mono;

public class Fakes {

    public static GithubClient empty() {
        return (owner, name) -> Mono.empty();
    }

    public static GithubClient repositoryExists(final GithubRepoResponse expectedResponse) {
        return (owner, name) -> Mono.just(expectedResponse);
    }

    public static GithubClient error() {
        return (owner, name) -> Mono.error(new GithubClientException("Error occcured"));
    }
}
