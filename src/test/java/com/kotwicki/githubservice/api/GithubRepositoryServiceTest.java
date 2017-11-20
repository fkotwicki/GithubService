package com.kotwicki.githubservice.api;

import com.kotwicki.githubservice.integration.github.Fakes;
import com.kotwicki.githubservice.integration.github.GithubRepoResponse;
import org.junit.Test;
import reactor.test.StepVerifier;

public class GithubRepositoryServiceTest {

    @Test
    public void shouldReturnRepositoryIfExists() {
        final GithubRepoResponse response = new GithubRepoResponse("fullName",
                "description",
                "cloneUrl",
                5,
                "createdAt");
        final GithubRepositoryService service = new GithubRepositoryService(Fakes.repositoryExists(response));

        final GithubRepository expected = new GithubRepository(response.fullName,
                response.description,
                response.cloneUrl,
                response.stars,
                response.createdAt);

        StepVerifier.create(service.findRepository("owner", "name"))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    public void shouldThrowApiExceptionOnError() {
        final GithubRepositoryService service = new GithubRepositoryService(Fakes.error());

        StepVerifier.create(service.findRepository("owner", "repo"))
                .expectError(ApiException.class)
                .verify();
    }
}
