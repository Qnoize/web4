package service;
import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;
import java.util.List;

public class CarService {
    private static CarService carService;
    private SessionFactory sessionFactory;
    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        List<Car> list;
        CarDao carDao = new CarDao( sessionFactory.openSession());
        return list = carDao.getAllCars();
    }

    public int getNumberBrand(String brand) {
        List<Car> list;
        int number = 0;
        CarDao carDao = new CarDao( sessionFactory.openSession());
        list = carDao.getCarByBrand(brand);
        if (list != null) {
            number = list.size();
        }
        return number;
    }

    public void addCar(Car car) {
        CarDao carDao = new CarDao(sessionFactory.openSession());
        carDao.addCar(car);
    }

    public Car getCarByParameters(String brand, String model, String number) {
        CarDao carDao = new CarDao(sessionFactory.openSession());
        return carDao.getCarByParameters(brand, model, number);
    }

    public void deleteCar(Car car) {
        new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public void deleteAllCar() {
        new CarDao(sessionFactory.openSession()).deleteAllCars();
    }
}
