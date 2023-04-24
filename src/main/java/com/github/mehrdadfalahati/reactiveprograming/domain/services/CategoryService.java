package com.github.mehrdadfalahati.reactiveprograming.domain.services;

import com.github.mehrdadfalahati.reactiveprograming.domain.Category;
import com.github.mehrdadfalahati.reactiveprograming.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Flux<Category> listCategory() {
        return repository.findAll();
    }

    public Mono<Category> loadById(String id) {
        return repository.findById(id);
    }

    public Mono<Category> save(Publisher<Category> categoryPublisher) {
        return repository.saveAll(categoryPublisher).map(cat -> new Category(cat.getId(), cat.getDescription())).next();
    }
}
