package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("Igor", "Golubev", "oldGar@mail.ru");
      User user2 = new User("Ivan", "Gusev", "businessGuss@mail.ru");
      Car car1 = new Car("Focus", 2);
      Car car2 = new Car("VAZ", 2107);
      user1.setCar(car1);
      user2.setCar(car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("----------------------");
         System.out.println(user.toString());
         System.out.println("----------------------");
      }

      System.out.println(userService.getUser("VAZ", 2107));

      context.close();
   }
}
