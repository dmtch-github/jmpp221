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

//   @Override
//   @SuppressWarnings("unchecked")
//   public List<User> listUsers() {
//      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
//      return query.getResultList();
//   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getByCar(String model, int series) {
      String hql = "FROM User where Car.model = :modelName AND Car.series = :seriesNum";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql); //Почему-то этот запрос не проходит
      query.setParameter("modelName", model);
      query.setParameter("seriesNum", series);
      return query.getResultList();
   }

}
