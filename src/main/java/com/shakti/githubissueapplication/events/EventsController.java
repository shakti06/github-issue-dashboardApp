package com.shakti.githubissueapplication.events;

import com.shakti.githubissueapplication.github.GithubClient;
import com.shakti.githubissueapplication.github.RepositoryEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventsController {
    private final GithubProjectRepository githubProjectRepository;
    private final GithubClient githubClient;

    public EventsController(GithubProjectRepository githubProjectRepository, GithubClient githubClient) {
        this.githubProjectRepository = githubProjectRepository;
        this.githubClient = githubClient;
    }

    @GetMapping("/events/{repoName}")
    @ResponseBody
    public RepositoryEvent[] fetchEvents(@PathVariable String repoName){
        GithubProject project=  this.githubProjectRepository.findByRepoName(repoName);
        return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
    }
}
