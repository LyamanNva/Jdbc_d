import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployerRepository employerRepository=new EmployerRepository();
//        while (true) {
//            Employer employerById = employerRepository.getEmployerById(new Scanner(System.in).nextInt());
//            System.out.println(employerById);
//            employerById.setContactName("Laman");
//            employerById.setPhoneNumber("+994702001006");
//            employerRepository.updateEmployer(employerById);
//            System.out.println(employerRepository.getEmployerById(employerById.getId()));
//        }
        try {
            employerRepository.getEmployerByIdWithProcedure(1);
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }
}