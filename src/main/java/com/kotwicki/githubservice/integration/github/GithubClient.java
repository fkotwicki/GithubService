package com.kotwicki.githubservice.integration.github;

import reactor.core.publisher.Mono;

public interface GithubClient {

    Mono<GithubRepoResponse> findRepository(String owner, String name);

}
