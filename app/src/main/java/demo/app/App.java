package demo.app;

import demo.app.login.LoginService;

public class App {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> new LoginService().login("zhangsan")).start();
        new Thread(() -> new LoginService().login("李四")).start();

        Thread.sleep(5000);
    }

}
