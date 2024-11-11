import java.sql.*;
import java.time.LocalDateTime;



public class EmployerRepository {
    Employer getEmployerById(Integer id) {
        Connection connection = DbConfig.getConnection();
        try (var prepareStatement = connection.prepareStatement("select * from company.employers where id=?");


        ) {

            prepareStatement.setInt(1,id);
            ResultSet resultSet=prepareStatement.executeQuery();
            resultSet.next();
            Integer employerId = resultSet.getObject("id",Integer.class);
            String companyName = resultSet.getString("company_name");
            String contactName = resultSet.getString("contact_name");
            String contactEmail = resultSet.getString("contact_email");
            String phoneNumber = resultSet.getString("phone_number");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            String postalCode = resultSet.getString("postal_code");
            String country = resultSet.getString("country");
            LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
            LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

            return Employer.builder()
                    .id(employerId)
                    .companyName(companyName)
                    .contactName(contactName)
                    .contactEmail(contactEmail)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .city(city)
                    .state(state)
                    .postalCode(postalCode)
                    .country(country)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    void updateEmployer(Employer employer) throws SQLException{

        String updateQuery="update company.employers set contact_name=? ,phone_number=? where id=?";
        Connection connection=DbConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1,employer.getContactName());
        preparedStatement.setString(2,employer.getPhoneNumber());
        preparedStatement.setInt(3,employer.getId());

        preparedStatement.execute();
        DbConfig.closeConnection(connection);
        preparedStatement.close();
    }

    public void getEmployerByIdWithProcedure(Integer id) throws SQLException {
        String procedure = "{ call company.get_employer_by_id(?) }";

        try (Connection connection = DbConfig.getConnection();
             CallableStatement callableStatement = connection.prepareCall(procedure)) {

            callableStatement.setInt(1, id); // Parametre olarak id'yi ayarla

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    int employerId = resultSet.getInt("id");
                    String companyName = resultSet.getString("company_name");
                    String contactName = resultSet.getString("contact_name");
                    String contactEmail = resultSet.getString("contact_email");
                    String phoneNumber = resultSet.getString("phone_number");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String postalCode = resultSet.getString("postal_code");
                    String country = resultSet.getString("country");
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                    System.out.printf("ID: %d, Şirket Adı: %s, İletişim: %s, E-posta: %s, Telefon: %s, Adres: %s, Şehir: %s, Eyalet: %s, Posta Kodu: %s, Ülke: %s, Oluşturulma: %s, Güncellenme: %s%n",
                            employerId, companyName, contactName, contactEmail, phoneNumber, address, city, state, postalCode, country, createdAt, updatedAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}





