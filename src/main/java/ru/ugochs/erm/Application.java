package ru.ugochs.erm;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@Theme("emergencies-response-management")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {
    public static void main(String[] arguments) {
        SpringApplication.run(Application.class, arguments);
    }
}
