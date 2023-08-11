package com.laioffer.fastdelivery.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.laioffer.fastdelivery.exception.GeoCodingException;
import com.laioffer.fastdelivery.exception.InvalidAddressException;
import com.laioffer.fastdelivery.model.Location;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GeoCodingService {

    private final GeoApiContext context;

    public GeoCodingService(GeoApiContext context) {
        this.context = context;
    }

    public Location getLatLng(Long id, String address) {
        try {
            GeocodingResult result = GeocodingApi.geocode(context, address).await()[0];
            if (result.partialMatch) {
                throw new InvalidAddressException("Failed to find the address");
            }
            return new Location(id, new GeoPoint(result.geometry.location.lat, result.geometry.location.lng));
        } catch (IOException | ApiException | InterruptedException e) {
            e.printStackTrace();
            throw new GeoCodingException("Failed to encode the address");
        }
    }
}
