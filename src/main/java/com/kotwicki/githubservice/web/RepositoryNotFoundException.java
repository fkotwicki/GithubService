package com.kotwicki.githubservice.web;

class RepositoryNotFoundException extends RuntimeException {

    private final String repositoryName;

    RepositoryNotFoundException(final String repositoryName) {
        this.repositoryName = repositoryName;
    }

    String getRepositoryName() {
        return repositoryName;
    }
}
