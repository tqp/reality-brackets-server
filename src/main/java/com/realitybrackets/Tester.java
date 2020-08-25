package com.realitybrackets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realitybrackets.data.DataService_BB22;
import com.realitybrackets.services.*;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) {
        Tester tester = new Tester();
        DataService_BB22 dataService = new DataService_BB22();

        TeamService teamService = new TeamService(dataService);
        UserService userService = new UserService(dataService);
        RoundService roundService = new RoundService(dataService);
        ResultService resultService = new ResultService(dataService);
        PickService pickService = new PickService(dataService, roundService);
        PickResultService pickResultService = new PickResultService(dataService, pickService, resultService, roundService);
        ScoreService scoreService = new ScoreService(pickResultService, userService, roundService);
        ProjectedScoreService projectedScoreService = new ProjectedScoreService(pickResultService, userService, roundService);
        BestPickService bestPickService = new BestPickService(dataService, roundService, pickService);

//        tester.PrintObject("\nResult", bestPickService.getBestPicksTest());
    }

    public void PrintObject(String title, Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(title + ":\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
