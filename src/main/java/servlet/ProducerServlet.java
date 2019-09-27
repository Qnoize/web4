package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/producer")
public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(403);
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        String priceIn = req.getParameter("price");
        if (!brand.equals("")&& !model.equals("") && !licensePlate.equals("") && !priceIn.equals("")){
            Long price = Long.parseLong(priceIn);
            if(CarService.getInstance().getNumberBrand(brand) < 10) {
                Car car = new Car(brand, model, licensePlate, price);
                CarService.getInstance().addCar(car);
                resp.setStatus(200);
            }
        }

    }
}
