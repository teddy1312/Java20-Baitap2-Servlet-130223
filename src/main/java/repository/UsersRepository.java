package repository;

import config.MySqlConfig;
import model.UserColumn;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    public boolean countAccountByEmailAndPassword(String account, String password){
        int count = 0;
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT count(*) as countAccount FROM bt_login.users user WHERE user.email = ? and user.password = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt("countAccount");
            }
        } catch(Exception e){
            System.out.println("An error occurred when checking account "+e.getMessage());
            e.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("An error occurred when closing database "+e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return count>0;
    }

    public UserModel getUserInfoByEmail(String email){
        UserModel userModel = new UserModel();
        Connection connection = MySqlConfig.getConnection();
        String query = "SELECT * FROM bt_login.users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(email.equals(resultSet.getString(UserColumn.EMAIL.getValue()))){
                    userModel.setId(resultSet.getInt(UserColumn.ID.getValue()));
                    userModel.setEmail(resultSet.getString(UserColumn.EMAIL.getValue()));
                    userModel.setPassword(resultSet.getString(UserColumn.PASSWORD.getValue()));
                    userModel.setFullname(resultSet.getString(UserColumn.FULLNAME.getValue()));
                    userModel.setPhone(resultSet.getString(UserColumn.PHONE.getValue()));
                    userModel.setAddress(resultSet.getString(UserColumn.ADDRESS.getValue()));

                    break;
                }

            }

        } catch (Exception e){
            System.out.println("An error occurred when get user info "+e.getMessage());
            e.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("An error occurred when closing database "+e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return userModel;
    }
}
