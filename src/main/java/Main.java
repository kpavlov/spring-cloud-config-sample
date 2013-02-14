import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.MyService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        System.out.println("==============================");
        loadProductProperties();
        loadEnvironmentProperties();
        System.out.println("==============================");

        AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("/spring-config.xml");

        appContext.registerShutdownHook();

        final MyService service = appContext.getBean(MyService.class);
        service.printConfig();
    }

    private static void loadEnvironmentProperties() {
        final Properties props = new Properties();
        try {
            final Path envPath = Paths.get("env.properties");
            if (!envPath.toFile().canRead()) {
                throw new FileNotFoundException(envPath.toAbsolutePath().toString());
            }
//            props.load(currentThread().getContextClassLoader().getResourceAsStream("/env.properties"));
            props.load(new FileReader(envPath.toFile()));
            System.out.println("Loaded Environment Properties:");
            for (String prop : props.stringPropertyNames()) {
                System.out.println("\t" + prop + "=" + props.getProperty(prop));
                System.setProperty(prop, props.getProperty(prop));
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static void loadProductProperties() {
        final Properties props = new Properties();
        try {
            props.load(Main.class.getResourceAsStream("/product.properties"));

            System.out.println("Product Properties:");
            for (String prop : props.stringPropertyNames()) {
                System.out.println("\t" + prop + "=" + props.getProperty(prop));
                System.setProperty(prop, props.getProperty(prop));
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}