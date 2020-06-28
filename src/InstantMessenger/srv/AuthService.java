package InstantMessenger.srv;

public interface AuthService {
    boolean changeNick(String oldNick, String newNick);

    String getNickbyLoginandPwd(String login, String pwd);
}
