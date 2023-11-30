package com.example.sdrconverterissue;

import java.sql.SQLException;
import java.util.List;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.lang.NonNull;

@Configuration(proxyBeanMethods = false)
class JdbcConfig extends AbstractJdbcConfiguration {

  @NonNull
  @Override
  protected List<?> userConverters() {
    return List.of(
        new StringJsonbReadingConverter(),
        new StringJsonbWritingConverter());
  }

}

@ReadingConverter
class StringJsonbReadingConverter implements Converter<PGobject, String> {

  @Override
  public String convert(@NonNull PGobject source) {
    if (source.getValue() == null) {
      return null;
    }
    return source.getValue();
  }
}

@WritingConverter
class StringJsonbWritingConverter implements Converter<String, PGobject> {

  @Override
  public PGobject convert(@NonNull String source) {
    var pGobject = new PGobject();
    pGobject.setType("jsonb");

    try {
      pGobject.setValue(source);
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }

    return pGobject;
  }
}
