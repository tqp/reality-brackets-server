package com.realitybrackets.services;

import com.realitybrackets.Tester;
import com.realitybrackets.beans.BestPick;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestPickService {
    private final DataService_BB22 dataService;
    private final RoundService roundService;
    private final PickService pickService;

    @Autowired
    public BestPickService(DataService_BB22 dataService, RoundService roundService, PickService pickService) {
        this.dataService = dataService;
        this.roundService = roundService;
        this.pickService = pickService;
    }

    public static void main(String[] args) {
        Tester.main(null);
    }

    public List<BestPick> getBestPicksTest(String teamKey, String userKey) {
        System.out.println("getBestPicksTest");
        this.dataService.getResults().stream()
                .filter(result -> result.getRoundNumber().equals(this.roundService.getLastPlayedRound()))
                .collect(Collectors.toList())
                .forEach(result -> {
                    // Remaining Contestants
                    System.out.println(result.getContestantKey());

//                    this.pickService.getUserPicksByRound(teamKey, userKey, roundNumber).stream()
//                            .filter(pick -> pick.getContestantKey().equalsIgnoreCase(result.getContestantKey()))
//                            .forEach(pick -> {
//                                System.out.println("Pick: " + pick.getContestantKey());
//                            });

                });

        return null;
    }

    public List<BestPick> getBestPicksByTeamUserRound(String teamKey, String userKey, Integer roundNumber) {
        this.dataService.getResults().stream()
                .filter(result -> result.getRoundNumber().equals(this.roundService.getLastPlayedRound()))
                .collect(Collectors.toList())
                .forEach(result -> {

                    this.pickService.getUserPicksByRound(teamKey, userKey, roundNumber).stream()
                            .filter(pick -> pick.getContestantKey().equalsIgnoreCase(result.getContestantKey()))
                            .forEach(pick -> {
                                System.out.println("Pick: " + pick.getContestantKey());
                            });
                });
        return null;
    }
}
