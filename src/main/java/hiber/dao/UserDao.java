package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   List<User> getByCar(String model, Integer series);
   User getUserByCarModel(String model);
   User getUserByCarSeries(Integer series);
}
