package com.retail.demo;

import com.retail.demo.entity.InventoryItemEntity;
import com.retail.demo.entity.OrderedItemEntity;
import com.retail.demo.model.InventoryItem;
import com.retail.demo.model.UserOrder;
import com.retail.demo.service.InventoryItemService;
import com.retail.demo.service.OrderService;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserOrderTest {

    @Autowired
    private OrderService orderService;
    private InventoryItemService inventoryItemService;
    private InventoryItem testItem = new InventoryItem(null, "testItem", "testItem", -1L, -1L,
            -1L, null, -1L);
    private UserOrder testUserOrder = new UserOrder(null, -1L, -1L, -1L, -1L, -1L, -1L, "testUserOrder");

    private Long testId = -1L;

    private UserOrder updateTestUserOrder = new UserOrder(null, -1L, -1L, -1L, -1L, -1L, -1L, "test");

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Start..." + testInfo.getDisplayName());
        if(!orderService.isDaoEmpty()) {
            testId = orderService.getLastOrder().getId();
        }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Finished..." + testInfo.getDisplayName());
    }

    @Test
    @Order(1)
    void createOrder() throws Exception{
        assertNotNull(orderService.createOrder(testUserOrder));
    }

    @Test
    @Order(2)
    void getOrderById() throws Exception {
        assertNotNull(orderService.getOrder(testId));
    }

    @Test
    @Order(3)
    void getAllOrders() throws Exception {
        assertTrue(orderService.getAllOrders().size() > 0);
    }

    @Test
    @Order(4)
    void updateOrder() throws Exception {
        assertNotNull(orderService.updateOrder(testId, updateTestUserOrder));
    }

    @Test
    @Order(5)
    void AddItemToOrder() throws Exception {
        InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();
        BeanUtils.copyProperties(testItem, inventoryItemEntity);
        assertNotNull(orderService.addItemToOrder(testId, inventoryItemEntity));
    }

    @Test
    @Order(6)
    void getAllItemsInOrder() throws Exception {
        assertTrue(orderService.getAllItemsInOrder(testId).size() > 0);
    }

    @Test
    @Order(7)
    void removeItemFromOrder() throws Exception {
        OrderedItemEntity orderedItemEntity = new OrderedItemEntity();
        BeanUtils.copyProperties(testItem, orderedItemEntity);
        orderedItemEntity.setOrderId(testId);
        assertNotNull(orderService.removeItemFromOrder(testId, orderedItemEntity));
    }

    @Test
    @Order(8)
    void deleteOrder() throws Exception {
        assertTrue(orderService.deleteOrder(testId));
    }
}
