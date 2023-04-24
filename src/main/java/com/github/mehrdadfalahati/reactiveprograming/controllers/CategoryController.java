package com.github.mehrdadfalahati.reactiveprograming.controllers;

import com.github.mehrdadfalahati.reactiveprograming.domain.Category;
import com.github.mehrdadfalahati.reactiveprograming.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/categories")
    public ResponseEntity<Flux<Category>> list() {
        log.info("Request for list category");
        final Flux<Category> categoryFlux = service.listCategory();
        return ResponseEntity.ok(categoryFlux);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Mono<Category>> getById(@PathVariable String id) {
        final Mono<Category> categoryFlux = service.loadById(id);
        return ResponseEntity.ok(categoryFlux);
    }

    @PostMapping("/categories")
    public ResponseEntity<Mono<Category>> create(@RequestBody Publisher<Category> categoryPublisher) {
        log.info("save category {}", categoryPublisher);
        final Mono<Category> categoryFlux = service.save(categoryPublisher);
        return new ResponseEntity<>(categoryFlux, HttpStatus.CREATED);
    }
}
