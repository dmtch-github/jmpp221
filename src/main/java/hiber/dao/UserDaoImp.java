package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getByCar(String model, Integer series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.car WHERE u.car.model = :model AND u.car.series = :series")
              .setParameter("model", model)
              .setParameter("series", series);
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCarModel(String model) {
      return (User) sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.car WHERE u.car.model = :model")
              .setParameter("model", model)
              .uniqueResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCarSeries(Integer series) {
      return (User) sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.car WHERE u.car.series = :series")
              .setParameter("series", series)
              .uniqueResult();
   }

}
