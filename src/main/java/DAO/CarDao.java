package DAO;
import model.Car;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public Car getCarByParameters(String brand, String model, String licensePlate) {
        return (Car) session.createQuery("select c from Car as c where c.brand = ?1 and c.model = ?2 and c.licensePlate = ?3")
                .setParameter(1, brand)
                .setParameter(2, model)
                .setParameter(3,licensePlate)
                .list().get(0);
    }

    public List<Car> getCarByBrand(String brand) {
        return session.createQuery("from Car as c where c.brand = ?1")
                .setParameter(1, brand)
                .list();
    }

    public void addCar(Car car) {
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    public List<Car> getAllCars() {
        session.beginTransaction();
        List<Car> list = new ArrayList<>(session.createQuery("from Car").list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void deleteCar(Car car) {
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAllCars() {
        session.beginTransaction();
        session.createQuery("DELETE from Car").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
