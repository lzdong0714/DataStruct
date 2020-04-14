package demo.base;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoginServer {

    private  User userA = new User("lzdong","YCY");
    ConcurrentMap<String, Ticket> onlineCheckMap = new ConcurrentHashMap<>();

    private void simulation(){
        SSOServer        ssoServer = new SSOServer();
        ResourceServer   resourceServer = new ResourceServer();
        WebClient  webClientA = new WebClient();
        WebClient  webClientB = new WebClient();
        webClientA.resgistServer(SSOServer.serverId,ssoServer)
                .resgistServer(ResourceServer.serverId,resourceServer);
        webClientB.resgistServer(SSOServer.serverId,ssoServer)
                .resgistServer(ResourceServer.serverId,resourceServer);

        // 共享一个内存服务
        ssoServer.setOnlineCheckMap(onlineCheckMap);
        resourceServer.setOnlineCheckMap(onlineCheckMap);
        ssoServer.setUser(userA); // 服务器库中存入用户

        webClientA.login(userA);          // A浏览器用户登录,向上sso发送请求，获取token
        webClientA.send("helloA1"); // 向资资源服务器获取信息
        webClientA.send("helloA2"); // 向资资源服务器获取信息
        webClientB.login(userA);          // B浏览器用户登录
        webClientB.send("helloB2"); // B浏览器用户获取信息
//        webClient.login(userA);           // 浏览器用户登录,
        webClientA.send("helloA3"); // A浏览器用户获取信息，无法通过在线检测
    }
    public static void main(String[] args) {
        LoginServer loginServer = new LoginServer();
        loginServer.simulation();
    }
}

interface Client{
    int login(String name,String password);
    int send(String msg);
    int receive();
}

interface Server{
    int validateLogin(String name, String password);
    int send(String msg);
    int receive();
}

@Data
class User{
    public User(String name, String password){
        this.name     = name;
        this.password = password;
    }
    String name;
    String password;
}


class WebClient implements Client{
    private static String clientId = "WebClient";
    private HashMap<String ,Server> serverMap = new HashMap<>(8);
    WebClient resgistServer(String serverName ,Server server){
       serverMap.put(serverName,server);
       return this;
    }
    private Token token; // token 类似refresh token 保存在服务器端
    public int login(User user){
        this.user = user;
        return login(user.getName(),user.getPassword());
    }

    @Override
    public int login(String name, String password) {
        Server server = serverMap.get(SSOServer.serverId);
        SSOServer ssoServer = (SSOServer) server;
        try {
             token = ssoServer.releaseToken(name, password);
            return 0;
        }catch (IllegalAccessException e){
            return -1;
        }

    }

    private User user;

    @Override
    public int send(String msg) {
        Server server = serverMap.get(ResourceServer.serverId);
        ResourceServer resourceServer = (ResourceServer) server;
        resourceServer.receive(msg,token,user);
        return 0;
    }


    @Override
    public int receive() {
        return 0;
    }
}

class AppClient implements  Client{
    private static String clientId = "AppClient";

    @Override
    public int login(String name, String password) {
        return 0;
    }

    @Override
    public int send(String msg) {
        return 0;
    }

    @Override
    public int receive() {
        return 0;
    }
}

class WebServer implements  Server{
    private static String serverId = "WebServer";

    @Override
    public int validateLogin(String name, String password) {
        return 0;
    }

    @Override
    public int send(String msg) {
        return 0;
    }

    @Override
    public int receive() {
        return 0;
    }
}

class AppServer implements  Server{
    public static final String serverId = "AppServer";

    @Override
    public int validateLogin(String name, String password) {
        return 0;
    }

    @Override
    public int send(String msg) {
        return 0;
    }

    @Override
    public int receive() {
        return 0;
    }
}


class SSOServer implements Server{
    static final String serverId = "SSOServer";
    private User user;
    public void setUser(User user){ this.user = user;}

    private Map<String, Ticket> onlineCheckMap;

    public void setOnlineCheckMap(Map<String, Ticket> onlineCheckMap) {
        this.onlineCheckMap = onlineCheckMap;
    }

    //    private List<Ticket> TicketCacheList = new
    private Token releaseToken;

    private void onlineCheckIn(User user,Ticket tokenTicket){
//        Ticket ticket = onlineCheckMap.get(user.getName());
       // 放入新的Token，代替旧的，也即旧的token失效

        onlineCheckMap.put(user.getName(), tokenTicket);
    }

    public Token releaseToken(User user)throws IllegalAccessException{
        return releaseToken(user.getName(),user.getPassword());
    }
    public Token releaseToken(String name, String password)throws IllegalAccessException{
        int i = validateLogin(name, password);
        if(i==0){
            releaseToken = new Token();
            releaseToken.setAlive(true);
            releaseToken.setTicket(new Ticket(LocalDateTime.now()));
            onlineCheckIn(user, releaseToken.getTicket());
            return releaseToken;
        }else {
            throw new IllegalAccessException("用户密码错误");
        }
    }
    @Override
    public int validateLogin(String name, String password) {
        // 用户密码检测
        boolean isUser = (user.name.equals(name) && user.password.equals(password));
        if (isUser) {
            return 0;
        }else {
            return -1;
        }
    }



    @Override
    public int send(String msg) {
        return 0;
    }

    @Override
    public int receive() {
        return 0;
    }
}

class ResourceServer implements Server{
    public static final String serverId = "ResourceServer";
    private Map<String, Ticket> onlineCheckMap;

    public void setOnlineCheckMap(Map<String, Ticket> onlineCheckMap) {
        this.onlineCheckMap = onlineCheckMap;
    }

    public int onlineCheck(User user, Token token){
        long TokenId =  token.getTicket().getTicketId();
        long cacheId = onlineCheckMap.get(user.getName()).getTicketId();
       if(TokenId == cacheId)
           return 0; // 一致的，保持验证
       else return -1; // 不一致，token失效,登录被顶出。

    }

    public int validateToken(Token token)throws IllegalAccessException{
        boolean isAlive = token.getIsAlive();
        if(isAlive)
            return 0;
        else
            throw new IllegalAccessException("token is unaccessable");
    }
    @Override
    public int validateLogin(String name, String password) {
        return 0;
    }



    @Override
    public int send(String msg) {

        return 0;
    }

    public int receive(String msg, Token token, User user){
        try {
            int i = validateToken(token);
            int onlineStatue = onlineCheck(user, token);
            if(onlineStatue != 0){
                System.out.println(msg + "不一致，token失效,登录被顶出。");
                return -1;
            }
            System.out.println(msg + "验证通过，获取资源");
            return i + onlineStatue;
        } catch (IllegalAccessException e) {
            System.out.println(msg + "token is unaccessable");
            return -1;
        }

    }

    @Override
    public int receive() {
        return 0;
    }
}

class Ticket{
    private LocalDateTime time;
    public Ticket(LocalDateTime time){
        this.time = time;
    }
    public long getTicketId(){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}

@Data
class Token{
    private boolean isAlive;
    public boolean getIsAlive(){return  isAlive;}
    private Ticket ticket;
    private String header;
    private String playLoad;
    private String signuture;
}