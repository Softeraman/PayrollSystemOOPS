// PayrollGUI.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Abstract base class
abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [Name=" + name + ", ID=" + id + ", Salary=" + calculateSalary() + "]";
    }
}

// Full-time Employee
class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// Part-time Employee
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

// Payroll manager
class PayrollSystem {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public boolean removeEmployee(int id) {
        return employeeList.removeIf(e -> e.getId() == id);
    }

    public String displayEmployees() {
        if (employeeList.isEmpty()) return "No employees in the system.\n";
        StringBuilder sb = new StringBuilder();
        for (Employee e : employeeList) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}

// GUI class
public class PayrollGUI {
    private static PayrollSystem payrollSystem = new PayrollSystem();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Payroll System");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField nameField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField salaryField = new JTextField(20);
        JTextField hoursField = new JTextField(20);
        JTextField rateField = new JTextField(20);

        JRadioButton fullTimeBtn = new JRadioButton("Full-Time", true);
        JRadioButton partTimeBtn = new JRadioButton("Part-Time");
        ButtonGroup group = new ButtonGroup();
        group.add(fullTimeBtn);
        group.add(partTimeBtn);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        inputPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        inputPanel.add(new JLabel("Monthly Salary (Full-Time):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(salaryField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        inputPanel.add(new JLabel("Hours Worked (Part-Time):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(hoursField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        inputPanel.add(new JLabel("Hourly Rate (Part-Time):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(rateField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        inputPanel.add(fullTimeBtn, gbc);
        gbc.gridx = 1;
        inputPanel.add(partTimeBtn, gbc);

        frame.add(inputPanel, BorderLayout.NORTH);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Employee List"));
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addButton = new JButton("Add Employee");
        JButton removeButton = new JButton("Remove by ID");
        JButton displayButton = new JButton("Show Employees");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String idText = idField.getText().trim();

            if (name.isEmpty() || idText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and ID are required!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID (must be a number).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fullTimeBtn.isSelected()) {
                try {
                    double salary = Double.parseDouble(salaryField.getText().trim());
                    payrollSystem.addEmployee(new FullTimeEmployee(name, id, salary));
                    JOptionPane.showMessageDialog(frame, "Full-time employee added successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid salary value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    int hours = Integer.parseInt(hoursField.getText().trim());
                    double rate = Double.parseDouble(rateField.getText().trim());
                    payrollSystem.addEmployee(new PartTimeEmployee(name, id, hours, rate));
                    JOptionPane.showMessageDialog(frame, "Part-time employee added successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid hours or rate.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            nameField.setText(""); idField.setText("");
            salaryField.setText(""); hoursField.setText(""); rateField.setText("");
        });

        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                boolean removed = payrollSystem.removeEmployee(id);
                if (removed) {
                    JOptionPane.showMessageDialog(frame, "Employee removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee ID not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        displayButton.addActionListener(e -> {
            outputArea.setText(payrollSystem.displayEmployees());
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}