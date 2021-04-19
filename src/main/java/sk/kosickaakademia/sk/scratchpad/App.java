package sk.kosickaakademia.sk.scratchpad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(App.class);
        application.setApplicationContextClass(AnnotationConfigWebApplicationContext.class);
        SpringApplication.run(App.class, args);
    }
}
