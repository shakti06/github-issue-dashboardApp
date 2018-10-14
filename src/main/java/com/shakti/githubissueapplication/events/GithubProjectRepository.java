package com.shakti.githubissueapplication.events;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GithubProjectRepository extends PagingAndSortingRepository<GitHubProject, Long> {
    GitHubProject findByRepoName(String repoName);
}
