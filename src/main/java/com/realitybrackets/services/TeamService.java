package com.realitybrackets.services;

import com.realitybrackets.beans.Team;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private DataService_BB22 dataService;

    @Autowired
    public TeamService(DataService_BB22 dataService) {
        this.dataService = dataService;
    }

    public List<Team> getTeamList() {
        return new ArrayList<>(this.dataService.getTeams());
    }

    public Team getTeamByTeamKey(String teamKey) {
        return this.dataService.getTeams().stream()
                .filter(team -> team.getTeamKey().equalsIgnoreCase(teamKey))
                .findFirst()
                .orElse(null);
    }
}
