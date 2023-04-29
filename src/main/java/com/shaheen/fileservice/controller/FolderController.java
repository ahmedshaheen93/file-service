package com.shaheen.fileservice.controller;

import com.shaheen.fileservice.api.FoldersApi;
import com.shaheen.fileservice.api.model.FolderAddRequest;
import com.shaheen.fileservice.api.model.FolderResponse;
import com.shaheen.fileservice.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FolderController implements FoldersApi {
  private final FileSystemService fileSystemService;

  @Override
  public ResponseEntity<FolderResponse> createFolder(FolderAddRequest folderAddRequest) {
    return new ResponseEntity<>(fileSystemService.createFolder(folderAddRequest), HttpStatus.CREATED);
  }

}
