/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DatabaseConnection;
import model.UserModel;
/**
 *
 * @author admin
 */
public class UserController {
    //  SQL
    private static final String INSERT_USER = "INSERT INTO users (username, password) VALUES (?, ?)";

    private static final String CHECK_USER = "SELECT * FROM users WHERE username = ?";

    private final String LOGIN_USER = "SELECT username, password FROM users WHERE username=? AND password=?";

    private final String GET_INFO_USER = "SELECT username, password FROM users WHERE username=?";

    //    private final String UPDATE_USER = "UPDATE users SET score = ?, win = ?, draw = ?, lose = ?, avgCompetitor = ?, avgTime = ? WHERE username=?";
    //  Instance
    private final Connection con;

    public UserController() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    //    public String register(String username, String password) {
//    	//  Check user exit
//        try {
//            PreparedStatement p = con.prepareStatement(CHECK_USER);
//            p.setString(1, username);
//            ResultSet r = p.executeQuery();
//            if (r.first()) {
//                return "failed;" + "User Already Exit";
//            } else {
//                r.close();
//                p.close();
//                p = con.prepareStatement(INSERT_USER);
//                p.setString(1, username);
//                p.setString(2, password);
//                p.executeUpdate();
//                p.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "success;";
//    }
    public String register(String username, String password) {
        try {
            // Kiểm tra người dùng đã tồn tại
            PreparedStatement checkUserStmt = con.prepareStatement(CHECK_USER);
            checkUserStmt.setString(1, username);
            ResultSet resultSet = checkUserStmt.executeQuery();

            if (resultSet.next()) {
                return "failed;User Already Exists";
            } else {
                // Nếu không tồn tại, thêm người dùng mới
                PreparedStatement insertUserStmt = con.prepareStatement(INSERT_USER);
                insertUserStmt.setString(1, username);
                insertUserStmt.setString(2, password); // Nên băm mật khẩu trước khi lưu
                insertUserStmt.executeUpdate();
                return "success;";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "failed;Database Error";
        }
    }


    public String login(String username, String password) {
        // Kiểm tra người dùng
        try (PreparedStatement p = con.prepareStatement(LOGIN_USER)) {
            // Đăng nhập người dùng
            p.setString(1, username);
            p.setString(2, password);
            ResultSet r = p.executeQuery();

            if (r.next()) { // Sử dụng next() để kiểm tra xem có kết quả nào không
                return "success;" + username; // Trả về thông báo thành công
            } else {
                return "failed;Please enter the correct username and password!";
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return "failed;Database Error"; // Trả về thông báo lỗi
        }
    }

//    public String getInfoUser(String username) {
//        UserModel user = new UserModel();
//        try {
//            PreparedStatement p = con.prepareStatement(GET_INFO_USER);
//            p.setString(1, username);
//
//            ResultSet r = p.executeQuery();
//            while(r.next()) {
//                user.setUserName(r.getString("username"));
//                user.setScore(r.getFloat("score"));
//                user.setWin(r.getInt("win"));
//                user.setDraw(r.getInt("draw"));
//                user.setLose(r.getInt("lose"));
//                user.setAvgCompetitor(r.getFloat("avgCompetitor"));
//                user.setAvgTime(r.getFloat("avgTime"));
//            }
//            return "success;" + user.getUserName() + ";" + user.getScore() + ";" + user.getWin() + ";" + user.getDraw() + ";" + user.getLose() + ";" + user.getAvgCompetitor() + ";" + user.getAvgTime() ;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public boolean updateUser(UserModel user) throws SQLException {
//        boolean rowUpdated;
//        PreparedStatement p = con.prepareStatement(UPDATE_USER);
//        //  Login User
//        p.setFloat(1, user.getScore());
//        p.setInt(2, user.getWin());
//        p.setInt(3, user.getDraw());
//        p.setInt(4, user.getLose());
//        p.setFloat(5, user.getAvgCompetitor());
//        p.setFloat(6, user.getAvgTime());
//        p.setString(7, user.getUserName());
//
////            ResultSet r = p.executeQuery();
//        rowUpdated = p.executeUpdate() > 0;
//        return rowUpdated;
//    }

    public UserModel getUser(String username) {
        UserModel user = new UserModel();
        try {
            PreparedStatement p = con.prepareStatement(GET_INFO_USER);
            p.setString(1, username);

            ResultSet r = p.executeQuery();
            while(r.next()) {
                user.setUserName(r.getString("username"));
//                user.setScore(r.getFloat("score"));
//                user.setWin(r.getInt("win"));
//                user.setDraw(r.getInt("draw"));
//                user.setLose(r.getInt("lose"));
//                user.setAvgCompetitor(r.getFloat("avgCompetitor"));
//                user.setAvgTime(r.getFloat("avgTime"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
