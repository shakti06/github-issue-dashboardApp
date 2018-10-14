package com.shakti.githubissueapplication.events;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class GitHubProject implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String orgName;
    @Column(unique = true)
    private String repoName;

    public GitHubProject(){

    }

    public GitHubProject(String orgName, String repoName) {
        this.orgName = orgName;
        this.repoName = repoName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Override
    public String toString() {
        return "GitHubProject{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", repoName='" + repoName + '\'' +
                '}';
    }
}
