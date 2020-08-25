package com.realitybrackets.services;

import com.realitybrackets.beans.Contestant;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContestantService {
    private final DataService_BB22 dataService;

    @Autowired
    public ContestantService(DataService_BB22 dataService) {
        this.dataService = dataService;
    }

    public List<Contestant> getContestantList() {
        return new ArrayList<>(dataService.getContestants());
    }

    public Contestant getContestantByContestantKey(String contestantKey) {
        return dataService.getContestants().stream()
                .filter(contestant -> contestant.getContestantKey().equalsIgnoreCase(contestantKey))
                .findFirst()
                .orElse(null);
    }
}
