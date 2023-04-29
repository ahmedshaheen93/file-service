package com.shaheen.fileservice.controller;

import com.shaheen.fileservice.api.FilesApi;
import com.shaheen.fileservice.api.model.FileAddRequest;
import com.shaheen.fileservice.api.model.FileResponse;
import com.shaheen.fileservice.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequiredArgsConstructor
public class FileController implements FilesApi {
  private final FileSystemService fileSystemService;
  @Override
  public ResponseEntity<FileResponse> createFile(FileAddRequest fileAddRequest) {
    return new ResponseEntity<>(fileSystemService.createFile(fileAddRequest), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<FileResponse> getFile(Integer fileId) {
    return new ResponseEntity<>(fileSystemService.getFile(fileId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Resource> downloadFile(Integer fileId) {
    FileResponse file = fileSystemService.getFile(fileId);
    String base64Content = file.getContent();
    byte[] fileContent = Base64.getDecoder().decode(base64Content);
    ByteArrayResource resource = new ByteArrayResource(fileContent);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
        .contentLength(fileContent.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
  }
}
