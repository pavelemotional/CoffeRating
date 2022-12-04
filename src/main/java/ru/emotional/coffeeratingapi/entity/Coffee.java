package ru.emotional.coffeeratingapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Coffee {
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private  Long id;
    private String name;
    private String link;
    private String company;
    private String imageLink;
    private String roasting;
    private String description;
    private Integer weight1;
    private Integer price1;
    private Integer weight2;
    private Integer price2;
    private String processing;
    private String region;
    private Float qGrade;
    public Coffee() {
    }

    public Coffee(String name, String link, String company, String imageLink,
                  String roasting, String description, Integer weight1, Integer price1,
                  Integer weight2, Integer price2, String processing, String region, Float qGrade) {
        this.name = name;
        this.link = link;
        this.company = company;
        this.imageLink = imageLink;
        this.roasting = roasting;
        this.description = description;
        this.weight1 = weight1;
        this.price1 = price1;
        this.weight2 = weight2;
        this.price2 = price2;
        this.processing = processing;
        this.region = region;
        this.qGrade = qGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getRoasting() {
        return roasting;
    }

    public void setRoasting(String roasting) {
        this.roasting = roasting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight1() {
        return weight1;
    }

    public void setWeight1(Integer weight1) {
        this.weight1 = weight1;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1;
    }

    public Integer getWeight2() {
        return weight2;
    }

    public void setWeight2(Integer weight2) {
        this.weight2 = weight2;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getqGrade() {
        return qGrade;
    }

    public void setqGrade(Float qGrade) {
        this.qGrade = qGrade;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", company='" + company + '\'' +
                ", imageLink=" + imageLink +
                ", roasting='" + roasting + '\'' +
                ", description='" + description + '\'' +
                ", weight1=" + weight1 +
                ", price1=" + price1 +
                ", weight2=" + weight2 +
                ", price2=" + price2 +
                ", processing='" + processing + '\'' +
                ", region='" + region + '\'' +
                ", qGrade=" + qGrade +
                '}';
    }
}
