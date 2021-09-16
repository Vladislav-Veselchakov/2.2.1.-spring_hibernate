package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(Car car) {
      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery("from Car where model = :model and series = :series");
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      List<Car> lstCar = query.getResultList();
      if(lstCar.size() <= 0) {
         return null;
      }
      Hibernate.initialize(lstCar.get(0).getUser());
      User usr = lstCar.get(0).getUser();
      return usr;
   }
}
