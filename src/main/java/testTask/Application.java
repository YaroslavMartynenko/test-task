package testTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import testTask.service.ActionService;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        ActionService service = context.getBean(ActionService.class);
        Scanner scanner = new Scanner(System.in);
        int actionNumber;
        String value;

        System.out.println("Available actions:\n" +
                "1 - Show head of department name.\n" +
                "2 - Show department statistics.\n" +
                "3 - Show the average salary for the department.\n" +
                "4 - Show count of employee for department.\n" +
                "5 - Global search by template.\n" +
                "0 - Exit from app.");

        while (true) {
            System.out.println("Input action: ");
            actionNumber = scanner.nextInt();
            scanner.nextLine();

            if (actionNumber == 0) {
                System.exit(0);
            }

            System.out.println("Input value: ");
            value = scanner.nextLine();

            System.out.println(service.invokeAction(actionNumber, value));
        }
    }
}
