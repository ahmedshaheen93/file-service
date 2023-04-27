package com.shaheen.fileservice.service;

import com.shaheen.fileservice.api.model.FolderAddRequest;
import com.shaheen.fileservice.api.model.FolderResponse;
import com.shaheen.fileservice.api.model.SpaceAddRequest;
import com.shaheen.fileservice.api.model.SpaceResponse;

public interface SpaceService {
  SpaceResponse createSpace(SpaceAddRequest request);
  FolderResponse createFolder(Integer spaceId,FolderAddRequest request);

}
