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
   public User getUser(String carModel, int carSeries) {
      String HQL = "FROM User WHERE car.model = :modelPar AND car.series = :seriesPar";
      User user = sessionFactory.getCurrentSession()
              .createQuery(HQL, User.class)
              .setParameter("modelPar", carModel)
              .setParameter("seriesPar", carSeries)
              .uniqueResult();
      return user;
   }
}
