package com.example.sdrconverterissue;

import com.example.sdrconverterissue.TestRepository.TestEntity;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class TestApplicationRunner implements ApplicationRunner {
  
  private final TestRepository repository;

  TestApplicationRunner(TestRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    var saved = repository.save(TestEntity.of("text", "{\"key\":\"value\"}"));

    System.out.println("saved = " + saved);

    // This works up until 3.1.6
    repository.findByTextColumn("text")
        .forEach(entity -> System.out.println("found = " + entity));
  }
}
