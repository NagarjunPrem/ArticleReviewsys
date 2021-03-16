/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.articlerevsys;

/**
 *
 * @author Nagarjun Prem
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name="addreview")
@SessionScoped
public class AddReviewsBean implements Serializable {
    
     //private static final long serialVersionUID = 6081417964063918994L;
     
     private int review_id;
	private String review_title;
	private String pub_title;
        private String pub_url;
	private String summary;
        private String post_negt;
	private String major_points;
        private String minor_points;
	private String recommendation;
        private String reviewer_name;

   

   /* public Reviews(int review_id, String review_title, String pub_title, String pub_url, String summary, String post_negt, String major_points, String minor_points, String recommendation, String reviewer_name) {
        this.review_id = review_id;
        this.review_title = review_title;
        this.pub_title = pub_title;
        this.pub_url = pub_url;
        this.summary = summary;
        this.post_negt = post_negt;
        this.major_points = major_points;
        this.minor_points = minor_points;
        this.recommendation = recommendation;
        this.reviewer_name = reviewer_name;
    }*/

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }

    public String getPub_title() {
        return pub_title;
    }

    public void setPub_title(String pub_title) {
        this.pub_title = pub_title;
    }

    public String getPub_url() {
        return pub_url;
    }

    public void setPub_url(String pub_url) {
        this.pub_url = pub_url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPost_negt() {
        return post_negt;
    }

    public void setPost_negt(String post_negt) {
        this.post_negt = post_negt;
    }

    public String getMajor_points() {
        return major_points;
    }

    public void setMajor_points(String major_points) {
        this.major_points = major_points;
    }

    public String getMinor_points() {
        return minor_points;
    }

    public void setMinor_points(String minor_points) {
        this.minor_points = minor_points;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }
    
    
     
     
     
     
     
     public void setReviews() throws ClassNotFoundException, SQLException
     {
         
        // Get session object from Session
  
FacesContext context = FacesContext.getCurrentInstance();
HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
HttpSession httpSession = request.getSession(false);
String uid = httpSession.getAttribute("userId").toString();  
        
       
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
                .prepareStatement("insert into reviews(rid, rtitle, pub_title, pub_url, summary, post_negt, maj_points, min_points, recommendation, rev_name, uid) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        pstmt.setInt(1, review_id);
        pstmt.setString(2,review_title);
        pstmt.setString(3,pub_title);
        pstmt.setString(4,pub_url);
        pstmt.setString(5,summary);
        pstmt.setString(6,post_negt);
        pstmt.setString(7,major_points);
        pstmt.setString(8,minor_points);
        pstmt.setString(9,recommendation);
        pstmt.setString(10,reviewer_name);
        pstmt.setString(11, uid);
        
         
      
        pstmt.executeUpdate();
        
        pstmt.close();
        connect.close();

       
   
     }

}
