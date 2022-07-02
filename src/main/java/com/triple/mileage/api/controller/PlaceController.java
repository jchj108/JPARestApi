package com.triple.mileage.api.controller;

import com.triple.mileage.api.common.ErrorResponse;
import com.triple.mileage.api.domain.Place;
import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.PlaceRepository;
import com.triple.mileage.api.repository.UserRepository;
import com.triple.mileage.api.service.PlaceService;
import com.triple.mileage.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final PlaceService placeService;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @GetMapping(path = "places/{placeId}")
    public Place one(@PathVariable("placeId") UUID id) {
        return placeService.findOne(id);
    }

    @PostMapping(path = "places")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Place saveMember(@RequestBody @Valid Place place) {
        return placeService.savePlace(place);
    }
}
