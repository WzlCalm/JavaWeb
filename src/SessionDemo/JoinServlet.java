package SessionDemo;

import DruidMysql.DruidMysql;
import Users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//设置request编码格式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户登陆信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        DruidMysql druidMysql = new DruidMysql();
        if (!druidMysql.query(user)){
            boolean b = druidMysql.createMysql(user);
            if (b){
                response.getWriter().write("注册成功,<a href='login.html'>返回登陆</a>");
            }
        }else{
            response.getWriter().write("注册失败");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
