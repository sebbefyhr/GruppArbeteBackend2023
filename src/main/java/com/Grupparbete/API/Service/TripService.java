package com.Grupparbete.API.Service;



import com.Grupparbete.API.Entities.Trip;

import java.io.IOException;
import java.util.List;

public interface TripService {
    List<Trip> findAll();
    Trip findById(int id);
    Trip save(Trip trip);
    Trip update(int id, Trip trip);
    void deleteById(int id);
    List<Trip> inspectTripList(List<Trip> tripList, int customerId);
    List<Trip> findTripsByCustomerId(int customerId);
    Trip makeSureCurrencyIsUpdated(Trip trip, boolean autoSave) throws IOException;
}
