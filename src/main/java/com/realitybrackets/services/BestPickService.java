package com.realitybrackets.services;

import com.realitybrackets.Tester;
import com.realitybrackets.beans.BestPick;
import com.realitybrackets.utils.PrintObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestPickService {
    PrintObjectService printObjectService;
    ResultService resultService;
    RoundService roundService;
    ProjectedScoreService projectedScoreService;

    @Autowired
    public BestPickService(PrintObjectService printObjectService,
                           ResultService resultService,
                           RoundService roundService,
                           ProjectedScoreService projectedScoreService
    ) {
        this.printObjectService = printObjectService;
        this.resultService = resultService;
        this.roundService = roundService;
        this.projectedScoreService = projectedScoreService;
    }

    public static void main(String[] args) {
        Tester.main(null);
    }

    public List<BestPick> getBestPickListByTeamUserRound(String teamKey, String userKey) {
        Integer lastPlayedRoundNumber = this.roundService.getLastPlayedRound();
        Integer nextRoundNumber = lastPlayedRoundNumber + 1;

        System.out.println("Last Round: " + lastPlayedRoundNumber);
        System.out.println("Next Round: " + nextRoundNumber);

        // Get all of the contestants that haven't been eliminated
        List<BestPick> bestPickList = new ArrayList<>();
        this.resultService.getResultList().stream()
                .filter(result -> result.getRoundNumber().equals(lastPlayedRoundNumber))
                .collect(Collectors.toList())
                .forEach(result -> {
                    System.out.println("contestantKey: " + result.getContestantKey());

                    // Get my projected score for the contestant in the next round.
                    double myPoints = this.projectedScoreService.getProjectedScoreByTeamUser_GroupByPosition(teamKey, userKey).stream()
                            .filter(projectedScoreList -> projectedScoreList.getContestantKey().equalsIgnoreCase(result.getContestantKey()))
                            .mapToDouble(item -> this.roundService.getRound(nextRoundNumber).getRoundPoints())
                            .sum();
                    System.out.println("  - " + myPoints);

                    // Get the average of everyone else's projected score for the ontestant in the next round

                    // Add the result to a list
                    bestPickList.add(new BestPick(result.getContestantKey(), 0.0, 0.0, 0.0));
                });
        return bestPickList;

    }
}
