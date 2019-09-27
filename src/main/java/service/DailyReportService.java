package service;
import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;
import util.DayReportBuffer;
import java.util.List;

public class DailyReportService {
    private static DailyReportService dailyReportService;
    private SessionFactory sessionFactory;
    private DailyReportService(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() { return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport(); }

    public DailyReport getLastReport() { return new DailyReportDao(sessionFactory.openSession()).getLastReport(); }

    public void addingSoldCar(Car car) {
        new DailyReportDao(sessionFactory.openSession()).addingSoldCar(car);
    }

    public void deleteAllReports() {
        new DailyReportDao(sessionFactory.openSession()).deleteAllReport();
    }

    public void createNewDayReport() {
        new DailyReportDao(sessionFactory.openSession()).createNewDayReport();
        DayReportBuffer.getInstance().reset();
    }
}
