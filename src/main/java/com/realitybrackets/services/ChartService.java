package com.realitybrackets.services;

import com.realitybrackets.Tester;
import com.realitybrackets.beans.Chart;
import com.realitybrackets.beans.PickResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ChartService {
    private final RoundService roundService;
    private final ContestantService contestantService;
    private final PickResultService pickResultService;

    @Autowired
    public ChartService(RoundService roundService,
                        ContestantService contestantService,
                        PickResultService pickResultService
    ) {
        this.roundService = roundService;
        this.contestantService = contestantService;
        this.pickResultService = pickResultService;
    }

    public static void main(String[] args) {
        Tester.main(null);
    }

    public List<Chart> getChartByRound() {
        List<Chart> chartList = new ArrayList<>();
        this.roundService.getRoundList().forEach(round -> {
            AtomicInteger position = new AtomicInteger(1);
            //System.out.println("---");
            this.contestantService.getContestantList().forEach(contestant -> {
                PickResult pickResult = this.pickResultService.getPickResult("key_team2", "key_user1", position.get(), round.getRoundNumber());
                //System.out.println("R" + String.format("%02d", round.getRoundNumber()) + "P" + String.format("%02d", position.get()) + ": " + pickResult.getStatus());
                chartList.add(new Chart("", position.get(), round.getRoundNumber(), pickResult.getStatus().toString()));
                position.getAndIncrement();
            });
        });
        return chartList;
    }

    public List<Chart> getChartByPosition() {
        List<Chart> chartList = new ArrayList<>();
        AtomicInteger position = new AtomicInteger(1);
        this.contestantService.getContestantList().forEach(contestant -> {
            //System.out.println("---");
            this.roundService.getRoundList().forEach(round -> {
                PickResult pickResult = this.pickResultService.getPickResult("key_team2", "key_user1", position.get(), round.getRoundNumber());
                //System.out.println("P" + String.format("%02d", position.get()) + "R" + String.format("%02d", round.getRoundNumber()) + ": " + pickResult.getStatus());
                chartList.add(new Chart("", position.get(), round.getRoundNumber(), pickResult.getStatus().toString()));
            });
            position.getAndIncrement();
        });
        return chartList;
    }
}
