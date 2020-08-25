package com.realitybrackets.services;

import com.realitybrackets.beans.Pick;
import com.realitybrackets.beans.Team;
import com.realitybrackets.beans.User;
import com.realitybrackets.data.DataService_BB22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private DataService_BB22 dataService;

    @Autowired
    public UserService(DataService_BB22 dataService) {
        this.dataService = dataService;
    }

    public List<User> getUserList() {
        return new ArrayList<>(this.dataService.getUsers());
    }

    public User getUserByUserKey(String userKey) {
        return this.dataService.getUsers().stream()
                .filter(user -> user.getUserKey().equalsIgnoreCase(userKey))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUserListByTeamKey(String teamKey) {
        // Gets a list of Users for a given Team, using Picks
        return this.dataService.getPicks().stream()
                .filter(pick -> pick.getTeamKey().equalsIgnoreCase(teamKey))
                .map(Pick::getUserKey)
                .distinct()
                .map(this::getUserByUserKey)
                .collect(Collectors.toList());
    }
}
