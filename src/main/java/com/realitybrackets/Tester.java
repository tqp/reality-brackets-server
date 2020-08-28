package com.realitybrackets;

import com.realitybrackets.data.DataService_BB22;
import com.realitybrackets.services.*;
import com.realitybrackets.utils.PrintObjectService;

public class Tester {
    public static void main(String[] args) {
        PrintObjectService printObjectService = new PrintObjectService();
        DataService_BB22 dataService = new DataService_BB22();

        TeamService teamService = new TeamService(dataService);
        UserService userService = new UserService(dataService);
        ContestantService contestantService = new ContestantService(dataService);
        ResultService resultService = new ResultService(dataService);
        RoundService roundService = new RoundService(dataService, resultService);
        PickService pickService = new PickService(printObjectService, dataService, roundService, resultService);
        PickResultService pickResultService = new PickResultService(pickService, resultService, roundService);
        ChartService chartService = new ChartService(roundService, contestantService, pickResultService);
//        ScoreService scoreService = new ScoreService(pickResultService, userService, roundService);
//        ProjectedScoreService projectedScoreService = new ProjectedScoreService(pickResultService, userService, roundService);
//        BestPickService bestPickService = new BestPickService(dataService, roundService, pickService);


//        printObjectService.PrintObject("test", pickService.getPickList());
        printObjectService.PrintObject("\nResult", chartService.getChartByPosition());
    }


}
