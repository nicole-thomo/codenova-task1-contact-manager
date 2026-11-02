/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codenova.taskOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicole
 */
public class ContactManagementAppGUI extends JFrame {

    private JTextField tfContactID;
    private JTextField tfContactName;
    private JTextField tfEmailAddress;
    private JTextField tfPhoneNumber;
    private JComboBox<String> cmbCategory;
    private JCheckBox ckbActive;
    private JTextField tfDateAdded;
    private JLabel lblStatus; 
    
    // Search components
    private JTextField tfSearchQuery;
    private JButton btnSearch;
    
    // Core Action buttons
    private JButton btnViewAll;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClearForm;
    private JButton btnExit;
    
    private JTable tblContacts;
    private DefaultTableModel tableModel;
    
    // Menu components
    private JMenuBar mnuBar;
    private JMenu mnuFile;
    private JMenuItem mnuItemLoadDatabase;
    private JMenuItem mnuItemExit;
    
    // Processor instance
    private ContactProcessor contactProcessor = new ContactProcessor();

    public ContactManagementAppGUI() {
        super("Contact Management App");
        setSize(950, 720);
        setLocationRelativeTo(rootPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Menu Bar setup with Load Database and Exit menu items
        mnuBar = new JMenuBar();
        mnuFile = new JMenu("File");
        
        mnuItemLoadDatabase = new JMenuItem("Load Database");
        mnuItemLoadDatabase.addActionListener(new LoadDatabaseMenuListener());
        mnuFile.add(mnuItemLoadDatabase);
        
        mnuFile.addSeparator();
        
        mnuItemExit = new JMenuItem("Exit");
        mnuItemExit.addActionListener(new ExitButtonListener());
        mnuFile.add(mnuItemExit);
        
        mnuBar.add(mnuFile);
        setJMenuBar(mnuBar);

        // Title Panel (North)
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBackground(new Color(44, 62, 80));
        JLabel lblTitle = new JLabel("Contact Management System");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        pnlTitle.add(lblTitle);
        add(pnlTitle, BorderLayout.NORTH);

        // Center Panel container
        JPanel pnlCenter = new JPanel(new BorderLayout(10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form Panel (GridLayout simulating the 2 rows of 4 elements)
        JPanel pnlForm = new JPanel(new GridLayout(2, 4, 15, 10));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Contact Details"));

        tfContactID = new JTextField("Generated or selected ID");
        tfContactID.setEditable(false);
        tfContactName = new JTextField("Contact name");
        tfEmailAddress = new JTextField("email@domain.com");
        tfPhoneNumber = new JTextField("10-digit number");

        String[] categories = {"General", "Family", "Work", "Other"};
        cmbCategory = new JComboBox<String>(categories);
        ckbActive = new JCheckBox("Currently active");
        tfDateAdded = new JTextField("yyyy-MM-dd");
        
        lblStatus = new JLabel("No connection");

        // Row 1 components
        JPanel p1 = new JPanel(new BorderLayout(5, 2));
        p1.add(new JLabel("Contact ID:"), BorderLayout.NORTH);
        p1.add(tfContactID, BorderLayout.CENTER);
        pnlForm.add(p1);

        JPanel p2 = new JPanel(new BorderLayout(5, 2));
        p2.add(new JLabel("Contact Name:"), BorderLayout.NORTH);
        p2.add(tfContactName, BorderLayout.CENTER);
        pnlForm.add(p2);

        JPanel p3 = new JPanel(new BorderLayout(5, 2));
        p3.add(new JLabel("Email Address:"), BorderLayout.NORTH);
        p3.add(tfEmailAddress, BorderLayout.CENTER);
        pnlForm.add(p3);

        JPanel p4 = new JPanel(new BorderLayout(5, 2));
        p4.add(new JLabel("Phone Number:"), BorderLayout.NORTH);
        p4.add(tfPhoneNumber, BorderLayout.CENTER);
        pnlForm.add(p4);

        // Row 2 components
        JPanel p5 = new JPanel(new BorderLayout(5, 2));
        p5.add(new JLabel("Category:"), BorderLayout.NORTH);
        p5.add(cmbCategory, BorderLayout.CENTER);
        pnlForm.add(p5);

        JPanel p6 = new JPanel(new BorderLayout(5, 2));
        p6.add(new JLabel("Active:"), BorderLayout.NORTH);
        p6.add(ckbActive, BorderLayout.CENTER);
        pnlForm.add(p6);

        JPanel p7 = new JPanel(new BorderLayout(5, 2));
        p7.add(new JLabel("Date Added:"), BorderLayout.NORTH);
        p7.add(tfDateAdded, BorderLayout.CENTER);
        pnlForm.add(p7);

        JPanel p8 = new JPanel(new BorderLayout(5, 2));
        p8.add(new JLabel("Status:"), BorderLayout.NORTH);
        p8.add(lblStatus, BorderLayout.CENTER);
        pnlForm.add(p8);

        pnlCenter.add(pnlForm, BorderLayout.NORTH);

        // Middle Container for Buttons and Search Bar
        JPanel pnlActionArea = new JPanel(new BorderLayout(5, 5));

        // Button Panel (FlowLayout)
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnViewAll = new JButton("View All");
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClearForm = new JButton("Clear Form");
        btnExit = new JButton("Exit");

        btnViewAll.addActionListener(new ViewAllButtonListener());
        btnAdd.addActionListener(new AddButtonListener());
        btnUpdate.addActionListener(new UpdateButtonListener());
        btnDelete.addActionListener(new DeleteButtonListener());
        btnClearForm.addActionListener(new ClearFormButtonListener());
        btnExit.addActionListener(new ExitButtonListener());

        pnlButtons.add(btnViewAll);
        pnlButtons.add(btnAdd);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnClearForm);
        pnlButtons.add(btnExit);

        pnlActionArea.add(pnlButtons, BorderLayout.NORTH);

        // Search Panel with Search Text Field and Search Button
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlSearch.setBorder(BorderFactory.createTitledBorder("Search Contacts (by Name or Phone)"));
        tfSearchQuery = new JTextField(25);
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new SearchButtonListener());

        pnlSearch.add(new JLabel("Query:"));
        pnlSearch.add(tfSearchQuery);
        pnlSearch.add(btnSearch);

        pnlActionArea.add(pnlSearch, BorderLayout.CENTER);
        pnlCenter.add(pnlActionArea, BorderLayout.CENTER);

        // Table setup with exact column headings required
        String[] columnNames = {"ID", "Contact Name", "Email Address", "Phone Number", "Category", "Active", "Date Added"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblContacts = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblContacts);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Contact Records"));

        pnlCenter.add(scrollPane, BorderLayout.SOUTH);

        add(pnlCenter, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private class LoadDatabaseMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                contactProcessor.connectDatabase();
                lblStatus.setText("Connected");
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(rootPane, "Database error: "+ ex.getMessage());
            }
        }
    }

    private class ViewAllButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!contactProcessor.isConnected()) {
                   JOptionPane.showMessageDialog(rootPane, "Connect a database first");
                   return;
                }

                tableModel.setRowCount(0);
                ArrayList<ContactDetails> contactList = contactProcessor.getAllContacts();
                
                for(ContactDetails details : contactList) {
                    Object[] row = {
                        details.getContactId(),
                        details.getContactName(),
                        details.getEmailAddress(),
                        details.getPhoneNumber(),
                        details.getCategory(),
                        details.isActive(),
                        java.sql.Date.valueOf(details.getDateAdded())
                    };
                    tableModel.addRow(row);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Database error: "+ ex.getMessage());
            }
        }
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!contactProcessor.isConnected()) {
                    JOptionPane.showMessageDialog(rootPane, "Connect a database first");
                    return;
                }
                
                String email = tfEmailAddress.getText().toLowerCase();
                String emailRegex = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";
                
                if(!email.matches(emailRegex)) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid email address");
                    return;
                }
                if(email.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Fill in email address");
                    return;
                }
                
                String phoneNum = tfPhoneNumber.getText().trim();
                
                if(phoneNum.length() == 10) {
                    for(int i=0; i<phoneNum.length(); i++) {
                        char c = phoneNum.charAt(i);
                        if(!Character.isDigit(c)) {
                            JOptionPane.showMessageDialog(rootPane, "Enter valid numerical values");
                            return;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Phone number must be 10 digits long");
                    return;
                }

                LocalDate date = LocalDate.parse(tfDateAdded.getText().trim());
                String category = cmbCategory.getSelectedItem().toString();
                boolean active = ckbActive.isSelected();
                
                String contactName = tfContactName.getText().trim();
                
                if(contactName.contains("Contact name"))
                {
                    JOptionPane.showMessageDialog(rootPane, "Invalid Contact Name");
                    return;
                }
                
                ContactDetails newContact = new ContactDetails(
                    tfContactName.getText().trim(),
                    email,
                    phoneNum,
                    category,
                    active,
                    date
                );
                
                int rows = contactProcessor.addContact(newContact);
                JOptionPane.showMessageDialog(rootPane, "Successfully added "+ rows+" rows");
                
                btnViewAll.doClick();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Database error: "+ ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Input error (Check date format yyyy-MM-dd): " + ex.getMessage());
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchtext = tfSearchQuery.getText().trim().toLowerCase();
            
            if(searchtext.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Can not search for a blank entry");
                return;
            }
            
            try {
                if(!contactProcessor.isConnected()) {
                   JOptionPane.showMessageDialog(rootPane, "Connect a database first");
                   return;
                }

                tableModel.setRowCount(0);
                ArrayList<ContactDetails> contactList = contactProcessor.getAllContacts();
                
                boolean found = false;
                for(ContactDetails details : contactList) {
                    String dbname = details.getContactName().toLowerCase();
                    String dbphonenumber = details.getPhoneNumber().toLowerCase();
                    
                    if(dbname.contains(searchtext) || dbphonenumber.contains(searchtext)) {
                         found = true;
                         Object[] row = {
                            details.getContactId(),
                            details.getContactName(),
                            details.getEmailAddress(),
                            details.getPhoneNumber(),
                            details.getCategory(),
                            details.isActive(),
                            java.sql.Date.valueOf(details.getDateAdded())
                         };
                         tableModel.addRow(row);
                    }
                }
                if(found == false) {
                    JOptionPane.showMessageDialog(rootPane, "Contact does not exists");
                }
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(rootPane, "Database error: "+ex.getMessage());
            }
        }
    }

    private class LoadSelectedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                contactProcessor.getAllContacts();
            } catch (SQLException ex) {
               // TODO: Handle exception
            }
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!contactProcessor.isConnected()) {
                JOptionPane.showMessageDialog(rootPane, "Connect a database first");
                return;
            }

            String message = "Enter the Id Number of the contact you would like to update in the database.";
            String userId = JOptionPane.showInputDialog(rootPane, message);
            
            if (userId == null || userId.trim().isEmpty()) {
                return; 
            }
            
            try {
                int userIDNum = Integer.parseInt(userId.trim());
                
                String name = JOptionPane.showInputDialog(rootPane, "Enter Contact Name");
                if (name == null) return;
                
                String email = JOptionPane.showInputDialog(rootPane, "Enter valid email address");
                if (email == null) return;
                
                String phoneNumber = JOptionPane.showInputDialog(rootPane, "Enter 10-Digit number");
                if (phoneNumber == null) return;
                
                int categoryResult = JOptionPane.showConfirmDialog(rootPane, cmbCategory, "Select Category", JOptionPane.OK_CANCEL_OPTION);
                if (categoryResult != JOptionPane.OK_OPTION) {
                    return;
                }
                String category = cmbCategory.getSelectedItem().toString();
                
                String activeStr = JOptionPane.showInputDialog(rootPane, "Is the Contact Active? (enter true or false)");
                if (activeStr == null) return;
                boolean active = Boolean.parseBoolean(activeStr.trim());
                
                LocalDate date = LocalDate.now();
                
                email = email.toLowerCase().trim();
                String emailRegex = "^[a-z0-9+_.-]+@[a-z0-9.-]+$";
                if (!email.matches(emailRegex)) {
                    JOptionPane.showMessageDialog(rootPane, "Invalid email entered");
                    return;
                }
                
                if (phoneNumber.length() == 10) {
                    for (int i = 0; i < phoneNumber.length(); i++) {
                        char c = phoneNumber.charAt(i);
                        if (!Character.isDigit(c)) {
                            JOptionPane.showMessageDialog(rootPane, "Enter valid numerical values");
                            return;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Phone Number must be 10 digits long");
                    return;
                }
                
                ContactDetails updatedDetails = new ContactDetails(name.trim(), email, phoneNumber.trim(), category, active, date);
                int rowsAffected = contactProcessor.updateContact(userIDNum, updatedDetails);
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully updated contact!");
                    btnViewAll.doClick(); 
                } else {
                    JOptionPane.showMessageDialog(rootPane, "User Id does not exist");
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Please enter a valid numeric ID.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Database error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Input error: " + ex.getMessage());
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!contactProcessor.isConnected()) {
                    JOptionPane.showMessageDialog(rootPane, "Connect a database first");
                    return;
                }

                int id = Integer.parseInt(JOptionPane.showInputDialog(rootPane, "Enter the contact id you want to delete: "));
                
                int rows = contactProcessor.deleteContact(id);
                if(rows > 0) {
                    JOptionPane.showMessageDialog(rootPane, rows +" rows deleted");
                    btnViewAll.doClick();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "The id number does not exist");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(rootPane, "Please enter a valid numeric ID.");
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }

    private class ClearFormButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tfContactID.setText("generated or selected ID");
            tfContactName.setText("contact name");
            tfDateAdded.setText("yyyy-MM-dd");
            tfEmailAddress.setText("email@domain.com");
            tfPhoneNumber.setText("10-digit number");
            tfSearchQuery.setText("");
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                contactProcessor.closeConnection();
                System.exit(0);
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManagementAppGUI();
            }
        });
    }
}