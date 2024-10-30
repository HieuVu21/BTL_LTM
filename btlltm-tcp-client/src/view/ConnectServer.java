//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import run.ClientRun;
import run.ClientRun.SceneName;

public class ConnectServer extends JFrame {
    private JButton btnConnect;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField txIP;
    private JTextField txPort;

    public ConnectServer() {
        this.initComponents();
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.txIP = new JTextField();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.txPort = new JTextField();
        this.btnConnect = new JButton();
        this.setDefaultCloseOperation(3);
        this.jLabel1.setFont(new Font("Tahoma", 0, 18));
        this.jLabel1.setText("CONNECT TO SERVER");
        this.txIP.setEditable(false);
        this.txIP.setFont(new Font("Tahoma", 0, 14));
        this.txIP.setText("127.0.0.1");
        this.jLabel2.setFont(new Font("Tahoma", 0, 14));
        this.jLabel2.setText("IP");
        this.jLabel3.setFont(new Font("Tahoma", 0, 14));
        this.jLabel3.setText("PORT");
        this.txPort.setEditable(false);
        this.txPort.setFont(new Font("Tahoma", 0, 14));
        this.txPort.setText("2000");
        this.btnConnect.setText("CONNECT");
        this.btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ConnectServer.this.btnConnectActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(32, 32, 32).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel2, -1, -1, 32767).addComponent(this.jLabel3, -1, 56, 32767)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.txIP, -2, 240, -2).addComponent(this.txPort, -2, 240, -2))).addGroup(layout.createSequentialGroup().addGap(113, 113, 113).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel1, -2, 187, -2).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.btnConnect, -2, 174, -2).addGap(4, 4, 4))))).addContainerGap(54, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(this.jLabel1, -2, 34, -2).addGap(39, 39, 39).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.txIP, -2, 37, -2).addComponent(this.jLabel2, -2, 37, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.txPort, -2, 37, -2).addComponent(this.jLabel3, -2, 37, -2)).addGap(35, 35, 35).addComponent(this.btnConnect, -2, 48, -2).addContainerGap(27, 32767)));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    private void btnConnectActionPerformed(ActionEvent evt) {
        String ip;
        int port;
        try {
            ip = this.txIP.getText();
            port = Integer.parseInt(this.txPort.getText());
            if (port < 0 || port > 65535) {
                JOptionPane.showMessageDialog(this, "Port phải từ 0 - 65535", "Sai port", 0);
                this.txPort.requestFocus();
                return;
            }
        } catch (NumberFormatException var5) {
            JOptionPane.showMessageDialog(this, "Port phải là số nguyên", "Sai port", 0);
            this.txPort.requestFocus();
            return;
        }

        this.connect(ip, port);
    }

    private void connect(String ip, int port) {
        (new Thread(() -> {
            String result = ClientRun.socketHandler.connect(ip, port);
            if (result.equals("success")) {
                this.onSuccess();
            } else {
                String failedMsg = result.split(";")[1];
                this.onFailed(failedMsg);
            }

        })).start();
    }

    private void onSuccess() {
        this.dispose();
        ClientRun.openScene(SceneName.LOGIN);
        System.out.println("connect to server thanh cong");
    }

    private void onFailed(String failedMsg) {
        JOptionPane.showMessageDialog(this, failedMsg, "Lỗi kết nối", 0);
    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] var12 = UIManager.getInstalledLookAndFeels();
            int var2 = var12.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                UIManager.LookAndFeelInfo info = var12[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            UnsupportedLookAndFeelException ex = var8;
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new ConnectServer()).setVisible(true);
            }
        });
    }
}
