//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import run.ClientRun;

public class MessageView extends JFrame {
    String userChat = "";
    private JButton btnLeaveChat;
    private JButton btnSend;
    private JTextArea contentChat;
    private JLabel infoUserChat;
    private JScrollPane jScrollPane1;
    private JTextField tfMessage;

    public MessageView() {
        this.initComponents();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(MessageView.this, "Do you want to leave chat?", "Leave chat?", 0, 3) == 0) {
                    ClientRun.socketHandler.leaveChat(MessageView.this.userChat);
                    MessageView.this.dispose();
                }

            }
        });
    }

    public void setInfoUserChat(String username) {
        this.userChat = username;
        this.infoUserChat.setText("Chat with: " + username);
    }

    public void setContentChat(String chat) {
        this.contentChat.append(chat);
    }

    public void eventSendMessage() {
        String message = this.tfMessage.getText().trim();
        if (message.equals("")) {
            this.tfMessage.grabFocus();
        } else {
            ClientRun.socketHandler.sendMessage(this.userChat, message);
            this.tfMessage.setText("");
            this.tfMessage.grabFocus();
        }

    }

    private void initComponents() {
        this.tfMessage = new JTextField();
        this.btnSend = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.contentChat = new JTextArea();
        this.infoUserChat = new JLabel();
        this.btnLeaveChat = new JButton();
        this.setDefaultCloseOperation(0);
        this.tfMessage.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                MessageView.this.tfMessageKeyPressed(evt);
            }
        });
        this.btnSend.setText("Send");
        this.btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MessageView.this.btnSendActionPerformed(evt);
            }
        });
        this.contentChat.setEditable(false);
        this.contentChat.setColumns(20);
        this.contentChat.setRows(5);
        this.jScrollPane1.setViewportView(this.contentChat);
        this.infoUserChat.setFont(new Font("Tahoma", 0, 18));
        this.infoUserChat.setText("Chat with:");
        this.btnLeaveChat.setBackground(new Color(255, 102, 0));
        this.btnLeaveChat.setForeground(new Color(204, 255, 255));
        this.btnLeaveChat.setText("Leave chat");
        this.btnLeaveChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MessageView.this.btnLeaveChatActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(this.infoUserChat, -2, 252, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.btnLeaveChat, -2, 100, -2)).addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jScrollPane1, -2, 441, -2).addGroup(layout.createSequentialGroup().addComponent(this.tfMessage).addGap(18, 18, 18).addComponent(this.btnSend, -2, 112, -2)))).addContainerGap(21, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(26, 32767).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.infoUserChat, -1, 36, 32767).addComponent(this.btnLeaveChat, -1, -1, 32767)).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 213, -2).addGap(18, 18, 18).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.btnSend, -2, 38, -2).addComponent(this.tfMessage, -2, 38, -2)).addGap(28, 28, 28)));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    private void btnLeaveChatActionPerformed(ActionEvent evt) {
        ClientRun.socketHandler.leaveChat(this.userChat);
        this.dispose();
    }

    private void btnSendActionPerformed(ActionEvent evt) {
        this.eventSendMessage();
    }

    private void tfMessageKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            this.eventSendMessage();
        }

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
            Logger.getLogger(MessageView.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(MessageView.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(MessageView.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            UnsupportedLookAndFeelException ex = var8;
            Logger.getLogger(MessageView.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new MessageView()).setVisible(true);
            }
        });
    }
}
