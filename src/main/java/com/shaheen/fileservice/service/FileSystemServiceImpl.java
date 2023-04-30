package com.shaheen.fileservice.service;

import com.shaheen.fileservice.api.model.*;
import com.shaheen.fileservice.controller.FileController;
import com.shaheen.fileservice.controller.FolderController;
import com.shaheen.fileservice.controller.SpaceController;
import com.shaheen.fileservice.errorhandling.BadRequestException;
import com.shaheen.fileservice.errorhandling.NotFoundException;
import com.shaheen.fileservice.model.File;
import com.shaheen.fileservice.model.*;
import com.shaheen.fileservice.repository.FileRepository;
import com.shaheen.fileservice.repository.GroupPermissionRepository;
import com.shaheen.fileservice.repository.ItemRepository;
import com.shaheen.fileservice.repository.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class FileSystemServiceImpl implements FileSystemService {
  private final GroupPermissionRepository groupPermissionRepository;
  private final UserPermissionRepository userPermissionRepository;
  private final ItemRepository itemRepository;
  private final FileRepository fileRepository;


  @Override
  public SpaceResponse createSpace(SpaceAddRequest request) {
    Integer groupPermissionId = request.getGroupPermissionId();
    Integer userPermissionId = request.getUserPermissionId();
    GroupPermission groupPermission = getGroupPermission(groupPermissionId).orElseThrow(() -> new NotFoundException(String.format("Group Permission with id '%s' was not founded", groupPermissionId)));
    UserPermission userPermission = getUserPermission(userPermissionId).orElseThrow(() -> new NotFoundException(String.format("User Permission with id '%s' was not founded", userPermissionId)));
    Item item = createItem(request.getName(), null, userPermission, groupPermission, Type.SPACE);
    return new SpaceResponse()
        .name(item.getName())
        .id(item.getId())
        .groupPermissionId(groupPermissionId)
        .userPermissionId(groupPermissionId)
        .location(getItemLocation(item))
        .links(generateSpaceLinks(request));
  }

  @Override
  public FolderResponse createFolder(FolderAddRequest request) {
    Item parent = getParent(request.getParentId(), List.of(Type.SPACE, Type.FOLDER));
    itemRepository.findByNameAndParent_IdAndType(request.getName(), parent.getId(), Type.FOLDER).ifPresent(item -> {
      throw new BadRequestException(String.format("folder with name '%s' was founded in same dir", request.getName()));
    });
    Optional<UserPermission> userPermissionOptional = getUserPermission(request.getUserPermissionId());
    Optional<GroupPermission> groupPermissionOptional = getGroupPermission(request.getGroupPermissionId());
    UserPermission userPermission = userPermissionOptional.orElseGet(parent::getUserPermission);
    GroupPermission groupPermission = groupPermissionOptional.orElseGet(parent::getGroupPermission);

    Item item = createItem(request.getName(), parent, userPermission, groupPermission, Type.FOLDER);

    return new FolderResponse()
        .id(item.getId())
        .name(item.getName())
        .userPermissionId(userPermission.getId())
        .parentId(parent.getId())
        .groupPermissionId(groupPermission.getId())
        .location(getItemLocation(item))
        .links(generateFolderLinks(request));
  }


  @Override
  @Transactional
  public FileResponse createFile(FileAddRequest request) {
    Item parent = getParent(request.getParentId(), List.of(Type.SPACE, Type.FOLDER));
    GroupPermission groupPermission = getGroupPermission(request.getGroupPermissionId()).orElseGet(parent::getGroupPermission);
    UserPermission userPermission = getUserPermission(request.getUserPermissionId()).orElseGet(parent::getUserPermission);
    itemRepository.findByNameAndParent_IdAndType(request.getName(), parent.getId(), Type.FILE).ifPresent(item -> {
      throw new BadRequestException(String.format("File with name '%s' was founded in same folder", request.getName()));
    });

    Item item = createItem(request.getName(), parent, userPermission, groupPermission, Type.FILE);
    File file = new File();
    file.setContent(request.getContent());
    file.setItem(item);
    item.setFile(file);
    file = fileRepository.save(file);
    return new FileResponse()
        .id(file.getId())
        .name(request.getName())
        .groupPermissionId(groupPermission.getId())
        .userPermissionId(userPermission.getId())
        .parentId(parent.getId())
        .content(request.getContent())
        .location(getItemLocation(item))
        .links(generateFileLinks(request, file.getId()));
  }

  @Override
  public FileResponse getFile(Integer fileId) {
    File file = fileRepository.findById(fileId).orElseThrow(() -> new NotFoundException(String.format("File with id '%s' was not founded", fileId)));
    Item item = file.getItem();
    return new FileResponse()
        .id(file.getId())
        .name(item.getName())
        .groupPermissionId(item.getGroupPermission().getId())
        .userPermissionId(item.getUserPermission().getId())
        .parentId(item.getParent().getId())
        .content(file.getContent())
        .location(getItemLocation(item));
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
    }
    return optionalUserPermission;
  }

  private Optional<GroupPermission> getGroupPermission(Integer groupPermissionId) {
    Optional<GroupPermission> optionalGroupPermission = Optional.empty();
    if (!ObjectUtils.isEmpty(groupPermissionId)) {
      optionalGroupPermission = groupPermissionRepository.findById(groupPermissionId);
    }
    return optionalGroupPermission;
  }

  private Item getParent(Integer parentId, List<Type> parentTypes) {
    Optional<Item> optionalParent = itemRepository.findByIdAndTypeIn(parentId, parentTypes);
    return optionalParent.orElseThrow(() -> new NotFoundException(String.format("Parent with id '%s' was not founded", parentId)));
  }

  private String getItemLocation(Item item) {
    String separator = "/";
    String location = "/";
    if (!ObjectUtils.isEmpty(item.getParent())) {
      String parentLocation = getItemLocation(item.getParent());
      location = parentLocation + separator + item.getName();
    } else {
      location = location + item.getName();
    }
    return location;
  }

  private List<Link> generateSpaceLinks(SpaceAddRequest request) {
    org.springframework.hateoas.Link link = linkTo(methodOn(SpaceController.class)
        .createSpace(request)).withSelfRel();
    String href = link.getHref();
    Link self = new Link().rel("self").href(href);
    return List.of(self);
  }

  private List<Link> generateFolderLinks(FolderAddRequest request) {
    org.springframework.hateoas.Link link = linkTo(methodOn(FolderController.class)
        .createFolder(request)).withSelfRel();
    String href = link.getHref();
    Link self = new Link().rel("self").href(href);
    return List.of(self);
  }

  private List<Link> generateFileLinks(FileAddRequest request, Integer fileId) {
    org.springframework.hateoas.Link selfLink = linkTo(methodOn(FileController.class)
        .createFile(request)).withSelfRel();
    org.springframework.hateoas.Link contentLink = linkTo(methodOn(FileController.class)
        .downloadFile(fileId)).withRel("download");
    String contentHref = contentLink.getHref();
    String selfHref = selfLink.getHref();
    Link self = new Link().rel("self").href(selfHref);
    Link download = new Link().rel("download").href(contentHref);
    return List.of(self, download);
  }

}
