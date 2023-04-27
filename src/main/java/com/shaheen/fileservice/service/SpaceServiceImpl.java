package com.shaheen.fileservice.service;

import com.shaheen.fileservice.api.model.SpaceAddRequest;
import com.shaheen.fileservice.api.model.SpaceResponse;
import com.shaheen.fileservice.model.Item;
import com.shaheen.fileservice.model.PermissionGroup;
import com.shaheen.fileservice.model.Type;
import com.shaheen.fileservice.repository.ItemRepository;
import com.shaheen.fileservice.repository.PermissionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {
  private final PermissionGroupRepository permissionGroupRepository;
  private final ItemRepository itemRepository;

  @Override
  public SpaceResponse createSpace(SpaceAddRequest request) {
    Optional<PermissionGroup> optionalPermissionGroup = permissionGroupRepository.findById(request.getPermissionGroupId());
    if (optionalPermissionGroup.isPresent()) {
      PermissionGroup permissionGroup = optionalPermissionGroup.get();
      Item item = new Item();
      item.setName(request.getName());
      item.setType(Type.SPACE);
      item.setPermissionGroup(permissionGroup);
      item = itemRepository.save(item);
      return new SpaceResponse().name(item.getName()).id(item.getId()).permissionGroupId(permissionGroup.getId());
    } else {
      throw new RuntimeException("not found");
    }

  }
}
