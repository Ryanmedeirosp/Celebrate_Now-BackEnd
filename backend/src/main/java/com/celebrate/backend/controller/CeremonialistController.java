package com.celebrate.backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.dto.CreateCeremonialist;
import com.celebrate.backend.service.CeremonialistService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ceremonialist")
public class CeremonialistController {

    private final CeremonialistService ceremonialistService;

    @PostMapping()
    public void createCeremonialist(@RequestBody CreateCeremonialist request){
        
        ceremonialistService.createCeremonialist(request);
    }

    @PutMapping("/{ceremonialistId}")
    public void updateCeremonialisById(@PathVariable Integer ceremonialistId, @RequestBody CreateCeremonialist request){

        ceremonialistService.updateCeremonialisById(ceremonialistId, request);
    }
}
