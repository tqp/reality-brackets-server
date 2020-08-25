package com.realitybrackets.controllers;

import com.realitybrackets.beans.Contestant;
import com.realitybrackets.services.ContestantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reality-tracker/api/v1/contestants")
@Tag(name = "Reality-Tracker Comp: ContestantController", description = "Contestant Endpoints")
public class ContestantController {
    private final ContestantService contestantService;

    @Autowired
    public ContestantController(ContestantService contestantService) {
        this.contestantService = contestantService;
    }

    @GetMapping(value = "/", produces = "application/json")
    @Operation(summary = "getContestantList", description = "Get Contestant List", security = @SecurityRequirement(name = "bearerAuth"))
    public List<Contestant> getContestantList() {
        return this.contestantService.getContestantList();
    }

    @GetMapping(value = "/{contestantKey}", produces = "application/json")
    @Operation(summary = "getContestantListByContestantKey", description = "Get Contestant List by Contestant Key", security = @SecurityRequirement(name = "bearerAuth"))
    public Contestant getContestantListByContestantKey(@Parameter(description = "Contestant Key", example = "key_Madison") @PathVariable("contestantKey") String contestantKey) {
        return this.contestantService.getContestantByContestantKey(contestantKey);
    }

}
