package com.shaheen.fileservice.service;

import com.shaheen.fileservice.api.model.*;

public interface FileSystemService {
  SpaceResponse createSpace(SpaceAddRequest request);
  FolderResponse createFolder(FolderAddRequest request);

  FileResponse createFile(FileAddRequest request);
  FileResponse getFile(Integer fileId);


}
