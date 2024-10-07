package newSychat.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.awt.*;

@SpringBootApplication
@ComponentScan(basePackages = {"newSychat.backend"})
public class Main {
    public static void main(String[] args) {
        ConfigFile.setup();
        new SpringApplication(Main.class).run();
    }
}


// TODO´s
// ✔ - %ban
// [✔] - colors
// ✔ - Wenn jmd gelöscht wird soll dieser nicht mehr senden können
// ✔ - %nick keine gleichen display Names
// ✔ - Permissions
// ✔ - Beim registrieren soll die klein und groß Schreibung entfallen
