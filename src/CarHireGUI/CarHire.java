/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarHireGUI;

public class CarHire {

    private String customerName;
    private String licenseNumber;
    private int days_hired;

    public CarHire(String customerName, String licenseNumber, int days_hired) {
        this.customerName = customerName;
        this.licenseNumber = licenseNumber;
        this.days_hired = days_hired;
    }

    public CarHire() {
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setDays_hired(int days_hired) {
        this.days_hired = days_hired;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public int getDays_hired() {
        return days_hired;
    }
    
    public double calculateHireRental(){
        double hire_price = 0;
        if (getDays_hired() > 0 && getDays_hired() <= 3) {
            hire_price = getDays_hired() * 34.50;
        } else if (getDays_hired() > 3 && getDays_hired() <= 7) {
            hire_price = getDays_hired() * 30.50;
        }else if (getDays_hired() > 7) {
            hire_price = getDays_hired() * 22.50;
        }
        return hire_price;       
    }

}
