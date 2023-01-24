package com.retail.demo.controller;

import com.retail.demo.dao.InventoryItemDao;
import com.retail.demo.model.InventoryItem;
import com.retail.demo.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/inventoryItemApi/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryItemController {
    private InventoryItemDao inventoryItemDao;
    @Autowired
    private InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService){
        this.inventoryItemService = inventoryItemService;
    }

    @PostMapping("/inventoryItems")
    public InventoryItem createInventoryItem(@RequestBody InventoryItem inventoryItem){
        return inventoryItemService.createInventoryItem(inventoryItem);
    }

    @GetMapping("/inventoryItems/{inventoryItemId}")
    public InventoryItem getInventoryItem(@PathVariable Long inventoryItemId){
        return inventoryItemService.getInventoryItem(inventoryItemId);
    }

    @GetMapping("/inventoryItems/byName/{inventoryItemName}")
    public List<InventoryItem> getInventoryItem(@PathVariable String inventoryItemName){
        return inventoryItemService.getInventoryItemByName(inventoryItemName);
    }

    @GetMapping("/inventoryItems")
    public List<InventoryItem> getLastInventoryItem(){
        return inventoryItemService.getAllInventoryItems();
    }

    @PutMapping("/inventoryItems/{inventoryItemId}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long inventoryItemId,
                                                             @RequestBody InventoryItem inventoryItem){
        inventoryItem = inventoryItemService.updateInventoryItem(inventoryItemId, inventoryItem);
        return ResponseEntity.ok(inventoryItem);
    }

    @DeleteMapping("/inventoryItems/{inventoryItemId}")
    public ResponseEntity<Map<String, Boolean>> deleteInventoryItem(@PathVariable Long inventoryItemId){
        boolean deleted = false;
        deleted = inventoryItemService.deleteInventoryItem(inventoryItemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }




}
