package com.kotwicki.githubservice.api;

public class GithubRepository {

    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final int stars;
    private final String createdAt;

    GithubRepository(final String fullName,
                     final String description,
                     final String cloneUrl,
                     final int stars,
                     final String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public int getStars() {
        return stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GithubRepository that = (GithubRepository) o;

        if (stars != that.stars) return false;
        if (!fullName.equals(that.fullName)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!cloneUrl.equals(that.cloneUrl)) return false;
        return createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + cloneUrl.hashCode();
        result = 31 * result + stars;
        result = 31 * result + createdAt.hashCode();
        return result;
    }
}
