package tadashiy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    try (ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args)) {
      Main m = ctx.getBean(Main.class);
      m.method();
    }
  }
  @Autowired
  private JdbcTemplate jdbc;
  public void method() {
    this.jdbc.execute("CREATE TABLE TEST_TABLE (ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(256))");
    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "hoge");
    this.jdbc.update("INSERT INTO TEST_TABLE (VALUE) VALUES (?)", "fuga");
    List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM TEST_TABLE");
    list.forEach(System.out::println);
  }
}