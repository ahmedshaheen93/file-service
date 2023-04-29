package com.shaheen.fileservice.repository;

import com.shaheen.fileservice.model.Item;
import com.shaheen.fileservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
  Optional<Item> findByIdAndTypeIn(Integer id, List<Type> types);
}
