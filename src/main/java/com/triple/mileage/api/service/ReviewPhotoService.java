package com.triple.mileage.api.service;

import com.triple.mileage.api.domain.ReviewPhoto;
import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.ReviewPhotoRepository;
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
public class ReviewPhotoService {

    private final ReviewPhotoRepository reviewPhotoRepository;

    @Transactional(readOnly = true)
    public List<ReviewPhoto> findUsers() {
        return reviewPhotoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ReviewPhoto findOne(UUID id) {
        return reviewPhotoRepository.findOne(id);
    }
}
