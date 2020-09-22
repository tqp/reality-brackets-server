package com.realitybrackets.services;

import com.realitybrackets.Tester;
import com.realitybrackets.beans.BestPick;
import com.realitybrackets.beans.Pick;
import com.realitybrackets.beans.PickResult;
import com.realitybrackets.beans.Result;
import com.realitybrackets.utils.PrintObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestPickService {
    ResultService resultService;
    RoundService roundService;
    ProjectedScoreService projectedScoreService;
    PickService pickService;
    UserService userService;
    PrintObjectService printObjectService;
    PickResultService pickResultService;

    @Autowired
    public BestPickService(ResultService resultService,
                           RoundService roundService,
                           ProjectedScoreService projectedScoreService,
                           PickService pickService,
                           UserService userService,
                           PrintObjectService printObjectService,
                           PickResultService pickResultService

    ) {
        this.resultService = resultService;
        this.roundService = roundService;
        this.projectedScoreService = projectedScoreService;
        this.pickService = pickService;
        this.userService = userService;
        this.printObjectService = printObjectService;
        this.pickResultService = pickResultService;
    }

    public static void main(String[] args) {
        Tester.main(null);
    }

    public List<BestPick> getBestPicks_NextRound(String teamKey, String userKey) {
        return this.resultService.getResultList().stream()
                // Get only contestants that are still in the game
                .filter(result -> result.getRoundNumber().equals(this.roundService.getLastPlayedRound()))
                .map(remainingContestants -> {
                    // What is the next round?
                    int nextRound = this.roundService.getLastPlayedRound() + 1;
                    // How may points are correct guesses in the next round worth?
                    double pointsPerCorrect = this.roundService.getRound(nextRound).getRoundPoints();

                    List<Pick> myPickList = this.pickService.getPickList().stream()
                            .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey)) // Filter picks by Team
                            .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey)) // Filter picks by User
                            .filter(pick -> pick.getContestantKey().equalsIgnoreCase(remainingContestants.getContestantKey()))
                            .filter(pick -> pick.getPosition() <= roundService.getRound(nextRound).getRoundCutoffCount())
                            .sorted(Comparator.comparing(Pick::getPosition))
                            .collect(Collectors.toList());

                    List<Pick> othersPickList = this.pickService.getPickList().stream()
                            .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey)) // Filter picks by Team
                            .filter(pick -> !pick.getUserKey().equalsIgnoreCase(userKey)) // Filter picks by User
                            .filter(pick -> pick.getContestantKey().equalsIgnoreCase(remainingContestants.getContestantKey()))
                            .filter(pick -> pick.getPosition() <= roundService.getRound(nextRound).getRoundCutoffCount())
                            .sorted(Comparator.comparing(Pick::getPosition))
                            .collect(Collectors.toList());

                    int otherSize = this.userService.getUserListByTeamKey(teamKey).size() - 1;

                    return new BestPick(remainingContestants.getContestantKey(),
                            pointsPerCorrect * (double) myPickList.size(),
                            pointsPerCorrect * ((double) othersPickList.size() / otherSize),
                            pointsPerCorrect * ((double) myPickList.size() - ((double) othersPickList.size() / otherSize))
                    );
                })
                .sorted(Comparator.comparing(BestPick::getPointDifferential).reversed())
                .collect(Collectors.toList());
    }

    public List<BestPick> getBestPicks_Game(String teamKey, String userKey) {
        List<Result> remainingPlayerList = this.resultService.getResultList().stream()
                // Get only contestants that are still in the game
                .filter(result -> result.getRoundNumber().equals(this.roundService.getLastPlayedRound()))
                .collect(Collectors.toList());

        // What is the next round?
        int nextRound = this.roundService.getLastPlayedRound() + 1;
        System.out.println("nextRound: " + nextRound);

        remainingPlayerList.forEach(result -> {
            System.out.println("---");
            System.out.println(result.getContestantKey());

            // Get my projected score for the contestant
            Double score = this.pickResultService.getPickResultByTeamUser(teamKey, userKey).stream()
                    .filter(pickResult -> pickResult.getRoundNumber() >= nextRound)
                    .filter(pickResult -> pickResult.getPick().getContestantKey().equalsIgnoreCase(result.getContestantKey()))
                    .filter(pickResult -> pickResult.getStatus().equals(PickResult.Status.CORRECT) || pickResult.getStatus().equals(PickResult.Status.PROJECTED))
                    .mapToDouble(pickResult -> this.roundService.getRound(pickResult.getRoundNumber()).getRoundPoints())
                    .sum();

            // Get my team opponents' combined score for the contestant
            Double opponentsScore = this.roundService.getRoundList().stream()
                    .flatMap(round -> {
                        if (!this.roundService.isRoundNumberValid(round.getRoundNumber())) {
                            throw new IllegalArgumentException("The round number is invalid.");
                        } else {
                            return this.pickService.getPickList().stream()
                                    .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey)) // Filter picks by Team
                                    .filter(pick -> !pick.getUserKey().equalsIgnoreCase(userKey)) // Filter picks by User
                                    .filter(pick -> this.pickService.isPositionValid(pick.getPosition(), round.getRoundNumber()))
                                    .peek(pick -> pick.setRoundNumber(round.getRoundNumber())) // Add Round Number to Pick
                                    .sorted(Comparator.comparing(Pick::getPosition))
                                    .map(pick -> this.pickResultService.getPickResult(teamKey, userKey, pick.getPosition(), round.getRoundNumber()));
                        }
                    })
                    .peek(pickResult -> {
                        System.out.println(pickResult.getPick().getUserKey() + ", " + pickResult.getRoundNumber() + ", ");
                    })
                    .filter(pickResult -> pickResult.getRoundNumber() >= nextRound)
                    .filter(pickResult -> pickResult.getPick().getContestantKey().equalsIgnoreCase(result.getContestantKey()))
                    .filter(pickResult -> pickResult.getStatus().equals(PickResult.Status.CORRECT) || pickResult.getStatus().equals(PickResult.Status.PROJECTED))
                    .mapToDouble(pickResult -> this.roundService.getRound(pickResult.getRoundNumber()).getRoundPoints())
                    .sum();

            System.out.println("score: " + score + ", opponentScore: " + opponentsScore);
        });

//        this.printObjectService.PrintObject("list", list);

        return null;

    }

    public List<BestPick> getBestPicksRootToStay(String teamKey, String userKey) {
        return this.getBestPicks_NextRound(teamKey, userKey).stream()
                .filter(bestPick -> bestPick.getPointDifferential() > 0)
                .sorted(Comparator.comparing(BestPick::getPointDifferential).reversed())
                .collect(Collectors.toList());
    }

    public List<BestPick> getBestPicksRootToLeave(String teamKey, String userKey) {
        return this.getBestPicks_NextRound(teamKey, userKey).stream()
                .filter(bestPick -> bestPick.getPointDifferential() < 0)
                .sorted(Comparator.comparing(BestPick::getPointDifferential).reversed())
                .collect(Collectors.toList());
    }

    public List<BestPick> getBestPicksRootNoImpact(String teamKey, String userKey) {
        return this.getBestPicks_NextRound(teamKey, userKey).stream()
                .filter(bestPick -> bestPick.getPointDifferential() == 0)
                .sorted(Comparator.comparing(BestPick::getPointDifferential).reversed())
                .collect(Collectors.toList());
    }
}
