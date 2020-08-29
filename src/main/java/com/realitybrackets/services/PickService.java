package com.realitybrackets.services;

import com.realitybrackets.Tester;
import com.realitybrackets.beans.Pick;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickService {
    private final DataService_BB22 dataService;
    private final RoundService roundService;
    private final ResultService resultService;

    @Autowired
    public PickService(DataService_BB22 dataService,
                       RoundService roundService,
                       ResultService resultService
    ) {
        this.dataService = dataService;
        this.roundService = roundService;
        this.resultService = resultService;
    }

    public static void main(String[] args) {
        Tester.main(null);
    }

    public List<Pick> getPickList() {
        return this.dataService.definePicks().stream()
                .sorted(Comparator.comparing(Pick::getTeamKey)
                        .thenComparing(Pick::getUserKey)
                        .thenComparing(Pick::getPosition)
                        .thenComparing(Pick::getContestantKey)
                )
                .collect(Collectors.toList());
    }

    public Pick getPick(String teamKey, String userKey, Integer position) {
        return this.dataService.definePicks().stream()
                .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey))
                .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey))
                .filter(pick -> pick.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    // AGGREGATIONS

    // Vertical
    public List<Pick> getPickListByTeamUserRound(String teamKey, String userKey, Integer roundNumber) {
        if (!this.roundService.isRoundNumberValid(roundNumber)) {
            throw new IllegalArgumentException("The round number is invalid.");
        } else {
            return this.getPickList().stream()
                    .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey)) // Filter picks by Team
                    .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey)) // Filter picks by User
                    .filter(pick -> isPositionValid(pick.getPosition(), roundNumber))
                    .peek(pick -> pick.setRoundNumber(roundNumber)) // Add Round Number to Pick.
                    .sorted(Comparator.comparing(Pick::getPosition))
                    .collect(Collectors.toList());
        }
    }

    // Horizontal
    public List<Pick> getPickListByTeamUserPosition(String teamKey, String userKey, Integer position) {
        return this.roundService.getRoundList().stream()
                .flatMap(round -> this.getPickList().stream()
                        .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey)) // Filter picks by Team
                        .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey)) // Filter picks by User
                        .filter(pick -> isPositionValid(pick.getPosition(), round.getRoundNumber()))
                        .filter(pick -> pick.getPosition().equals(position))
                        .peek(pick -> pick.setRoundNumber(round.getRoundNumber())) // Add Round Number to Pick
                )
                .sorted(Comparator.comparing(Pick::getRoundNumber))
                .collect(Collectors.toList());
    }

    // VALIDATIONS

    private boolean getRoundCutoffSetting() {
        return true;
    }

    public Boolean isPositionValid(int position, int roundNumber) {
        if (this.getRoundCutoffSetting()) { // Only include pick positions within the pre-defined round cutoff counts.
            return position > 0 && position <= this.roundService.getRound(roundNumber).getRoundCutoffCount();
        } else {
            return position > 0 && position <= this.resultService.getResultList().stream()
                    .filter(result -> result.getRoundNumber().equals(roundNumber))
                    .count();
        }
    }

}
