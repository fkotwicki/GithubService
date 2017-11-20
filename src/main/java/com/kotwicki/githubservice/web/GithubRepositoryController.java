package com.kotwicki.githubservice.web;

import com.kotwicki.githubservice.api.GithubRepository;
import com.kotwicki.githubservice.api.GithubRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/repositories")
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    public GithubRepositoryController(final GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping(value = "/{owner}/{name}")
    public Mono<ResponseEntity<GithubRepository>> findRepository(@PathVariable final String owner,
                                                                 @PathVariable final String name) {
        return githubRepositoryService.findRepository(owner, name)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new RepositoryNotFoundException(name)));
    }

}
