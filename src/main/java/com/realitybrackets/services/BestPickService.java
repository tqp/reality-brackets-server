package com.realitybrackets.services;

import com.realitybrackets.Tester;
import com.realitybrackets.beans.BestPick;
import com.realitybrackets.beans.Result;
import com.realitybrackets.beans.Score;
import com.realitybrackets.data.DataService_BB22;
import com.realitybrackets.utils.PrintObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestPickService {
    PrintObjectService printObjectService;
    TeamService teamService;
    ResultService resultService;
    PickService pickService;
    RoundService roundService;
    ProjectedScoreService projectedScoreService;
    DataService_BB22 dataService;

    @Autowired
    public BestPickService(PrintObjectService printObjectService,
                           TeamService teamService,
                           ResultService resultService,
                           PickService pickService,
                           RoundService roundService,
                           ProjectedScoreService projectedScoreService,
                           DataService_BB22 dataService
    ) {
        this.printObjectService = printObjectService;
        this.teamService = teamService;
        this.resultService = resultService;
        this.pickService = pickService;
        this.roundService = roundService;
        this.projectedScoreService = projectedScoreService;
        this.dataService = dataService;
    }

    public static void main(String[] args) {
        Tester.main(null);
    }

    public List<BestPick> getBestPickListByTeamUser(String teamKey, String userKey) {
        Integer lastPlayedRoundNumber = this.roundService.getLastPlayedRound();
        int nextRoundNumber = lastPlayedRoundNumber + 1;
        List<BestPick> bestPickList = new ArrayList<>();
        System.out.println("Last Round: " + lastPlayedRoundNumber + ", Next Round: " + nextRoundNumber);

        // Get all of the contestants that haven't been eliminated
        List<Result> remainingContestants = this.resultService.getResultList().stream()
                .filter(result -> result.getRoundNumber().equals(lastPlayedRoundNumber))
                .collect(Collectors.toList());
//        this.printObjectService.PrintObject("remainingContestants", remainingContestants);

        remainingContestants.forEach(result -> {
            // My Projected Score
            System.out.println("\nContestant: " + result.getContestantKey());
            int position = this.pickService.getPickByTeamUserContestant(teamKey, userKey, result.getContestantKey()).getPosition();
            Double myScore = this.projectedScoreService.getProjectedScore(teamKey, userKey, nextRoundNumber, position).getScore();
            System.out.println("My Score      : " + myScore);

            // Average Team Competitors Score
//            this.



//            bestPickList.add(new BestPick(result.getContestantKey(), myScore, 0.0, 0.0));
        });

        return bestPickList;
    }
}

//                    // Get my projected score for the contestant in the next round.
//                    double myPoints = this.projectedScoreService.getProjectedScoreByTeamUser_GroupByPosition(teamKey, userKey).stream()
//                            .filter(projectedScoreList -> projectedScoreList.getContestantKey().equalsIgnoreCase(result.getContestantKey()))
//                            .mapToDouble(item -> this.roundService.getRound(nextRoundNumber).getRoundPoints())
//                            .sum();
//                    System.out.println("  - " + myPoints);
