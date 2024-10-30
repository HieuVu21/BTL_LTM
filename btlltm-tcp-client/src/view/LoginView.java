//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import run.ClientRun;
import run.ClientRun.SceneName;

public class LoginView extends JFrame {
    private JButton btnChangeRegister;
    private JButton btnLogin;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPasswordField tfPassword;
    private JTextField tfUsername;

    public LoginView() {
        this.initComponents();
    }

    private void initComponents() {
        this.tfUsername = new JTextField();
        this.btnLogin = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.tfPassword = new JPasswordField();
        this.jLabel3 = new JLabel();
        this.btnChangeRegister = new JButton();
        this.setDefaultCloseOperation(3);
        this.btnLogin.setFont(new Font("Tahoma", 0, 24));
        this.btnLogin.setText("Login");
        this.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LoginView.this.btnLoginActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 0, 48));
        this.jLabel1.setText("Login");
        this.jLabel2.setText("Username");
        this.tfPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LoginView.this.tfPasswordActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Password");
        this.btnChangeRegister.setText("Register");
        this.btnChangeRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LoginView.this.btnChangeRegisterActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(61, 32767).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel2, -2, 74, -2).addComponent(this.jLabel3, -2, 74, -2)).addGap(30, 30, 30).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tfPassword, -1, 336, 32767).addComponent(this.tfUsername)).addGap(49, 49, 49)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.btnLogin, -2, 145, -2).addGap(188, 188, 188)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.btnChangeRegister, -2, 89, -2).addGap(219, 219, 219)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jLabel1, -2, 150, -2).addGap(175, 175, 175)))));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(this.jLabel1, -2, 71, -2).addPreferredGap(ComponentPlacement.RELATED, 33, 32767).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.tfUsername, -2, 31, -2).addComponent(this.jLabel2, -2, 30, -2)).addGap(27, 27, 27).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.tfPassword, -2, 30, -2).addComponent(this.jLabel3, -2, 30, -2)).addGap(31, 31, 31).addComponent(this.btnLogin, -2, 35, -2).addGap(18, 18, 18).addComponent(this.btnChangeRegister).addGap(26, 26, 26)));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    private void tfPasswordActionPerformed(ActionEvent evt) {
    }

    private void btnLoginActionPerformed(ActionEvent evt) {
        String userName = this.tfUsername.getText();
        String password = this.tfPassword.getText();
        if (userName.equals("")) {
            this.tfUsername.grabFocus();
        } else if (password.equals("")) {
            this.tfPassword.grabFocus();
        } else {
            ClientRun.socketHandler.login(userName, password);
        }

    }

    private void btnChangeRegisterActionPerformed(ActionEvent evt) {
        this.dispose();
        ClientRun.openScene(SceneName.REGISTER);
    }
}
