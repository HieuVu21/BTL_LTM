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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import run.ClientRun;
import run.ClientRun.SceneName;

public class RegisterView extends JFrame {
    private JButton btnChangeLogin;
    private JButton btnRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPasswordField tfConfirmPassword;
    private JPasswordField tfPassword;
    private JTextField tfUsername;

    public RegisterView() {
        this.initComponents();
    }

    private void initComponents() {
        this.tfPassword = new JPasswordField();
        this.jLabel3 = new JLabel();
        this.btnChangeLogin = new JButton();
        this.tfUsername = new JTextField();
        this.btnRegister = new JButton();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel4 = new JLabel();
        this.tfConfirmPassword = new JPasswordField();
        this.setDefaultCloseOperation(3);
        this.tfPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RegisterView.this.tfPasswordActionPerformed(evt);
            }
        });
        this.jLabel3.setText("Confirm password");
        this.btnChangeLogin.setText("Login");
        this.btnChangeLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RegisterView.this.btnChangeLoginActionPerformed(evt);
            }
        });
        this.btnRegister.setFont(new Font("Tahoma", 0, 24));
        this.btnRegister.setText("Register");
        this.btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RegisterView.this.btnRegisterActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 0, 48));
        this.jLabel1.setText("Register");
        this.jLabel2.setText("Username");
        this.jLabel4.setText("Password");
        this.tfConfirmPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RegisterView.this.tfConfirmPasswordActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(49, 49, 49).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel2, -2, 74, -2).addComponent(this.jLabel4, -2, 74, -2)).addGap(30, 30, 30)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(ComponentPlacement.RELATED, 30, 32767))).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.tfPassword, -1, 336, 32767).addComponent(this.tfUsername).addComponent(this.tfConfirmPassword, -1, 336, 32767)).addGap(49, 49, 49)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.btnRegister, -2, 145, -2).addGap(188, 188, 188)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.btnChangeLogin, -2, 89, -2).addGap(219, 219, 219)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jLabel1).addGap(175, 175, 175)))));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 71, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.tfUsername, -2, 31, -2).addComponent(this.jLabel2, -2, 30, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.tfPassword, -2, 30, -2).addComponent(this.jLabel4, -2, 30, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel3, -2, 30, -2).addComponent(this.tfConfirmPassword, -2, 30, -2)).addPreferredGap(ComponentPlacement.RELATED, 21, 32767).addComponent(this.btnRegister, -2, 35, -2).addGap(18, 18, 18).addComponent(this.btnChangeLogin).addGap(26, 26, 26)));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    private void tfPasswordActionPerformed(ActionEvent evt) {
    }

    private void btnChangeLoginActionPerformed(ActionEvent evt) {
        this.dispose();
        ClientRun.openScene(SceneName.LOGIN);
    }

    private void btnRegisterActionPerformed(ActionEvent evt) {
        String userName = this.tfUsername.getText();
        String password = this.tfPassword.getText();
        String confirmPassword = this.tfConfirmPassword.getText();
        if (userName.equals("")) {
            this.tfUsername.grabFocus();
        } else if (password.equals("")) {
            this.tfPassword.grabFocus();
        } else if (confirmPassword.equals("")) {
            this.tfConfirmPassword.grabFocus();
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this.btnRegister, "Confirm Password is false!");
            this.tfConfirmPassword.grabFocus();
        } else {
            ClientRun.socketHandler.register(userName, password);
        }

    }

    private void tfConfirmPasswordActionPerformed(ActionEvent evt) {
    }
}
