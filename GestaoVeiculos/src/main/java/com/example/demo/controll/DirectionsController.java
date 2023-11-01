package com.example.demo.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;

@RestController
public class DirectionsController {
	
	@Value("${google.maps.api.key}")
    private String apiKey;

    @GetMapping("/directions")
    public DirectionsResult getDirections(
            @RequestParam List<String> waypoints
    ) throws Exception {
        if (waypoints.size() < 2) {
            throw new IllegalArgumentException("Pelo menos dois pontos de parada são necessários.");
        }

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        DirectionsApiRequest request = DirectionsApi.newRequest(context);
        request.origin(waypoints.get(0)); // O primeiro ponto é a origem
        request.destination(waypoints.get(waypoints.size() - 1)); // O último ponto é o destino

        // Adicione os pontos de parada intermediários
        for (int i = 1; i < waypoints.size() - 1; i++) {
            request.waypoints(waypoints.get(i));
        }

        return request.await();
    }
    
}
