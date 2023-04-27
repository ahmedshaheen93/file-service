package com.shaheen.fileservice.controller;

import com.shaheen.fileservice.api.SpaceApi;
import com.shaheen.fileservice.api.model.SpaceAddRequest;
import com.shaheen.fileservice.api.model.SpaceResponse;
import com.shaheen.fileservice.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpaceController implements SpaceApi {
  private final SpaceService spaceService;

  @Override
  public ResponseEntity<SpaceResponse> createSpace(SpaceAddRequest spaceAddRequest) {
    return new ResponseEntity<>(spaceService.createSpace(spaceAddRequest), HttpStatus.CREATED);
  }
}
