package com.vacinacerta;

import com.entities.db.User;
import com.entities.db.UsersVaccines;
import com.entities.db.Vaccine;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.entities.db", scanBasePackageClasses = Vaccine.class)
@ComponentScan(basePackages = "com.vacinacerta" )
@EntityScan(basePackages = "com.entities.db", basePackageClasses = {Vaccine.class, User.class, UsersVaccines.class})
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}