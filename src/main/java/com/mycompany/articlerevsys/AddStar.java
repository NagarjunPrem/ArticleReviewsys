/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.articlerevsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.component.rating.Rating;

/**
 *
 * @author Nagarjun Prem
 */
@ManagedBean(name="addStar")
@SessionScoped
public class AddStar {
    
    private List<Ratings> ratingsList = new ArrayList<>() ;
    private int rating;
    private String comment;
    

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }

    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
    }

    
        
    public AddStar() {
    }    
    
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStars() throws ClassNotFoundException, SQLException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String reviewId = paramMap.get("rid");
        
       
        // Get session object from Session
        
        FacesContext context2 = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context2.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        String uid = httpSession.getAttribute("userId").toString();
        
        /*FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        String uid = httpSession.getAttribute("userId").toString(); */

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

        
        if(uid.isEmpty() || uid.isBlank() || uid == null) uid = "1";
            
        PreparedStatement pstmt = connect
                .prepareStatement("INSERT INTO ratings(uid, rid, rating, comment) VALUES(?,?,?,?);");
//uid = "1";
//reviewId="1";

        //pstmt.setInt(1, (uid));
        pstmt.setInt(1, Integer.parseInt(uid));
        pstmt.setInt(2, Integer.parseInt(reviewId));
        pstmt.setInt(3, rating);
        pstmt.setString(4, comment); 
        pstmt.executeUpdate();

        pstmt.close();
        connect.close();
        
        
       
       // ratingsList.add(new Ratings(1,2,4,"hello"));
        
       // return ratingsList;

    }
    
}
