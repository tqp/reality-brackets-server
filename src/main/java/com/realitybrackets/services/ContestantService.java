package com.realitybrackets.services;

import com.realitybrackets.beans.Contestant;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContestantService {
    private final DataService_BB22 dataService;

    @Autowired
    public ContestantService(DataService_BB22 dataService) {
        this.dataService = dataService;
    }

    public List<Contestant> getContestantList() {
        return this.dataService.defineContestants().stream()
                .sorted(Comparator.comparing(Contestant::getContestantKey))
                .collect(Collectors.toList());
    }

    public Contestant getContestant(String contestantKey) {
        return dataService.defineContestants().stream()
                .filter(contestant -> contestant.getContestantKey().equalsIgnoreCase(contestantKey))
                .findFirst()
                .orElse(null);
    }
}
