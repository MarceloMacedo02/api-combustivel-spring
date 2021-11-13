package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.model.Route;
import br.com.zonesoftware.apicombustivel.service.integration.RequestService;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("route")
public class RouteController {

    @Autowired
    private RequestService requestService;

    @GetMapping("{deviceId}")
    public List<Route> traceRoute (@PathVariable("deviceId") Integer deviceId,
                                   @PathParam("from") String from, @PathParam("to") String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime fromDateTime = LocalDateTime.parse(from.concat(":01"), formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(to.concat(":59"), formatter);

        String params = String.format("deviceId=%d&from=%s&to=%s", deviceId, fromDateTime, toDateTime);

        Route[] routes = requestService
                .request("reports/route/?" + params, Route[].class)
                .getBody();

        List<Route> coordinates = Arrays.asList(routes);

        return coordinates;
    }

}
