package com.github.mehrdadfalahati.reactiveprograming.controllers;

import com.github.mehrdadfalahati.reactiveprograming.domain.Category;
import com.github.mehrdadfalahati.reactiveprograming.domain.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class CategoryControllerTest {

    private WebTestClient webTestClient;

    private CategoryService categoryService;

    private CategoryController controller;

    @BeforeEach
    void setUp() {
        categoryService = Mockito.mock(CategoryService.class);
        controller = new CategoryController(categoryService);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void list() {
        BDDMockito.given(categoryService.listCategory())
                .willReturn(Flux.just(Category.builder().description("Cat1").build(),
                        Category.builder().description("Cat2").build()));

        webTestClient.get().uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getById() {
        BDDMockito.given(categoryService.loadById(anyString()))
                .willReturn(Mono.just(Category.builder().description("Cat1").build()));

        webTestClient.get().uri("/api/v1/categories/id")
                .exchange()
                .expectBody(Category.class);
    }
}