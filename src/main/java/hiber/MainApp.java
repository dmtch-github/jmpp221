package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Ferrary",400)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Bentley",300)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Maserati",500)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Rolls-Royce",100)));


      List<User> usersAll = userService.listUsers();
      for (User user : usersAll) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      List<User> users = userService.getByCar("Ferrary", 400);
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCarModel("Rolls-Royce") + "\n");

      System.out.println(userService.getUserByCarSeries(500) + "\n");

      context.close();
   }
}
