package DruidMysql;

import Users.User;

public class Test {
    public static void main(String[] args) {
        User user = new User("马云", "my");
        User user1 = DruidMysql.queryMysql(user);
        System.out.println(user1.toString());
    }
}
