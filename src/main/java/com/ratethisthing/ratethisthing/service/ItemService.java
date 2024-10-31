package com.ratethisthing.ratethisthing.service;

import com.ratethisthing.ratethisthing.mappers.ItemMapper;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.ItemDTO;
import com.ratethisthing.ratethisthing.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemMapper.INSTANCE::itemEntityToDto).toList();
    }

    public ItemDTO findByItemId(Long id) {
        return itemRepository.findById(id)
                .map(ItemMapper.INSTANCE::itemEntityToDto)
                .orElse(null);
    }
}
