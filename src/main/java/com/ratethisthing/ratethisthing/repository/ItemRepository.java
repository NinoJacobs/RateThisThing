package com.ratethisthing.ratethisthing.repository;

import com.ratethisthing.ratethisthing.entity.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @EntityGraph(value = "Item.category", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Item> findById(Long id);
}