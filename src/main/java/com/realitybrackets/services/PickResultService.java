package com.realitybrackets.services;

import com.realitybrackets.beans.Pick;
import com.realitybrackets.beans.PickResult;
import com.realitybrackets.beans.Result;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickResultService {
    private final DataService_BB22 dataService;
    private final PickService pickService;
    private final ResultService resultService;
    private final RoundService roundService;

    @Autowired
    public PickResultService(DataService_BB22 dataService, PickService pickService, ResultService resultService, RoundService roundService) {
        this.dataService = dataService;
        this.pickService = pickService;
        this.resultService = resultService;
        this.roundService = roundService;
    }

    // THIS IS THE HEART OF THIS SYSTEM.
    public PickResult getPickResult(String teamKey, String userKey, Integer pickPosition, Integer roundNumber) {

        // Set Default/Initial status
        PickResult.Status status = PickResult.Status.UNKNOWN;

        // Get the pick for the criteria provided.
        Pick specificPick = this.pickService.getPick(teamKey, userKey, pickPosition);
        // Only picks that are within the cutoff for the round provided.
        specificPick = specificPick.getPickPosition() <= this.roundService.getRound(roundNumber).getRoundCutoffCount() ? specificPick : null;

        // Check to see if there is a result for the pick.
        Result specificResult = null;

        if (specificPick != null) {

            if (roundNumber <= this.roundService.getLastPlayedRound()) { // Known Result

                Pick pick = specificPick;
                specificResult = this.resultService.getResultList().stream()
                        .filter(result -> result.getRoundNumber().equals(roundNumber)) // Only check results for the round provided.
                        .filter(result -> result.getContestantKey().equalsIgnoreCase(pick.getContestantKey())) // Only show when the pick matches the result.
                        .findFirst()
                        .orElse(null);

                status = specificResult != null
                        ? // If a result is found
                        PickResult.Status.CORRECT
                        : // If no result is found
                        roundNumber <= this.roundService.getLastPlayedRound()
                                ? PickResult.Status.WRONG
                                : PickResult.Status.UNKNOWN; // This shouldn't happen since we're only looking at rounds already played.

            } else { // Projected Pick Result

                Pick pick = specificPick;
                specificResult = this.resultService.getResultList().stream()
                        .filter(result -> result.getRoundNumber().equals(this.roundService.getLastPlayedRound())) // Only check results for the round provided.
                        .filter(result -> result.getContestantKey().equalsIgnoreCase(pick.getContestantKey())) // Only show when the pick matches the result.
                        .findFirst()
                        .orElse(null);

                status = specificResult != null
                        ? // If a result is found
                        PickResult.Status.PROJECTED
                        : // If no result is found
                        PickResult.Status.WRONG;

            }

        } else {
            status = PickResult.Status.ELIMINATED;
        }
        return new PickResult(specificPick, specificResult, roundNumber, status);
    }

    // AGGREGATIONS

    public List<PickResult> getPickResultByTeamUserRound(String teamKey, String userKey, Integer roundNumber) {
        if (!this.roundService.isRoundNumberValid(roundNumber)) {
            throw new IllegalArgumentException("The round number is invalid.");
        } else {
            return this.pickService.getPickListByTeamUserRound(teamKey, userKey, roundNumber).stream()
                    .map(pick -> this.getPickResult(teamKey, userKey, pick.getPickPosition(), roundNumber))
                    .collect(Collectors.toList());
        }
    }

    public List<PickResult> getPickResultByTeamUserContestant(String teamKey, String userKey, String contestantKey) {
        List<Pick> pickList = this.pickService.getPickListByTeamUserContestant(teamKey, userKey, contestantKey);
        Pick pick = pickList.stream().findFirst().orElse(null);
        return this.roundService.getRoundList().stream()
                .map(round -> this.getPickResult(teamKey, userKey, pick.getPickPosition(), round.getRoundNumber()))
                .collect(Collectors.toList());
    }


}
