package pl.kaminski.okeapp;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Random;

@SpringBootApplication
public class OkeAppApplication extends SpringApplication {

    public static void main(String[] args) {
         SpringApplication.run(OkeAppApplication.class, args);


    }

}
