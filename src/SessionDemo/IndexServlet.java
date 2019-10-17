package SessionDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session
        HttpSession session = request.getSession();
        //获取session中的username值
        String username = (String) session.getAttribute("username");
        //判断username中有无登陆信息
        if (username != null && !"".equals(username)) {
            //有username信息
            //设置编码格式
            response.setContentType("text/html;charset=utf-8");
            //显示信息
            response.getWriter().write("<h2>已登陆，您好" + username + "<a href='login.html'>切换账号</a>,<a href='/day17_Cookie_Session/DeleteServlet'>注销账号</a>"+"</h2>");
        }else{
            //没有username信息,重定向到登陆界面login.html
            //获取路径
            String contextPath = request.getContextPath();
            //重定向到login.html
            response.sendRedirect(contextPath+"/login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
