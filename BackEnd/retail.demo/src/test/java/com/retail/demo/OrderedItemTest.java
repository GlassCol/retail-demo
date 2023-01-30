package com.retail.demo;

import com.retail.demo.model.OrderedItem;
import com.retail.demo.service.OrderedItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OrderedItemTest {
    @Autowired
    private OrderedItemService orderedItemService;

    private OrderedItem testItem = new OrderedItem(null, "testItem", "testItem", -1L, -1L,
            -1L, null, -1L);

    private Long testId = -1L;

    private OrderedItem updateTestItem = new OrderedItem(null, "test", "testDesc", -1L, -1L,
            -1L, null, -1L);

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Start..." + testInfo.getDisplayName());
        if(!orderedItemService.isDaoEmpty()) {
            testId = orderedItemService.getLatestAddition().getId();
        }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Finished..." + testInfo.getDisplayName());
    }

    @Test
    @Order(1)
    void createItem() throws Exception{
        assertNotNull(orderedItemService.createOrderedItem(testItem));
    }

    @Test
    @Order(2)
    void getItemById() throws Exception {
        assertNotNull(orderedItemService.getOrderedItem(testId));
    }


    @Test
    @Order(3)
    void getAllItems() throws Exception {
        assertTrue(orderedItemService.getAllOrderedItems().size() > 0);
    }

    @Test
    @Order(4)
    void updateItem() throws Exception {
        orderedItemService.updateOrderedItem(testId, updateTestItem);
        OrderedItem tempItem = orderedItemService.getOrderedItem(testId);
        assertTrue(tempItem.getName().equals(updateTestItem.getName()));
    }

    @Test
    @Order(5)
    void deleteItem() throws Exception {
        assertTrue(orderedItemService.deleteOrderedItem(testId));
    }
}

