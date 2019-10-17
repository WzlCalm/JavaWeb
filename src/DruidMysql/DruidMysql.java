package DruidMysql;

import Users.User;
import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidMysql {
    public static boolean query(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from tb_user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            String username = user.getUsername();
            String password = user.getPassword();
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }
        return false;
    }
    public static User queryMysql(User user) {
        User returnUser = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from tb_user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            String username = user.getUsername();
            String password = user.getPassword();
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                returnUser.setUsername(username1);
                returnUser.setPassword(password1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }
        return returnUser;
    }
    public static boolean createMysql(User user){

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into tb_user(username,password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            int i = ps.executeUpdate();
            if (i>0){
                System.out.println("注册成功");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps, conn);
        }
        return false;
    }
    public static boolean deleteMysql(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from tb_user where username = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            int i = ps.executeUpdate();
            if(i>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(ps,conn);
        }
        return false;
    }
}
