//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import run.ClientRun;
import run.ClientRun.SceneName;

public class SocketHandler {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    String loginUser = null;
    String roomIdPresent = null;
    float score = 0.0F;
    Thread listener = null;

    public SocketHandler() {
    }

    public String connect(String addr, int port) {
        try {
            InetAddress ip = InetAddress.getByName(addr);
            this.s = new Socket();
            this.s.connect(new InetSocketAddress(ip, port), 4000);
            System.out.println("Connected to " + String.valueOf(ip) + ":" + port + ", localport:" + this.s.getLocalPort());
            this.dis = new DataInputStream(this.s.getInputStream());
            this.dos = new DataOutputStream(this.s.getOutputStream());
            if (this.listener != null && this.listener.isAlive()) {
                this.listener.interrupt();
            }

            this.listener = new Thread(this::listen);
            this.listener.start();
            return "success";
        } catch (IOException var4) {
            IOException e = var4;
            return "failed;" + e.getMessage();
        }
    }

    private void listen() {
        boolean running = true;

        IOException ex;
        while(running) {
            try {
                String received = this.dis.readUTF();
                System.out.println("RECEIVED: " + received);
                switch (received.split(";")[0]) {
                    case "LOGIN":
                        this.onReceiveLogin(received);
                        break;
                    case "REGISTER":
                        this.onReceiveRegister(received);
                        break;
                    case "GET_LIST_ONLINE":
                        this.onReceiveGetListOnline(received);
                        break;
                    case "LOGOUT":
                        this.onReceiveLogout(received);
                        break;
                    case "INVITE_TO_CHAT":
                        this.onReceiveInviteToChat(received);
                        break;
                    case "ACCEPT_MESSAGE":
                        this.onReceiveAcceptMessage(received);
                        break;
                    case "NOT_ACCEPT_MESSAGE":
                        this.onReceiveNotAcceptMessage(received);
                        break;
                    case "LEAVE_TO_CHAT":
                        this.onReceiveLeaveChat(received);
                        break;
                    case "CHAT_MESSAGE":
                        this.onReceiveChatMessage(received);
                        break;
                    case "EXIT":
                        running = false;
                }
            } catch (IOException var7) {
                ex = var7;
                Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
                running = false;
            }
        }

        try {
            this.s.close();
            this.dis.close();
            this.dos.close();
        } catch (IOException var6) {
            ex = var6;
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        JOptionPane.showMessageDialog((Component)null, "Mất kết nối tới server", "Lỗi", 0);
        ClientRun.closeAllScene();
        ClientRun.openScene(SceneName.CONNECTSERVER);
    }

    public void login(String email, String password) {
        String data = "LOGIN;" + email + ";" + password;
        this.sendData(data);
    }

    public void register(String email, String password) {
        String data = "REGISTER;" + email + ";" + password;
        this.sendData(data);
    }

    public void logout() {
        this.loginUser = null;
        this.sendData("LOGOUT");
    }

    public void close() {
        this.sendData("CLOSE");
    }

    public void getListOnline() {
        this.sendData("GET_LIST_ONLINE");
    }

    public void getInfoUser(String username) {
        this.sendData("GET_INFO_USER;" + username);
    }

    public void checkStatusUser(String username) {
        this.sendData("CHECK_STATUS_USER;" + username);
    }

    public void inviteToChat(String userInvited) {
        this.sendData("INVITE_TO_CHAT;" + this.loginUser + ";" + userInvited);
    }

    public void leaveChat(String userInvited) {
        this.sendData("LEAVE_TO_CHAT;" + this.loginUser + ";" + userInvited);
    }

    public void sendMessage(String userInvited, String message) {
        String chat = "[" + this.loginUser + "] : " + message + "\n";
        ClientRun.messageView.setContentChat(chat);
        this.sendData("CHAT_MESSAGE;" + this.loginUser + ";" + userInvited + ";" + message);
    }

    public void inviteToPlay(String userInvited) {
        this.sendData("INVITE_TO_PLAY;" + this.loginUser + ";" + userInvited);
    }

    public void leaveGame(String userInvited) {
        this.sendData("LEAVE_TO_GAME;" + this.loginUser + ";" + userInvited + ";" + this.roomIdPresent);
    }

    public void startGame(String userInvited) {
        this.sendData("START_GAME;" + this.loginUser + ";" + userInvited + ";" + this.roomIdPresent);
    }

    public void sendData(String data) {
        try {
            this.dos.writeUTF(data);
        } catch (IOException var3) {
            IOException ex = var3;
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

    }

    private void onReceiveLogin(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("failed")) {
            String failedMsg = splitted[2];
            JOptionPane.showMessageDialog(ClientRun.loginView, failedMsg, "Lỗi", 0);
        } else if (status.equals("success")) {
            this.loginUser = splitted[2];
            ClientRun.closeScene(SceneName.LOGIN);
            ClientRun.openScene(SceneName.HOMEVIEW);
//            ClientRun.homeView.setUsername(this.loginUser);
//            ClientRun.homeView.setUserScore(this.score);
        }

    }

    private void onReceiveRegister(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("failed")) {
            String failedMsg = splitted[2];
            JOptionPane.showMessageDialog(ClientRun.registerView, failedMsg, "Lỗi", 0);
        } else if (status.equals("success")) {
            JOptionPane.showMessageDialog(ClientRun.registerView, "Register account successfully! Please login!");
            ClientRun.closeScene(SceneName.REGISTER);
            ClientRun.openScene(SceneName.LOGIN);
        }

    }

    private void onReceiveGetListOnline(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            int userCount = Integer.parseInt(splitted[2]);
            Vector vheader = new Vector();
            vheader.add("User");
            Vector vdata = new Vector();
            if (userCount > 1) {
                for(int i = 3; i < userCount + 3; ++i) {
                    String user = splitted[i];
                    if (!user.equals(this.loginUser) && !user.equals("null")) {
                        Vector vrow = new Vector();
                        vrow.add(user);
                        vdata.add(vrow);
                    }
                }

                ClientRun.homeView.setListUser(vdata, vheader);
            } else {
                ClientRun.homeView.resetTblUser();
            }
        } else {
            JOptionPane.showMessageDialog(ClientRun.loginView, "Have some error!", "Lỗi", 0);
        }

    }

    private void onReceiveLogout(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            ClientRun.closeScene(SceneName.HOMEVIEW);
            ClientRun.openScene(SceneName.LOGIN);
        }

    }

    private void onReceiveInviteToChat(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            String userHost = splitted[2];
            String userInvited = splitted[3];
            if (JOptionPane.showConfirmDialog(ClientRun.homeView, userHost + " want to chat with you?", "Chat?", 0) == 0) {
                ClientRun.openScene(SceneName.MESSAGEVIEW);
                ClientRun.messageView.setInfoUserChat(userHost);
                this.sendData("ACCEPT_MESSAGE;" + userHost + ";" + userInvited);
            } else {
                this.sendData("NOT_ACCEPT_MESSAGE;" + userHost + ";" + userInvited);
            }
        }

    }

    private void onReceiveAcceptMessage(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            String userHost = splitted[2];
            String userInvited = splitted[3];
            ClientRun.openScene(SceneName.MESSAGEVIEW);
            ClientRun.messageView.setInfoUserChat(userInvited);
        }

    }

    private void onReceiveNotAcceptMessage(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            String userHost = splitted[2];
            String userInvited = splitted[3];
            JOptionPane.showMessageDialog(ClientRun.homeView, userInvited + " don't want to chat with you!");
        }

    }

    private void onReceiveLeaveChat(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            String userHost = splitted[2];
            String userInvited = splitted[3];
            ClientRun.closeScene(SceneName.MESSAGEVIEW);
            JOptionPane.showMessageDialog(ClientRun.homeView, userHost + " leave to chat!");
        }

    }

    private void onReceiveChatMessage(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            String userHost = splitted[2];
            String userInvited = splitted[3];
            String message = splitted[4];
            String chat = "[" + userHost + "] : " + message + "\n";
            ClientRun.messageView.setContentChat(chat);
        }

    }

    private void onReceiveNotAcceptPlay(String received) {
        String[] splitted = received.split(";");
        String status = splitted[1];
        if (status.equals("success")) {
            String userHost = splitted[2];
            String userInvited = splitted[3];
            JOptionPane.showMessageDialog(ClientRun.homeView, userInvited + " don't want to play with you!");
        }

    }

    private void onReceiveCheckStatusUser(String received) {
        String[] splitted = received.split(";");
        String status = splitted[2];
        ClientRun.homeView.setStatusCompetitor(status);
    }

    public String getLoginUser() {
        return this.loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public Socket getS() {
        return this.s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public String getRoomIdPresent() {
        return this.roomIdPresent;
    }

    public void setRoomIdPresent(String roomIdPresent) {
        this.roomIdPresent = roomIdPresent;
    }
}
