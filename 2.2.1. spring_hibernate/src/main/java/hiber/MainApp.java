package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      Car car1 = new Car("Mazda", 5);
      Car car2 = new Car("BMW", 5);
      Car car3 = new Car("BMW", 3);

      userService.addCar(car1);
      userService.addCar(car2);
      userService.addCar(car3);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.add(new User("User5", "Lastname5", "user5@mail.ru", car1));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", car3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         }
         System.out.println();
      }


      User user7 = userService.findUser(car1);

      System.out.println("Id = "+user7.getId());
      System.out.println("First Name = "+user7.getFirstName());
      System.out.println("Last Name = "+user7.getLastName());
      System.out.println("Email = "+user7.getEmail());


      context.close();


      Scanner scanner = new Scanner(System.in);
      Deque<Integer> deque = new ArrayDeque<>();

      while (scanner.hasNext()) {
         deque.addFirst(scanner.nextInt()); //может возникнуть ошибка с нахождением Int
         scanner.nextInt();
      }

      for (Integer element : deque) {
         System.out.print(' ' + element.toString());
      }
   }
}
