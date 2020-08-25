package com.realitybrackets.services;

import com.realitybrackets.beans.Result;
import com.realitybrackets.beans.Round;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class RoundService {
    private final DataService_BB22 dataService;

    @Autowired
    public RoundService(DataService_BB22 dataService) {
        this.dataService = dataService;
    }

    public Round getRoundByRoundNumber(int roundNumber) {
        return this.dataService.getRounds().stream()
                .filter(round -> round.getRoundNumber().equals(roundNumber))
                .findFirst()
                .orElse(null);
    }

    public Integer getLastPlayedRound() {
        return this.dataService.getResults().stream()
                .map(Result::getRoundNumber)
                .max(Comparator.comparing(x -> x))
                .orElse(null);
    }

    public Integer getFinalRound() {
        return this.dataService.getRounds().stream()
                .map(Round::getRoundNumber)
                .max(Comparator.comparing(x -> x))
                .orElse(null);
    }

    public Boolean isRoundNumberValid(int roundNumber) {
        return roundNumber > 0 && this.dataService.getRounds().stream()
                .anyMatch(round -> round.getRoundNumber().equals(roundNumber));
    }
}
