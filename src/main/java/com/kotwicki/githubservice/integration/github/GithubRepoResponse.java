package com.kotwicki.githubservice.integration.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubRepoResponse {

    public String fullName;
    public String description;
    public String cloneUrl;
    public int stars;
    public String createdAt;

    @JsonCreator
    public GithubRepoResponse(
            @JsonProperty("name") final String fullName,
            @JsonProperty("description") final String description,
            @JsonProperty("clone_url") final String cloneUrl,
            @JsonProperty("stargazers_count") final int stars,
            @JsonProperty("created_at") final String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
