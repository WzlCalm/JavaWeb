package SessionDemo;

import DruidMysql.DruidMysql;
import Users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request编码格式
        request.setCharacterEncoding("utf-8");
        //获取用户登陆信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        HttpSession loginSession = request.getSession();
//        loginSession.setAttribute("username",username);
//        loginSession.setAttribute("password",password);
        //判断用户登陆信息是否正确
        User user = new User(username, password);
        DruidMysql druidMysql = new DruidMysql();
        User user1 = druidMysql.queryMysql(user);
        if (user1 != null) {
            //获取Session对象
            HttpSession session = request.getSession();
            //设置信息
            session.setAttribute("username", user1.getUsername());
            session.setAttribute("password", user1.getPassword());
            //转发请求到IndexServlet
            request.getRequestDispatcher("IndexServlet").forward(request, response);
        }else {
            request.getRequestDispatcher("login.html").forward(request,response);
        }
//        if ("张三".equals(username) && "123".equals(password)) {
//            //获取Session对象
//            HttpSession session = request.getSession();
//            //设置信息
//            session.setAttribute("username", username);
//            //转发请求到IndexServlet
//            request.getRequestDispatcher("IndexServlet").forward(request, response);
//        }else{
//            request.getRequestDispatcher("login.html").forward(request,response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
