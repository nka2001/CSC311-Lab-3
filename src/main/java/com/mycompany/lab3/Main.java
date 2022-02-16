package com.mycompany.lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        insertData("Nick",10000);
        
        
        System.out.println("Please enter the name");
        Scanner scan = new Scanner(System.in);
        
        String name = scan.next();
        
        System.out.println("Please enter the salary");
        
        int salary = scan.nextInt();
        
        insertData(name, salary);
        
        
        
    }

    public static void retrieveData() {
        String dbURL = "";
        Connection c = null;

        try {//this try catch block will connect to the database
            dbURL = "jdbc:ucanaccess://.//Company.accdb";//make sure access is closed before running
            c = DriverManager.getConnection(dbURL);
            System.out.println("Connected");

        } catch (SQLException e) {
            System.out.println("database not found");
        }
        try {//this try catch block will pull data from the database
            String tableName = "Employees";
            Statement st = c.createStatement();
            ResultSet r = st.executeQuery("Select * from " + tableName);

            while (r.next()) {
                int id = r.getInt("ID");
                String name = r.getString("EmployeeName");
                int salary = r.getInt("Salary");
                System.out.printf("%d %s %d\n", id, name, salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String name, int sal) {
        String dbURL = "";
        Connection c = null;

        try {//this try catch block will connect to the database
            dbURL = "jdbc:ucanaccess://.//Company.accdb";//make sure access is closed before running
            c = DriverManager.getConnection(dbURL);
            System.out.println("Connected");

        } catch (SQLException e) {
            System.out.println("database not found");
        }
        try {
            String sql = "INSERT INTO Company(name, sal)" + "VALUES (?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setInt(2, sal);
            
            int row = ps.executeUpdate();
            if(row > 0){
                System.out.println("row added");
            }

        } catch (SQLException e) {
            System.out.println("error adding");
            e.printStackTrace();
        }

    }
    
    public static void deleteData(){
        
        String dbURL = "";
        Connection c = null;

        try {//this try catch block will connect to the database
            dbURL = "jdbc:ucanaccess://.//Company.accdb";//make sure access is closed before running
            c = DriverManager.getConnection(dbURL);
            System.out.println("Connected");

        } catch (SQLException e) {
            System.out.println("database not found");
        }
        
        try{
        
        String sql = "DELETE FROM Company";
        PreparedStatement ps = c.prepareStatement(sql);
        
        int rowsDeleted = ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        
    }
    
    public static void updateSal(String name, int sal){
        
    }

}
