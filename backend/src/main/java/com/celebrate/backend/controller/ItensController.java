package com.celebrate.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celebrate.backend.models.Dto.CreateItem;
import com.celebrate.backend.service.ItensService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItensController {
    
    private final ItensService itensService;

    @PostMapping()
    public void createItem(@RequestBody CreateItem request){

        itensService.createItem(request);
    }
}
