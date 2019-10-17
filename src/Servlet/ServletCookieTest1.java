package Servlet;

import com.sun.jdi.Value;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ServletCookieTest1")
public class ServletCookieTest1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookie内容
        Cookie[] cookies = request.getCookies();
        //flag判断是否为第一次访问，默认为flase
        boolean flag = false;
        response.setContentType("text/html;charset=utf-8");
        //判断是否有lastTime数据
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //判断cookie中是否有lastTime这个name
                if (cookie.getName().equals("lastTime")) {
                    //有lastTime，将flag置换
                    flag = true;
                    //获取lastTime对应的value值
                    String value = cookie.getValue();
                    //获取到value后需要用URl解码
                    value = URLDecoder.decode(value, "utf-8");
                    //获取PrintWriter字符打印流并在页面输出
                    response.getWriter().write("<h2>欢迎回来,您上次的访问时间是" + value + "</h2>");
                    //获取此次访问时间,用于更新lastTime值
                    Date date = new Date();
                    //创建一个时间格式对象，生成时间格式模版
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    //将当前时间改为指定的时间格式，以String类型返回
                    String nowTime = sdf.format(date);
                    //使用URL编码解决空格问题
                    nowTime = URLEncoder.encode(nowTime, "utf-8");
                    //将更新lastTime中的值
                    cookie.setValue(nowTime);
                    //设置cookie有效的保存时间
                    cookie.setMaxAge(10);
                    //将更新后的cookie放进cookie[]中
                    response.addCookie(cookie);
                    //跳出循环
                    break;
                }
            }
        }
        if (cookies == null || cookies.length == 0 || flag == false) {
            //获取此次访问时间,用于更新lastTime值
            Date date = new Date();
            //创建一个时间格式对象，生成时间格式模版
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            //将当前时间改为指定的时间格式，以String类型返回
            String nowTime = sdf.format(date);
            //使用URL编码解决空格问题
            nowTime = URLEncoder.encode(nowTime, "utf-8");
            Cookie lastTime = new Cookie("lastTime", nowTime);
            //设置cookie保存时间
            lastTime.setMaxAge(10);
            //将更新后的lastTime放进cookie[]中
            response.addCookie(lastTime);
            //页面输出
            response.getWriter().write("<h2>欢迎第一次访问</h2>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
