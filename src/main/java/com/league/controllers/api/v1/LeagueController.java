package com.league.controllers.api.v1;

import com.league.api.v1.model.LeagueDto;
import com.league.service.LeagueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/leagues/")
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public LeagueDto createLeague(@RequestBody LeagueDto leagueDto){
        return leagueService.save(leagueDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeagueDto updateLeague(@PathVariable("id") Long leagueId, @RequestBody LeagueDto leagueDto){
        return leagueService.update(leagueId, leagueDto);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Set<LeagueDto> findAll(){
        return leagueService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeagueDto findByID(@PathVariable("id") Long leagueId){
        return leagueService.findById(leagueId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long leagueId){
        leagueService.findById(leagueId);
    }
}
