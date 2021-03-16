/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.articlerevsys;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nagarjun Prem
 */
@ManagedBean(name="reviewcont")
@RequestScoped
public class Reviews {
    
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

    public Reviews() {
    }

    public Reviews(int review_id, String review_title, String pub_title, String pub_url, String summary, String post_negt, String major_points, String minor_points, String recommendation, String reviewer_name) {
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
    }

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
    
    public String add()
    {
        if(review_id !=0)
            return "login";
            return null;
    }
        
        
    
}
