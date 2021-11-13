package br.com.zonesoftware.apicombustivel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zonesoftware.apicombustivel.model.Summary;
import br.com.zonesoftware.apicombustivel.service.integration.RequestService;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("summary")
public class ReportSummaryController {

    final BigDecimal CONVERT_KNOTS_TO_KM = new BigDecimal(1.852000);
    final BigDecimal CONVERT_M_TO_KM = new BigDecimal(1000);

    @Autowired
    private RequestService requestService;

    @GetMapping("{deviceId}")
    public Summary getSummary (@PathVariable("deviceId") Integer deviceId,
                               @PathParam("from") String from, @PathParam("to") String to) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime fromDateTime = LocalDateTime.parse(from.concat(":01"), formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(to.concat(":59"), formatter);

        String params = String.format("deviceId=%d&from=%s&to=%s", deviceId, fromDateTime, toDateTime);

        var summaries = requestService
                .request("reports/summary/?" + params, Summary[].class)
                .getBody();

        var summary = summaries[0];
        summary.setDistance(summary.getDistance().divide(CONVERT_M_TO_KM));
        summary.setMaxSpeed(summary.getMaxSpeed().multiply(CONVERT_KNOTS_TO_KM));
        summary.setAverageSpeed(summary.getAverageSpeed().multiply(CONVERT_KNOTS_TO_KM));

        return summaries[0];

    }

}
