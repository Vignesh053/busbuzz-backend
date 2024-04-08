package com.origami.busbuzz.controller;

import com.origami.busbuzz.dto.RouteDto;
import com.origami.busbuzz.entity.Route;
import com.origami.busbuzz.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("route")
public class RouteController {

    private RouteService routeService;


    @GetMapping("/getroutes")
    public ResponseEntity<List<RouteDto>> getRoutes (){
        return new ResponseEntity<>(routeService.getAllRoutes(), HttpStatus.OK);
    }
}
