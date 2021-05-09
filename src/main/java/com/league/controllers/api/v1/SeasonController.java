package com.league.controllers.api.v1;

import com.league.api.v1.model.SeasonDto;
import com.league.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SeasonDto createSeason(@RequestBody SeasonDto seasonDto){
        return seasonService.save(seasonDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeasonDto updateSeason(@PathVariable("id") Long seasonId, @RequestBody SeasonDto seasonDto){
        return seasonService.update(seasonId, seasonDto);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Set<SeasonDto> findAll(){
        return seasonService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SeasonDto findById(@PathVariable("id") Long seasonId){
        return seasonService.findById(seasonId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long seasonId){
        seasonService.deleteById(seasonId);
    }

}
