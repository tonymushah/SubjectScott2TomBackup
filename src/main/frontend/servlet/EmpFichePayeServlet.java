package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.backend.base.context.DBconnect;
import main.backend.salaire.FicheSalaire;
import main.backend.salaire.SalaireCalculator;
import main.frontend.display.BodyBuilder;
import main.frontend.servlet.helper.ServletHelper;

public class EmpFichePayeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String date = req.getParameter("date");
        String empnoParam = req.getParameter("empno");

        try (Connection con = DBconnect.connect(); SalaireCalculator calculator = new SalaireCalculator(con)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            FicheSalaire ficheSalaire = calculator.getFicheSalaire(Integer.parseInt(empnoParam),
                    Date.valueOf(LocalDate.from(formatter.parse(date))));
            out.println(BodyBuilder.makeDefaultBody(ficheSalaire.htmlPrint()));
        } catch (Exception e) {
            String errorContent = ServletHelper.generateErrorContentSearch(date, null, e);
            out.println(BodyBuilder.makeDefaultBody(errorContent));
        }
    }
}
