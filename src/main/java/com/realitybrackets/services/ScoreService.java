package com.realitybrackets.services;

import com.realitybrackets.beans.PickResult;
import com.realitybrackets.beans.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {
    private final PickResultService pickResultService;
    private final UserService userService;
    private final RoundService roundService;

    @Autowired
    public ScoreService(PickResultService pickResultService, UserService userService, RoundService roundService) {
        this.pickResultService = pickResultService;
        this.userService = userService;
        this.roundService = roundService;
    }

    public Score getPickResultScore(String teamKey, String userKey, Integer pickPosition, Integer roundNumber) {
        PickResult.Status status = this.pickResultService.getPickResult(teamKey, userKey, pickPosition, roundNumber).getStatus();
        if (status.equals(PickResult.Status.CORRECT)) {
            Double score = this.roundService.getRoundByRoundNumber(roundNumber).getRoundPoints();
            return new Score(teamKey, userKey, roundNumber, pickPosition, score, null);
        } else {
            return null;
        }
    }

    public Score getPickResultScoreByTeamUserRound(String teamKey, String userKey, Integer roundNumber) {
        if (!this.roundService.isRoundNumberValid(roundNumber)) {
            throw new IllegalArgumentException("Ths round number is invalid.");
        } else {
            Double score = this.pickResultService.getPickResultsByRound(teamKey, userKey, roundNumber).stream()
                    .filter(item -> item.getStatus().equals(PickResult.Status.CORRECT))
                    .mapToDouble(item -> this.roundService.getRoundByRoundNumber(item.getRoundNumber()).getRoundPoints())
                    .sum();
            return new Score(teamKey, userKey, roundNumber, null, score, null);
        }
    }

    public Score getPickResultScoreByTeamUser(String teamKey, String userKey) {
        Double score = this.pickResultService.getPickResultsByTeamUser(teamKey, userKey).stream()
                .filter(item -> item.getStatus().equals(PickResult.Status.CORRECT))
                .mapToDouble(item -> this.roundService.getRoundByRoundNumber(item.getRoundNumber()).getRoundPoints())
                .sum();
        return new Score(teamKey, userKey, null, null, score, null);
    }

    public List<Score> getPickResultScoreByTeamUserGroupByRound(String teamKey, String userKey) {
        return this.pickResultService.getPickResultsByTeamUser(teamKey, userKey).stream()
                .filter(item -> item.getStatus().equals(PickResult.Status.CORRECT))
                .collect(Collectors.groupingBy(PickResult::getRoundNumber, Collectors.summingDouble(item -> this.roundService.getRoundByRoundNumber(item.getRoundNumber()).getRoundPoints())))
                .entrySet().stream()
                .map(e -> new Score(teamKey, userKey, e.getKey(), null, e.getValue(), null))
                .collect(Collectors.toList());
    }

    public List<Score> getPickResultScoreByTeamGroupByUserRound(String teamKey) {
        return this.userService.getUserListByTeamKey(teamKey).stream()
                .map(user -> this.getPickResultScoreByTeamUserGroupByRound(teamKey, user.getUserKey()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Score> getPickResultScoreByTeamGroupByUser(String teamKey) {
        return this.userService.getUserListByTeamKey(teamKey).stream()
                .map(user -> this.getPickResultScoreByTeamUser(teamKey, user.getUserKey()))
                .collect(Collectors.toList());

    }
}
