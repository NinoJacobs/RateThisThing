package com.ratethisthing.ratethisthing.controller;

import com.ratethisthing.ratethisthing.openapi.ratethisthing.api.ItemApi;
import com.ratethisthing.ratethisthing.openapi.ratethisthing.dto.ItemDTO;
import com.ratethisthing.ratethisthing.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController implements ItemApi {
    private final ItemService itemService;

    @Override
    public ResponseEntity<List<ItemDTO>> getItems() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @Override
    public ResponseEntity<ItemDTO> getItemById(Long id) {
        ItemDTO item = itemService.findByItemId(id);

        return (item == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(item);
    }
}
