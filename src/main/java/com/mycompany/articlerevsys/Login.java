/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.articlerevsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nagarjun Prem
 */
@ManagedBean(name = "login")
@SessionScoped
public class Login {

    private String username;
    private String password;

    public Login() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validate() throws SQLException {

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        Connection connect = null;

        try {
            connect
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/articleReviewSys?"
                            + "user=student&password=student&useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false");

            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        PreparedStatement pstmt = connect
                .prepareStatement("select * from users where uname=?");
        pstmt.setString(1, username);
        
        //select rid, rtitle, pub_title, pub_url, summary, post_negt, maj_points, min_points, recommendation, rev_name from reviews
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        //db conn
        // sql query and result rs
        if (username.equals("x2019ftr") && password.equals("8055")) {

            return "loginSuccess";
        } else if (username.equals("admin") && password.equals("admin")) {

            // Set session Object in Session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("userActive", username);
            httpSession.setAttribute("userId", "001");
            return "loginSuccess";
            
        } else if (password.equals(rs.getString("upassword"))) {
            // Set session Object in Session

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("userActive", username);
            httpSession.setAttribute("userId", rs.getInt("uid"));
             return "loginSuccess";

        } else {
            return "loginFail";
        }

    }

}
