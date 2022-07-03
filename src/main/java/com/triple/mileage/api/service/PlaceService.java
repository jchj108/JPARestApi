package com.triple.mileage.api.service;

import com.triple.mileage.api.domain.Place;
import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place savePlace(Place place) {
        validateDuplicatePlace(place);
        placeRepository.save(place);
        return place;
    }

    private void validateDuplicatePlace(Place place) {
        Place findPlace = placeRepository.findOne(place.getId());
        if (findPlace != null) {
            throw new IllegalStateException("이미 존재하는 장소입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<Place> findUsers() {
        return placeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Place findOne(UUID id) {
        Place findPlace = placeRepository.findOne(id);
        if (findPlace == null) {
            throw new IllegalArgumentException("장소가 존재하지 않습니다");
        }
        return findPlace;
    }
}
