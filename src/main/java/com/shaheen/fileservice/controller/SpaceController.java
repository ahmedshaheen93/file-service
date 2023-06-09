package com.shaheen.fileservice.controller;

import com.shaheen.fileservice.api.SpacesApi;
import com.shaheen.fileservice.api.model.SpaceAddRequest;
import com.shaheen.fileservice.api.model.SpaceResponse;
import com.shaheen.fileservice.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpaceController implements SpacesApi {
  private final FileSystemService fileSystemService;

  @Override
  public ResponseEntity<SpaceResponse> createSpace(SpaceAddRequest spaceAddRequest) {
    return new ResponseEntity<>(fileSystemService.createSpace(spaceAddRequest), HttpStatus.CREATED);
  }
}
