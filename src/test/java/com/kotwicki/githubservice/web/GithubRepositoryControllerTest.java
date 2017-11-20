package com.kotwicki.githubservice.web;

import com.kotwicki.githubservice.api.GithubRepositoryService;
import com.kotwicki.githubservice.integration.github.Fakes;
import com.kotwicki.githubservice.integration.github.GithubRepoResponse;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class GithubRepositoryControllerTest {

    @Test
    public void shouldReturnValidResponseIfRepositoryFound() {
        final GithubRepoResponse expected = new GithubRepoResponse("John",
                "Doe",
                "johndoe.com",
                5,
                "2013-08-28T19:18:12Z");
        final GithubRepositoryController controller = new GithubRepositoryController(new GithubRepositoryService(Fakes.repositoryExists(expected)));

        doGet(controller)
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("fullName").isEqualTo("John")
                .jsonPath("description").isEqualTo("Doe")
                .jsonPath("cloneUrl").isEqualTo("johndoe.com")
                .jsonPath("stars").isEqualTo("5")
                .jsonPath("createdAt").isEqualTo("2013-08-28T19:18:12Z");
    }

    @Test
    public void shouldReturnErrorResponseIfRepositoryNotFound() {
        final GithubRepositoryController controller = new GithubRepositoryController(new GithubRepositoryService(Fakes.empty()));

        doGet(controller)
                .expectStatus().isEqualTo(404)
                .expectBody()
                .jsonPath("message").exists();
    }

    @Test
    public void shouldReturnErrorResponseWhenApiExceptionIsThrown() {
        final GithubRepositoryController controller = new GithubRepositoryController(new GithubRepositoryService(Fakes.error()));
        doGet(controller)
                .expectStatus().isEqualTo(500)
                .expectBody()
                .jsonPath("message").exists();
    }

    private WebTestClient.ResponseSpec doGet(final GithubRepositoryController controller) {
        return WebTestClient.bindToController(controller).controllerAdvice(new ExceptionHandlers())
                .build()
                .get()
                .uri("/repositories/someOwner/someRepository")
                .exchange();
    }
}
