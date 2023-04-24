package com.github.mehrdadfalahati.reactiveprograming.repositories;

import com.github.mehrdadfalahati.reactiveprograming.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
