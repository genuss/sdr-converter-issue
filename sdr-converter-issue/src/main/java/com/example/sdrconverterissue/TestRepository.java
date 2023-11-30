package com.example.sdrconverterissue;

import com.example.sdrconverterissue.TestRepository.TestEntity;
import java.time.Instant;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;

public interface TestRepository extends ListCrudRepository<TestEntity, Long> {
  
  
  List<TestEntity> findByTextColumn(String textColumn);

  record TestEntity(@Id Long id,
                    @CreatedDate Instant createdAt, 
                    String textColumn,
                    String jsonColumn) {

    public static TestEntity of(String text, String json) {
      return new TestEntity(null, null, text, json);
    }
  }
}
