package com.leadfinder.leadfinder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenStreetMapService {

    public String buscarEmpresas(String categoria){

        String query = "[out:json];node[\"amenity\"=\"" + categoria + "\"](around:5000,-12.97,-38.50);out;";

        String url = "https://overpass-api.de/api/interpreter?data=" + query;

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }

}