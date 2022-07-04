package com.triple.mileage.api.controller;

import com.triple.mileage.api.common.ErrorResponse;
import com.triple.mileage.api.dto.PlaceDto;
import com.triple.mileage.api.repository.PlaceRepository;
import com.triple.mileage.api.service.PlaceService;
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
    public ResponseEntity one(@PathVariable("placeId") UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PlaceDto.Res(placeService.findOne(id)));
    }

    @PostMapping(path = "places")
    public ResponseEntity savePlace(@RequestBody @Valid PlaceDto.PlaceRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PlaceDto.Res(placeService.savePlace(dto)));
    }
}
