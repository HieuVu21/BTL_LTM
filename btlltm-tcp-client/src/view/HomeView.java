//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import run.ClientRun;

public class HomeView extends JFrame {
    String statusCompetitor = "";
    private JButton btnExit;
    private JButton btnLogout;
    private JButton btnMessage;
    private JButton btnRefresh;
//    private JLabel infoUserScore;
//    private JLabel infoUsername;
    private JLabel jLabel1;
    private JScrollPane jScrollPane2;
    private JTable tblUser;

    public void getUserOnline() {
    }

    public HomeView() {
        this.initComponents();
    }

    public void setStatusCompetitor(String status) {
        this.statusCompetitor = status;
    }

    public void setListUser(Vector vdata, Vector vheader) {
        this.tblUser.setModel(new DefaultTableModel(vdata, vheader));
    }

    public void resetTblUser() {
        DefaultTableModel dtm = (DefaultTableModel)this.tblUser.getModel();
        dtm.setRowCount(0);
    }

//    public void setUsername(String username) {
//        this.infoUsername.setText("Hello: " + username);
//    }
//
//    public void setUserScore(float score) {
//        this.infoUserScore.setText("Score: " + score);
//    }

    private void initComponents() {
        this.btnMessage = new JButton();
        this.jLabel1 = new JLabel();
//        this.infoUsername = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.tblUser = new JTable();
        this.btnRefresh = new JButton();
//        this.infoUserScore = new JLabel();
        this.btnLogout = new JButton();
        this.btnExit = new JButton();
        this.setDefaultCloseOperation(3);

        this.btnMessage.setText("Message");
        this.btnMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeView.this.btnMessageActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 0, 36));
//        this.jLabel1.setText("User online");
//        this.infoUsername.setFont(new Font("Tahoma", 0, 14));
//        this.infoUsername.setText("Hello");
        this.tblUser.setModel(new DefaultTableModel(new Object[0][], new String[]{"User"}) {
            Class[] types = new Class[]{String.class};

            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
        });
        this.tblUser.setAutoResizeMode(4);
        this.tblUser.setSelectionMode(0);
        this.jScrollPane2.setViewportView(this.tblUser);
        this.btnRefresh.setText("Refresh");
        this.btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeView.this.btnRefreshActionPerformed(evt);
            }
        });
//        this.infoUserScore.setFont(new Font("Tahoma", 0, 14));
//        this.infoUserScore.setText("Score");
        this.btnLogout.setBackground(new Color(255, 51, 0));
        this.btnLogout.setFont(new Font("Tahoma", 1, 11));
        this.btnLogout.setForeground(new Color(204, 255, 255));
        this.btnLogout.setText("Logout");
        this.btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeView.this.btnLogoutActionPerformed(evt);
            }
        });

        this.btnExit.setBackground(new Color(255, 51, 0));
        this.btnExit.setFont(new Font("Tahoma", 1, 11));
        this.btnExit.setForeground(new Color(204, 255, 255));
        this.btnExit.setText("EXIT");
        this.btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeView.this.btnExitActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(36, 36, 36).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel1, -2, 235, -2).addGap(122, 122, 122).addComponent(this.btnExit, -2, 79, -2)).addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jScrollPane2, -2, 649, -2).addGroup(layout.createSequentialGroup().addGap(42, 42, 42).addComponent(this.btnMessage).addGap(42, 42, 42).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.btnRefresh, -2, 84, -2).addGap(18, 18, 18).addComponent(this.btnLogout, -2, 88, -2)))))));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(23, 23, 23).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(this.btnExit, -2, 34, -2).addComponent(this.jLabel1, -2, 46, -2)).addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addGap(26, 26, 26).addComponent(this.jScrollPane2, -2, 295, -2).addGap(33, 33, 33).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.btnLogout, -2, 36, -2).addComponent(this.btnRefresh, -2, 36, -2).addComponent(this.btnMessage, -2, 36, -2))))));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

//    private void btnPlayActionPerformed(ActionEvent evt) {
//        int row = this.tblUser.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "You haven't chosen anyone yet! Please select one user.", "ERROR", 0);
//        } else {
//            String userSelected = String.valueOf(this.tblUser.getValueAt(row, 0));
//            ClientRun.socketHandler.checkStatusUser(userSelected);
//            switch (this.statusCompetitor) {
//                case "ONLINE" -> ClientRun.socketHandler.inviteToPlay(userSelected);
//                case "OFFLINE" -> JOptionPane.showMessageDialog(this, "This user is offline.", "ERROR", 0);
//                case "INGAME" -> JOptionPane.showMessageDialog(this, "This user is in game.", "ERROR", 0);
//            }
//        }
//
//    }

    private void btnMessageActionPerformed(ActionEvent evt) {
        int row = this.tblUser.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "You haven't chosen anyone yet! Please select one user.", "ERROR", 0);
        } else {
            String userSelected = String.valueOf(this.tblUser.getValueAt(row, 0));
            System.out.println(userSelected);
            if (userSelected.equals(ClientRun.socketHandler.getLoginUser())) {
                JOptionPane.showMessageDialog(this, "You can not chat yourself.", "ERROR", 0);
            } else {
                ClientRun.socketHandler.inviteToChat(userSelected);
            }
        }

    }

    private void btnRefreshActionPerformed(ActionEvent evt) {
        ClientRun.socketHandler.getListOnline();
    }

    private void btnLogoutActionPerformed(ActionEvent evt) {
        JFrame frame = new JFrame("Logout");
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want Logout", "Logout", 0) == 0) {
            ClientRun.socketHandler.logout();
        }

    }

//    private void btnGetInfoActionPerformed(ActionEvent evt) {
//        int row = this.tblUser.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "You haven't chosen anyone yet! Please select one user.", "ERROR", 0);
//        } else {
//            String userSelected = String.valueOf(this.tblUser.getValueAt(row, 0));
//            System.out.println(userSelected);
//            if (userSelected.equals(ClientRun.socketHandler.getLoginUser())) {
//                JOptionPane.showMessageDialog(this, "You can not see yourself.", "ERROR", 0);
//            } else {
//                ClientRun.socketHandler.getInfoUser(userSelected);
//            }
//        }
//
//    }

    private void btnExitActionPerformed(ActionEvent evt) {
        JFrame frame = new JFrame("EXIT");
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want exit", "EXIT", 0) == 0) {
            ClientRun.socketHandler.close();
            System.exit(0);
        }

    }
}
