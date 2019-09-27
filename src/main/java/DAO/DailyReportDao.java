package DAO;
import model.Car;
import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DayReportBuffer;
import java.util.List;

public class DailyReportDao {
    private Session session;
    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> list = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return list;
    }

    public void addSoldCar(Car car) {
        DayReportBuffer dayReportBuffer = DayReportBuffer.getInstance();
        Long balanсeEarnings = dayReportBuffer.getEarnings() + car.getPrice();
        Long balanсeSoldCars = dayReportBuffer.getSoldCars() + 1;
        dayReportBuffer.setEarnings(balanсeEarnings);
        dayReportBuffer.setSoldCars(balanсeSoldCars);
    }

    public DailyReport getLastReport() {
        session.beginTransaction();
        Query query = session.createQuery("from DailyReport order by id desc");
        query.setMaxResults(1);
        DailyReport dailyReport = (DailyReport) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return dailyReport;
    }

    public void deleteAllReport() {
        session.beginTransaction();
        session.createQuery("DELETE from DailyReport").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void createNewDayReport() {
        DayReportBuffer dayReportBuffer = DayReportBuffer.getInstance();
        DailyReport dailyReport = new DailyReport(dayReportBuffer.getEarnings(), dayReportBuffer.getSoldCars());
        session.beginTransaction();
        session.save(dailyReport);
        session.getTransaction().commit();
        session.close();
    }
}
