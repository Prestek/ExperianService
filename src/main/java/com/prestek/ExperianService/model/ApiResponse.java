package com.prestek.ExperianService.model;

public record ApiResponse<T>(boolean success, String message, T data) {}
