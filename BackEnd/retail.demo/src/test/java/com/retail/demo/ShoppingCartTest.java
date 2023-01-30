package com.retail.demo;

import com.retail.demo.model.OrderedItem;
import com.retail.demo.model.ShoppingCartItem;
import com.retail.demo.service.ShoppingCartService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ShoppingCartTest {

    @Autowired
    private ShoppingCartService shoppingCartService;

    private Long testId = -1L;

    private ShoppingCartItem testItem = new ShoppingCartItem(null, "testItem", "testItem", -1L, -1L,
            -1L, null, -1L);

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Start..." + testInfo.getDisplayName());
        if(!shoppingCartService.isDaoEmpty()) {
            testId = shoppingCartService.getLastItem().getId();
        }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Finished..." + testInfo.getDisplayName());
    }

    @Test
    @Order(1)
    void createItem() throws Exception{
        assertNotNull(shoppingCartService.addToCart(testItem));
    }

    @Test
    @Order(2)
    void getItems() throws Exception {
        assertNotNull(shoppingCartService.getShoppingCart());
    }

    @Test
    @Order(3)
    void clearCart() throws Exception {
        shoppingCartService.clearShoppingCart();
        assertNull(shoppingCartService.getShoppingCart());
    }

    @Test
    @Order(4)
    void isEmpty() throws Exception {
        assertTrue(shoppingCartService.isDaoEmpty());
    }
}
