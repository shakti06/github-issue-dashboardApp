package com.shakti.githubissueapplication.events;

import com.shakti.githubissueapplication.github.GithubClient;
import com.shakti.githubissueapplication.github.RepositoryEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @GetMapping("/admin")
    public String getAdmin(Model model){
        model.addAttribute("projects", this.githubProjectRepository.findAll());
        return "admin";
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        List<DashboardEntry> entries = StreamSupport
                .stream(this.githubProjectRepository.findAll().spliterator(), true)
                .map(p -> new DashboardEntry(p, githubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
                .collect(Collectors.toList());
        model.addAttribute("entries", entries);
        return "dashboard";
    }
}
