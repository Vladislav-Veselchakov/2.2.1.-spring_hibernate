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
      System.out.println("---------- VL main ----------------------");
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

/** addin users/cars
      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.add(new Car("mod1", 1001, userService.listUsers().get(0)));
      userService.add(new Car("mod2", 1002, userService.listUsers().get(1)));
      userService.add(new Car("mod3", 1003, userService.listUsers().get(2)));
      userService.add(new Car("mod4", 1004, userService.listUsers().get(3)));
*/

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User usr = userService.getUserByCar(new Car("mod2", 1002, null));
      if (usr == null) {
         System.out.println("no user found for car");
      } else {
         System.out.printf(String.format("user by car:  %d -- %s -- %s -- %s\n", usr.getId(),usr.getFirstName(), usr.getLastName(), usr.getEmail()));
      }

      context.close();
   }
}
