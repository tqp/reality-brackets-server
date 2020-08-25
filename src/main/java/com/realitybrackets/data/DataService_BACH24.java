package com.realitybrackets.data;

import com.realitybrackets.beans.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService_BACH24 {

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("key_team1", "Team 1"));
        return teams;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("key_user1", "Tim"));
        users.add(new User("key_user2", "Jess"));
        users.add(new User("key_user3", "Internet"));
        return users;
    }

    public List<Round> getRounds() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round(1, 30, (double) 0));
        rounds.add(new Round(2, 18, (double) 2));
        rounds.add(new Round(3, 15, (double) 3));
        rounds.add(new Round(4, 12, (double) 4));
        rounds.add(new Round(5, 9, (double) 5));
        rounds.add(new Round(6, 6, (double) 6));
        rounds.add(new Round(7, 4, (double) 10));
        rounds.add(new Round(8, 3, (double) 15));
        rounds.add(new Round(9, 2, (double) 20));
        rounds.add(new Round(10, 1, (double) 30));
        return rounds;
    }

    public List<Pick> getPicks() {
        List<Pick> picks = new ArrayList<>();

        picks.add(new Pick("key_group1", "key_user1", 1, "key_Madison"));
        picks.add(new Pick("key_group1", "key_user1", 2, "key_Hannah Anne"));
        picks.add(new Pick("key_group1", "key_user1", 3, "key_Savannah"));
        picks.add(new Pick("key_group1", "key_user1", 4, "key_Victoria P."));
        picks.add(new Pick("key_group1", "key_user1", 5, "key_Kelsey"));
        picks.add(new Pick("key_group1", "key_user1", 6, "key_Mykenna"));
        picks.add(new Pick("key_group1", "key_user1", 7, "key_Lexi"));
        picks.add(new Pick("key_group1", "key_user1", 8, "key_Kelley"));
        picks.add(new Pick("key_group1", "key_user1", 9, "key_Natasha"));
        picks.add(new Pick("key_group1", "key_user1", 10, "key_Payton"));
        picks.add(new Pick("key_group1", "key_user1", 11, "key_Sydney"));
        picks.add(new Pick("key_group1", "key_user1", 12, "key_Alayah"));
        picks.add(new Pick("key_group1", "key_user1", 13, "key_Sarah"));
        picks.add(new Pick("key_group1", "key_user1", 14, "key_Courtney"));
        picks.add(new Pick("key_group1", "key_user1", 15, "key_Tammy"));
        picks.add(new Pick("key_group1", "key_user1", 16, "key_Victoria F."));
        picks.add(new Pick("key_group1", "key_user1", 17, "key_Alexa"));
        picks.add(new Pick("key_group1", "key_user1", 18, "key_Jasmine"));
        picks.add(new Pick("key_group1", "key_user1", 19, "key_Lauren"));
        picks.add(new Pick("key_group1", "key_user1", 20, "key_Deandra"));
        picks.add(new Pick("key_group1", "key_user1", 21, "key_Kiarra"));
        picks.add(new Pick("key_group1", "key_user1", 22, "key_Shiann"));

        picks.add(new Pick("key_group1", "key_user2", 1, "key_Savannah"));
        picks.add(new Pick("key_group1", "key_user2", 2, "key_Hannah Anne"));
        picks.add(new Pick("key_group1", "key_user2", 3, "key_Sydney"));
        picks.add(new Pick("key_group1", "key_user2", 4, "key_Victoria P."));
        picks.add(new Pick("key_group1", "key_user2", 5, "key_Kelsey"));
        picks.add(new Pick("key_group1", "key_user2", 6, "key_Mykenna"));
        picks.add(new Pick("key_group1", "key_user2", 7, "key_Alayah"));
        picks.add(new Pick("key_group1", "key_user2", 8, "key_Alexa"));
        picks.add(new Pick("key_group1", "key_user2", 9, "key_Victoria F."));
        picks.add(new Pick("key_group1", "key_user2", 10, "key_Kelley"));
        picks.add(new Pick("key_group1", "key_user2", 11, "key_Lexi"));
        picks.add(new Pick("key_group1", "key_user2", 12, "key_Natasha"));
        picks.add(new Pick("key_group1", "key_user2", 13, "key_Courtney"));
        picks.add(new Pick("key_group1", "key_user2", 14, "key_Lauren"));
        picks.add(new Pick("key_group1", "key_user2", 15, "key_Jasmine"));
        picks.add(new Pick("key_group1", "key_user2", 16, "key_Deandra"));
        picks.add(new Pick("key_group1", "key_user2", 17, "key_Madison"));
        picks.add(new Pick("key_group1", "key_user2", 18, "key_Payton"));
        picks.add(new Pick("key_group1", "key_user2", 19, "key_Sarah"));
        picks.add(new Pick("key_group1", "key_user2", 20, "key_Tammy"));
        picks.add(new Pick("key_group1", "key_user2", 21, "key_Kiarra"));
        picks.add(new Pick("key_group1", "key_user2", 22, "key_Shiann"));

        picks.add(new Pick("key_group1", "key_user3", 1, "key_Hannah Anne"));
        picks.add(new Pick("key_group1", "key_user3", 2, "key_Madison"));
        picks.add(new Pick("key_group1", "key_user3", 3, "key_Kelley"));
        picks.add(new Pick("key_group1", "key_user3", 4, "key_Victoria P."));
        picks.add(new Pick("key_group1", "key_user3", 5, "key_Alayah"));
        picks.add(new Pick("key_group1", "key_user3", 6, "key_Mykenna"));
        picks.add(new Pick("key_group1", "key_user3", 7, "key_Tammy"));
        picks.add(new Pick("key_group1", "key_user3", 8, "key_Lexi"));
        picks.add(new Pick("key_group1", "key_user3", 9, "key_Sydney"));
        picks.add(new Pick("key_group1", "key_user3", 10, "key_Alexa"));
        picks.add(new Pick("key_group1", "key_user3", 11, "key_Kiarra"));
        picks.add(new Pick("key_group1", "key_user3", 12, "key_Natasha"));
        picks.add(new Pick("key_group1", "key_user3", 13, "key_Deandra"));
        picks.add(new Pick("key_group1", "key_user3", 14, "key_Jasmine"));
        picks.add(new Pick("key_group1", "key_user3", 15, "key_Courtney"));
        picks.add(new Pick("key_group1", "key_user3", 16, "key_Lauren"));
        picks.add(new Pick("key_group1", "key_user3", 17, "key_Sarah"));
        picks.add(new Pick("key_group1", "key_user3", 18, "key_Kelsey"));
        picks.add(new Pick("key_group1", "key_user3", 19, "key_Savannah"));
        picks.add(new Pick("key_group1", "key_user3", 20, "key_Shiann"));
        picks.add(new Pick("key_group1", "key_user3", 21, "key_Victoria F."));
        picks.add(new Pick("key_group1", "key_user3", 22, "key_Payton"));

        return picks;
    }

    public List<Contestant> getContestants() {
        List<Contestant> contestant = new ArrayList<>();
        contestant.add(new Contestant("key_Alayah", "Alayah"));
        contestant.add(new Contestant("key_Alexa", "Alexa"));
        contestant.add(new Contestant("key_Avonlea", "Avonlea"));
        contestant.add(new Contestant("key_Courtney", "Courtney"));
        contestant.add(new Contestant("key_Deandra", "Deandra"));
        contestant.add(new Contestant("key_Eunice", "Eunice"));
        contestant.add(new Contestant("key_Hannah Anne", "Hannah Anne"));
        contestant.add(new Contestant("key_Jade", "Jade"));
        contestant.add(new Contestant("key_Jasmine", "Jasmine"));
        contestant.add(new Contestant("key_Jenna", "Jenna"));
        contestant.add(new Contestant("key_Katrina", "Katrina"));
        contestant.add(new Contestant("key_Kelley", "Kelley"));
        contestant.add(new Contestant("key_Kelsey", "Kelsey"));
        contestant.add(new Contestant("key_Kiarra", "Kiarra"));
        contestant.add(new Contestant("key_Kylie", "Kylie"));
        contestant.add(new Contestant("key_Lauren", "Lauren"));
        contestant.add(new Contestant("key_Lexi", "Lexi"));
        contestant.add(new Contestant("key_Madison", "Madison"));
        contestant.add(new Contestant("key_Maurissa", "Maurissa"));
        contestant.add(new Contestant("key_Megan", "Megan"));
        contestant.add(new Contestant("key_Mykenna", "Mykenna"));
        contestant.add(new Contestant("key_Natasha", "Natasha"));
        contestant.add(new Contestant("key_Payton", "Payton"));
        contestant.add(new Contestant("key_Sarah", "Sarah"));
        contestant.add(new Contestant("key_Savannah", "Savannah"));
        contestant.add(new Contestant("key_Shiann", "Shiann"));
        contestant.add(new Contestant("key_Sydney", "Sydney"));
        contestant.add(new Contestant("key_Tammy", "Tammy"));
        contestant.add(new Contestant("key_Victoria F.", "Victoria F."));
        contestant.add(new Contestant("key_Victoria P.", "Victoria P."));
        return contestant;
    }

    public List<Result> getResults() {
        List<Result> results = new ArrayList<>();

        // Round 1
        results.add(new Result(1, "key_Hannah Anne", 1));
        results.add(new Result(1, "key_Victoria P.", 2));
        results.add(new Result(1, "key_Madison", 3));
        results.add(new Result(1, "key_Kelley", 4));
        results.add(new Result(1, "key_Lexi", 5));
        results.add(new Result(1, "key_Savannah", 6));
        results.add(new Result(1, "key_Lauren", 7));
        results.add(new Result(1, "key_Tammy", 8));
        results.add(new Result(1, "key_Alayah", 9));
        results.add(new Result(1, "key_Jasmine", 10));
        results.add(new Result(1, "key_Sydney", 11));
        results.add(new Result(1, "key_Natasha", 12));
        results.add(new Result(1, "key_Mykenna", 13));
        results.add(new Result(1, "key_Deandra", 14));
        results.add(new Result(1, "key_Sarah", 15));
        results.add(new Result(1, "key_Alexa", 16));
        results.add(new Result(1, "key_Kelsey", 17));
        results.add(new Result(1, "key_Payton", 18));
        results.add(new Result(1, "key_Kiarra", 19));
        results.add(new Result(1, "key_Courtney", 20));
        results.add(new Result(1, "key_Shiann", 21));
        results.add(new Result(1, "key_Victoria F.", 22));

        // Round 2
        results.add(new Result(2, "key_Kelley", 1));
        results.add(new Result(2, "key_Madison", 2));
        results.add(new Result(2, "key_Sydney", 3));
        results.add(new Result(2, "key_Mykenna", 4));
        results.add(new Result(2, "key_Victoria P.", 5));
        results.add(new Result(2, "key_Natasha", 6));
        results.add(new Result(2, "key_Jasmine", 7));
        results.add(new Result(2, "key_Sarah", 8));
        results.add(new Result(2, "key_Lexi", 9));
        results.add(new Result(2, "key_Hannah Anne", 10));
        results.add(new Result(2, "key_Alexa", 11));
        results.add(new Result(2, "key_Tammy", 12));
        results.add(new Result(2, "key_Alayah", 13));
        results.add(new Result(2, "key_Deandra", 14));
        results.add(new Result(2, "key_Victoria F.", 15));
        results.add(new Result(2, "key_Shiann", 16));
        results.add(new Result(2, "key_Kiarra", 17));
        results.add(new Result(2, "key_Savannah", 18));
        results.add(new Result(2, "key_Kelsey", 19));

        // Round 3
        results.add(new Result(3, "key_Victoria F.", 1));
        results.add(new Result(3, "key_Victoria P.", 2));
        results.add(new Result(3, "key_Sydney", 3));
        results.add(new Result(3, "key_Kelsey", 4));
        results.add(new Result(3, "key_Hannah Anne", 5));
        results.add(new Result(3, "key_Natasha", 6));
        results.add(new Result(3, "key_Lexi", 7));
        results.add(new Result(3, "key_Madison", 8));
        results.add(new Result(3, "key_Shiann", 9));
        results.add(new Result(3, "key_Kelley", 10));
        results.add(new Result(3, "key_Kiarra", 11));
        results.add(new Result(3, "key_Tammy", 12));
        results.add(new Result(3, "key_Savannah", 13));
        results.add(new Result(3, "key_Deandra", 14));
        results.add(new Result(3, "key_Mykenna", 15));

        // Round 4
        results.add(new Result(4, "key_Victoria F.", 1));
        results.add(new Result(4, "key_Kelsey", 2));
        results.add(new Result(4, "key_Madison", 3));
        results.add(new Result(4, "key_Sydney", 4));
        results.add(new Result(4, "key_Natasha", 5));
        results.add(new Result(4, "key_Lexi", 6));
        results.add(new Result(4, "key_Hannah Anne", 7));
        results.add(new Result(4, "key_Shiann", 8));
        results.add(new Result(4, "key_Mykenna", 9));
        results.add(new Result(4, "key_Victoria P.", 10));
        results.add(new Result(4, "key_Kelley", 11));
        results.add(new Result(4, "key_Tammy", 12));

//        // Round 5
//        results.add(new Result(5, "key_Sydney", 1));
//        results.add(new Result(5, "key_Hannah Anne", 2));
//        results.add(new Result(5, "key_Kelley", 3));
//        results.add(new Result(5, "key_Kelsey", 4));
//        results.add(new Result(5, "key_Victoria F.", 5));
//        results.add(new Result(5, "key_Madison", 6));
//        results.add(new Result(5, "key_Natasha", 7));
//        results.add(new Result(5, "key_Victoria P.", 8));
//        results.add(new Result(5, "key_Mykenna", 9));
//        results.add(new Result(5, "key_Tammy", 10));
//
//        // Round 6
//        results.add(new Result(6, "key_Hannah Anne", 1));
//        results.add(new Result(6, "key_Madison", 2));
//        results.add(new Result(6, "key_Victoria F.", 3));
//        results.add(new Result(6, "key_Kelsey", 4));
//        results.add(new Result(6, "key_Natasha", 5));
//        results.add(new Result(6, "key_Kelley", 6));
//
//        // Round 7
//        results.add(new Result(7, "key_Madison", 1));
//        results.add(new Result(7, "key_Kelsey", 2));
//        results.add(new Result(7, "key_Victoria F.", 3));
//        results.add(new Result(7, "key_Hannah Anne", 4));
//
//        // Round 8
//        results.add(new Result(8, "key_Hannah Anne", 1));
//        results.add(new Result(8, "key_Madison", 2));
//        results.add(new Result(8, "key_Victoria F.", 3));

        return results;
    }


}
