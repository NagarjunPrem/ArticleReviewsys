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

@ManagedBean
@SessionScoped
public class ReviewsBean implements Serializable {

    
    /* Get session object from Session
  
FacesContext context = FacesContext.getCurrentInstance();
HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
HttpSession httpSession = request.getSession(false);
RequiredObject reqdObj = (RequiredObject) httpSession.getAttribute(attName);*/
    
    private static final long serialVersionUID = 6081417964063918994L;

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

        List<Reviews> reviews = new ArrayList<Reviews>();
        PreparedStatement pstmt = connect
                .prepareStatement("select * from reviews");
        //select rid, rtitle, pub_title, pub_url, summary, post_negt, maj_points, min_points, recommendation, rev_name from reviews
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
           
            reviews.add(new Reviews((rs.getInt("rid")), (rs.getString("rtitle")), (rs.getString("pub_title")), (rs.getString("pub_url")), (rs.getString("summary")), (rs.getString("post_negt")), (rs.getString("maj_points")), (rs.getString("min_points")), (rs.getString("recommendation")), (rs.getString("rev_name"))));
          
        }

        // close resources
        rs.close();
        pstmt.close();
        connect.close();

        // cars.add(new Car(9012,"suzuki", "red", 200, "Chn"));
        return reviews;

    }
    
}
