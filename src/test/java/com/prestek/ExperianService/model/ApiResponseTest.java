package com.prestek.ExperianService.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testApiResponse_SuccessWithData() {
        CreditHistory creditHistory = new CreditHistory(7.5, 3, 1, 2, 0, 1, true, 2, 1, 0);
        ApiResponse<CreditHistory> response = new ApiResponse<>(true, "Success", creditHistory);

        assertTrue(response.success());
        assertEquals("Success", response.message());
        assertEquals(creditHistory, response.data());
    }

    @Test
    void testApiResponse_Failure() {
        ApiResponse<String> response = new ApiResponse<>(false, "Error occurred", null);

        assertFalse(response.success());
        assertEquals("Error occurred", response.message());
        assertNull(response.data());
    }

    @Test
    void testApiResponse_Equality() {
        ApiResponse<String> response1 = new ApiResponse<>(true, "OK", "data");
        ApiResponse<String> response2 = new ApiResponse<>(true, "OK", "data");

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    void testApiResponse_ToString() {
        ApiResponse<String> response = new ApiResponse<>(true, "Success", "test data");

        String toString = response.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("true"));
        assertTrue(toString.contains("Success"));
    }

    @Test
    void testApiResponse_WithInteger() {
        ApiResponse<Integer> response = new ApiResponse<>(true, "Count", 42);

        assertTrue(response.success());
        assertEquals("Count", response.message());
        assertEquals(42, response.data());
    }
}
