import java.awt.*;
import javax.swing.*;

public class EmployeePayrollSystem extends JFrame {

    // Fields
    private JTextField txtEmpCode, txtDesignation, txtDOJ, txtName, txtDOB, txtAge, txtExperience,
            txtGender, txtProof, txtEmail, txtContact, txtMonth, txtYear, txtSalary, txtDays,
            txtAbsents, txtMedical, txtPF, txtConvence, txtNetSalary;

    private JTextArea txtAddress, txtSalaryReceipt;

    public EmployeePayrollSystem() {
        setTitle("Employee Payroll Management System | Developed by Shaksam Kumar");
        setSize(1350, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel lblTitle = new JLabel("Employee Payroll Management System");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(38, 38, 38));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(0, 0, 1350, 50);
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(lblTitle);

        // Frame 1 - Employee Details
        JPanel panel1 = new JPanel();
        panel1.setBounds(10, 70, 750, 620);
        panel1.setLayout(null);
        panel1.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        panel1.setBackground(Color.WHITE);
        add(panel1);

        txtEmpCode = addLabeledField(panel1, "Employee Code", 10, 40);
        txtDesignation = addLabeledField(panel1, "Designation", 10, 80);
        txtDOJ = addLabeledField(panel1, "D.O.J", 400, 80);
        txtName = addLabeledField(panel1, "Name", 10, 120);
        txtDOB = addLabeledField(panel1, "D.O.B", 400, 120);
        txtAge = addLabeledField(panel1, "Age", 10, 160);
        txtExperience = addLabeledField(panel1, "Experience", 400, 160);
        txtGender = addLabeledField(panel1, "Gender", 10, 200);
        txtProof = addLabeledField(panel1, "Proof ID", 400, 200);
        txtEmail = addLabeledField(panel1, "Email", 10, 240);
        txtContact = addLabeledField(panel1, "Contact No", 400, 240);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(10, 280, 120, 25);
        lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        panel1.add(lblAddress);

        txtAddress = new JTextArea();
        txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(txtAddress);
        scroll.setBounds(180, 280, 520, 100);
        panel1.add(scroll);

        // Frame 2 - Salary Details
        JPanel panel2 = new JPanel();
        panel2.setBounds(770, 70, 580, 300);
        panel2.setLayout(null);
        panel2.setBorder(BorderFactory.createTitledBorder("Salary Details"));
        panel2.setBackground(Color.WHITE);
        add(panel2);

        txtMonth = addLabeledField(panel2, "Month", 10, 30);
        txtYear = addLabeledField(panel2, "Year", 310, 30);
        txtSalary = addLabeledField(panel2, "Basic Salary", 10, 70);
        txtDays = addLabeledField(panel2, "Total Days", 10, 110);
        txtAbsents = addLabeledField(panel2, "Absents", 310, 110);
        txtMedical = addLabeledField(panel2, "Medical", 10, 150);
        txtPF = addLabeledField(panel2, "Provident Fund", 10, 190);
        txtConvence = addLabeledField(panel2, "Convence", 10, 230);

        JLabel lblNetSalary = new JLabel("Net Salary");
        lblNetSalary.setBounds(10, 270, 140, 25);
        lblNetSalary.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel2.add(lblNetSalary);

        txtNetSalary = new JTextField();
        txtNetSalary.setBounds(150, 270, 150, 25);
        txtNetSalary.setEditable(false);
        panel2.add(txtNetSalary);

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(320, 270, 100, 30);
        btnCalculate.setBackground(Color.GREEN);
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.addActionListener(e -> calculateSalary());
        panel2.add(btnCalculate);

        // Frame 3 - Salary Receipt
        JPanel panel3 = new JPanel();
        panel3.setBounds(770, 380, 580, 310);
        panel3.setLayout(null);
        panel3.setBorder(BorderFactory.createTitledBorder("Salary Receipt"));
        panel3.setBackground(Color.WHITE);
        add(panel3);

        txtSalaryReceipt = new JTextArea();
        txtSalaryReceipt.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        JScrollPane receiptScroll = new JScrollPane(txtSalaryReceipt);
        receiptScroll.setBounds(5, 20, 565, 260);
        panel3.add(receiptScroll);

        JButton btnPrint = new JButton("Print");
        btnPrint.setBounds(230, 270, 100, 30);
        btnPrint.setBackground(Color.BLUE);
        btnPrint.setForeground(Color.WHITE);
        // Add actual print logic here if needed
        panel3.add(btnPrint);
    }

    private JTextField addLabeledField(JPanel panel, String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 140, 25);
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        panel.add(lbl);

        JTextField field = new JTextField();
        field.setBounds(x + 170, y, 200, 25);
        panel.add(field);
        return field;
    }

    private void calculateSalary() {
        try {
            double salary = Double.parseDouble(txtSalary.getText());
            int days = Integer.parseInt(txtDays.getText());
            int absents = Integer.parseInt(txtAbsents.getText());
            double medical = Double.parseDouble(txtMedical.getText());
            double pf = Double.parseDouble(txtPF.getText());
            double convence = Double.parseDouble(txtConvence.getText());

            double per_day = salary / days;
            int work_days = days - absents;
            double gross_salary = (per_day * work_days) + convence - (medical + pf);

            txtNetSalary.setText(String.format("%.2f", gross_salary));
            showReceipt(gross_salary);
        } catch (Exception e) {
            txtSalaryReceipt.setText("Error in calculation: " + e.getMessage());
        }
    }

    private void showReceipt(double netSalary) {
        txtSalaryReceipt.setText("");
        txtSalaryReceipt.append("Employee Code: " + txtEmpCode.getText() + "\n");
        txtSalaryReceipt.append("Name: " + txtName.getText() + "\n");
        txtSalaryReceipt.append("Month: " + txtMonth.getText() + " - Year: " + txtYear.getText() + "\n");
        txtSalaryReceipt.append("Basic Salary: " + txtSalary.getText() + "\n");
        int worked = Integer.parseInt(txtDays.getText()) - Integer.parseInt(txtAbsents.getText());
        txtSalaryReceipt.append("Days Worked: " + worked + "\n");
        txtSalaryReceipt.append("Medical Deduction: " + txtMedical.getText() + "\n");
        txtSalaryReceipt.append("PF Deduction: " + txtPF.getText() + "\n");
        txtSalaryReceipt.append("Convence: " + txtConvence.getText() + "\n");
        txtSalaryReceipt.append(String.format("Net Salary: ₹%.2f\n", netSalary));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeePayrollSystem().setVisible(true));
    }
}
