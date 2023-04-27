package com.shaheen.fileservice.service;

import com.shaheen.fileservice.api.model.FolderAddRequest;
import com.shaheen.fileservice.api.model.FolderResponse;
import com.shaheen.fileservice.api.model.SpaceAddRequest;
import com.shaheen.fileservice.api.model.SpaceResponse;
import com.shaheen.fileservice.model.GroupPermission;
import com.shaheen.fileservice.model.Item;
import com.shaheen.fileservice.model.Type;
import com.shaheen.fileservice.model.UserPermission;
import com.shaheen.fileservice.repository.GroupPermissionRepository;
import com.shaheen.fileservice.repository.ItemRepository;
import com.shaheen.fileservice.repository.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {
  private final GroupPermissionRepository groupPermissionRepository;
  private final UserPermissionRepository userPermissionRepository;
  private final ItemRepository itemRepository;

  @Override
  public SpaceResponse createSpace(SpaceAddRequest request) {
    Integer groupPermissionId = request.getGroupPermissionId();
    Integer userPermissionId = request.getUserPermissionId();
    Optional<GroupPermission> groupPermission = getGroupPermission(groupPermissionId);
    Optional<UserPermission> userPermission = getUserPermission(userPermissionId);
    Item item = createItem(request.getName(), null, userPermission.get(), groupPermission.get(), Type.SPACE);
    return new SpaceResponse().name(item.getName()).id(item.getId()).groupPermissionId(groupPermissionId).userPermissionId(groupPermissionId);
  }

  @Override
  public FolderResponse createFolder(Integer spaceId, FolderAddRequest request) {
    Optional<Item> optionalSpace = itemRepository.findByIdAndType(spaceId,Type.SPACE);
    if (optionalSpace.isEmpty()) {
      throw new RuntimeException("not found");
    }
    Item parent = optionalSpace.get();

    Optional<UserPermission> userPermissionOptional = getUserPermission(request.getUserPermissionId());
    Optional<GroupPermission> groupPermissionOptional = getGroupPermission(request.getGroupPermissionId());
    UserPermission userPermission = userPermissionOptional.orElseGet(parent::getUserPermission);
    GroupPermission groupPermission = groupPermissionOptional.orElseGet(parent::getGroupPermission);

    Item item = createItem(request.getName(), parent, userPermission, groupPermission, Type.FOLDER);

    return new FolderResponse()
        .id(item.getId())
        .name(item.getName())
        .parentId(parent.getId())
        .userPermissionId(userPermission.getId())
        .groupPermissionId(groupPermission.getId());
  }

  private Item createItem(String itemName, Item parent, UserPermission userPermission, GroupPermission groupPermission, Type type) {
    Item item = new Item();
    item.setName(itemName);
    item.setParent(parent);
    item.setType(type);
    item.setUserPermission(userPermission);
    item.setGroupPermission(groupPermission);
    return itemRepository.save(item);
  }

  private Optional<UserPermission> getUserPermission(Integer userPermissionId) {
    Optional<UserPermission> optionalUserPermission = Optional.empty();
    if (!ObjectUtils.isEmpty(userPermissionId)) {
      optionalUserPermission = userPermissionRepository.findById(userPermissionId);
      if (optionalUserPermission.isEmpty()) {
        throw new RuntimeException("not found");
      }
    }
    return optionalUserPermission;
  }

  private Optional<GroupPermission> getGroupPermission(Integer groupPermissionId) {
    Optional<GroupPermission> optionalGroupPermission = Optional.empty();
    if (!ObjectUtils.isEmpty(groupPermissionId)) {
      optionalGroupPermission = groupPermissionRepository.findById(groupPermissionId);
      if (optionalGroupPermission.isEmpty()) {
        throw new RuntimeException("not found");
      }
    }
    return optionalGroupPermission;
  }
}
