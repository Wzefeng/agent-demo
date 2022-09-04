package demo.app.login;

public class LoginService {

    public void login(String username) {

        System.out.println("用户登录:" + username);
        queryUserInfo(username);
    }

    private String queryUserInfo(String username) {
        System.out.println("用户查询: " + username);

        return username;
    }
}
