/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package employee.attendance.system;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceApp extends JFrame {
    private JTextArea attendanceDisplay;
    private JLabel statsLabel;
    private JButton loadButton, statsButton, reportButton;
    private Map<String, Integer> attendanceStats;
    
    public AttendanceApp() {
        setTitle("Employee Attendance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        initializeGUI();
        setVisible(true);
    }
    
    private void initializeGUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Display area
        attendanceDisplay = new JTextArea(25, 60);
        attendanceDisplay.setEditable(false);
        add(new JScrollPane(attendanceDisplay), BorderLayout.CENTER);
        
        // Stats label
        statsLabel = new JLabel("Attendance Statistics: Not calculated");
        add(statsLabel, BorderLayout.NORTH);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        loadButton = new JButton("Load Attendance Data");
        statsButton = new JButton("Calculate Statistics");
        reportButton = new JButton("Save Monthly Report");
        reportButton.setEnabled(false);
        
        buttonPanel.add(loadButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(reportButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        loadButton.addActionListener(e -> loadAttendanceData());
        statsButton.addActionListener(e -> calculateMonthlyStats());
        reportButton.addActionListener(e -> saveMonthlyReport());
        
        attendanceStats = new HashMap<>();
    }
    
    private void loadAttendanceData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("data"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            attendanceDisplay.setText("");
            
            try {
                // TODO: Implement attendance data loading
                // Read from attendance.txt
                // Format: Date,EmployeeID,Status (Present/Absent)
                // Example: 2024-01-15,E001,Present
                // Display in attendanceDisplay
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String currLine = reader.readLine();
                String text="";
                while (currLine!= null) { 
                    text+=currLine+"\n";
                    currLine= reader.readLine();
                }
                attendanceDisplay.setText(text);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AttendanceApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AttendanceApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calculateMonthlyStats() {
        String content = attendanceDisplay.getText();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please load attendance data first!");
            return;
        }
        
        // TODO: Implement statistics calculation
        // Calculate present/absent counts per employee
        // Update attendanceStats map
        // Update statsLabel with summary
        // Enable report button
        attendanceDisplay.setText("");
        String [] employees = content.split("\n");
        int present=0;
        int absent=0;
        for (String employee : employees) {
            String [] empParts = employee.split(",");
            if (empParts[2].equalsIgnoreCase("present")) {
                present++;
            }
            else absent++;
        }
        attendanceStats.put("Absent", absent);
        attendanceStats.put("Present", present);
        attendanceDisplay.setText(attendanceStats.toString());
        reportButton.setEnabled(true);
    }
    
    private void saveMonthlyReport() {
        // TODO: Save monthly attendance report
        // Include employee statistics
        // Add summary and timestamp
        // Save to attendance_report.txt
        
        String path = "C:\\Users\\Bafana Bhuda\\Documents\\NetBeansProjects\\Files\\Employee Attendance System\\attendance_report.txt";
        File file = new File(path);
        
        try {
            BufferedWriter writter = new BufferedWriter(new FileWriter(file, true));
            String text = attendanceStats.toString();
           writter.append(text+"\n");
           writter.close();
           
        } catch (IOException ex) {
            Logger.getLogger(AttendanceApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        AttendanceApp app = new AttendanceApp();
       
    }
}

