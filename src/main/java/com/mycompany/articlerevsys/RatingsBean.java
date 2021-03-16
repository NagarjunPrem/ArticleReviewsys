/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.articlerevsys;

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

/**
 *
 * @author Nagarjun Prem
 */
@ManagedBean(name="ratingb")
@SessionScoped
public class RatingsBean implements Serializable{
    
        private List<Reviews> reviews;
        
        private List<Ratings> ratings;
    
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


    
    public List<Reviews> getReviews() throws ClassNotFoundException, SQLException {

       
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String reviewId = paramMap.get("rid");
       
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("rid", reviewId);
        
        
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

        //List<Reviews> reviews = new ArrayList<Reviews>();
        reviews = new ArrayList<>();
        PreparedStatement pstmt = connect
                .prepareStatement("select * from reviews where rid=?");
        
        pstmt.setInt(1,Integer.parseInt(reviewId));
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
           
            reviews.add(new Reviews((rs.getInt("rid")), (rs.getString("rtitle")), (rs.getString("pub_title")), (rs.getString("pub_url")), (rs.getString("summary")), (rs.getString("post_negt")), (rs.getString("maj_points")), (rs.getString("min_points")), (rs.getString("recommendation")), (rs.getString("rev_name"))));
          
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();
                                
        return reviews;

    }
        
    
    
     public List<Ratings> getRatings() throws ClassNotFoundException, SQLException {

       
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String reviewId = paramMap.get("rid");
       
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        httpSession.setAttribute("rid", reviewId);
        
        
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

        //List<Reviews> reviews = new ArrayList<Reviews>();
        ratings = new ArrayList<>();
        PreparedStatement pstmt = connect
                .prepareStatement("select * from ratings where rid=?");
        
        pstmt.setInt(1,Integer.parseInt(reviewId));
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
           
            ratings.add(new Ratings((rs.getString("rating")), (rs.getString("comment"))));
          
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();
                                
        return ratings;

    }
     
    
}
