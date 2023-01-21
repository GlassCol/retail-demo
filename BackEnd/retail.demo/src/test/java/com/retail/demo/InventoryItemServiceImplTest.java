package com.retail.demo;

import com.retail.demo.model.InventoryItem;
import com.retail.demo.service.InventoryItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class InventoryItemServiceImplTest {

    @Autowired
    private InventoryItemService inventoryItemService;

    private InventoryItem testItem = new InventoryItem(null, "testItem", -1L, -1L,
            -1L, -1L, -1L);

    private Long testId = -1L;

    private InventoryItem updateTestItem = new InventoryItem(null, "test", -1L, -1L,
            -1L, -1L, -1L);

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Start..." + testInfo.getDisplayName());
        if(!inventoryItemService.isDaoEmpty()) {
        testId = inventoryItemService.getLatestAddition().getId();
    }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Finished..." + testInfo.getDisplayName());
    }

    @Test
    @Order(1)
    void createItem() throws Exception{
        assertNotNull(inventoryItemService.createInventoryItem(testItem));
    }

    @Test
    @Order(2)
    void getItemById() throws Exception {
        assertNotNull(inventoryItemService.getInventoryItem(testId));
    }

    @Test
    @Order(3)
    void getItemByName() throws Exception {
        assertTrue(inventoryItemService.getInventoryItemByName("testItem").size() > 0);
    }

    @Test
    @Order(4)
    void getBadItemName() throws Exception {
        assertTrue(inventoryItemService.getInventoryItemByName("badItemName").size() == 0);
    }
    @Test
    @Order(5)
    void getAllItems() throws Exception {
        assertTrue(inventoryItemService.getAllInventoryItems().size() > 0);
    }

    @Test
    @Order(6)
    void updateItem() throws Exception {
        inventoryItemService.updateInventoryItem(testId, updateTestItem);
        InventoryItem tempItem = inventoryItemService.getInventoryItem(testId);
        assertTrue(tempItem.getName().equals(updateTestItem.getName()));
    }



    @Test
    @Order(7)
    void deleteItem() throws Exception {
        assertTrue(inventoryItemService.deleteInventoryItem(testId));
    }
}
