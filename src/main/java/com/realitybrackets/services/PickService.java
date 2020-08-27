package com.realitybrackets.services;

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

    public List<Pick> getPickList() {
        return this.dataService.definePicks().stream()
                .sorted(Comparator.comparing(Pick::getTeamKey)
                        .thenComparing(Pick::getUserKey)
                        .thenComparing(Pick::getPickPosition)
                        .thenComparing(Pick::getContestantKey)
                )
                .collect(Collectors.toList());
    }

    public Pick getPick(String teamKey, String userKey, Integer pickPosition) {
        return this.dataService.definePicks().stream()
                .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey))
                .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey))
                .filter(pick -> pick.getPickPosition().equals(pickPosition))
                .findFirst()
                .orElse(null);
    }

    // VALIDATIONS

    private boolean getRoundCutoffSetting() {
        return true;
    }

    public Boolean isPickPositionValid(int pickPosition, int roundNumber) {
        if (this.getRoundCutoffSetting()) { // Only include pick positions within the pre-defined round cutoff counts.
            return pickPosition > 0 && pickPosition <= this.roundService.getRound(roundNumber).getRoundCutoffCount();
        } else {
            return pickPosition > 0 && pickPosition <= this.resultService.getResultList().stream()
                    .filter(result -> result.getRoundNumber().equals(roundNumber))
                    .count();
        }
    }

    // AGGREGATIONS

    public List<Pick> getPickListByTeamUserRound(String teamKey, String userKey, Integer roundNumber) {
        return this.getPickList().stream()
                .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey))
                .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey))
                .filter(pick -> isPickPositionValid(pick.getPickPosition(), roundNumber))
                .sorted(Comparator.comparing(Pick::getPickPosition))
                .collect(Collectors.toList());
    }

    public List<Pick> getPickListByTeamUserContestant(String teamKey, String userKey, String contestantKey) {
        return this.getPickList().stream()
                .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey))
                .filter(pick -> pick.getUserKey().equalsIgnoreCase(userKey))
                .filter(pick -> pick.getContestantKey().equalsIgnoreCase(contestantKey))
                .sorted(Comparator.comparing(Pick::getPickPosition))
                .collect(Collectors.toList());
    }

}
