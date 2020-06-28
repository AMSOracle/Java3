package InstantMessenger.srv;

import InstantMessenger.srv.AuthService;

import java.util.ArrayList;
import java.util.List;

public class AuthServiceDummyImpl implements AuthService {
    private class User {
        private String login;
        private String pwd;
        private String nick;

        public User(String login, String pwd, String nick) {
            this.login = login;
            this.pwd = pwd;
            this.nick = nick;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }
    }

    private List<User> userList;

    public AuthServiceDummyImpl() {
        userList = new ArrayList<>();
        userList.add(new User("login1", "pwd1", "nick1"));
        userList.add(new User("login2", "pwd2", "nick2"));
    }

    @Override
    public boolean changeNick(String oldNick, String newNick) {
        User changeUser = null;
        for (User user: userList) {
            if (user.nick.toLowerCase().equals(newNick.toLowerCase()))
                return false;
            if (user.nick.toLowerCase().equals(oldNick.toLowerCase())){
                changeUser = user;
            }
        }
        if (changeUser != null){
            changeUser.setNick(newNick);
            return true;
        }
        return false;
    }

    public String getNickbyLoginandPwd(String login, String pwd) {
        for (User u : userList) {
            if (u.login.equals(login) && u.pwd.equals(pwd)) {
                return u.nick;
            }
        }
        return null;
    }
}
