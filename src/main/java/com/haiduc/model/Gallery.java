package com.haiduc.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "gallery")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameImage;
    private String descImage;
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date dateCreate;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastEdit;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Gallery() {
    }

    public Gallery(String nameImage, String descImage, String image, Date dateCreate, Date lastEdit) {
        this.nameImage = nameImage;
        this.descImage = descImage;
        this.image = image;
        this.dateCreate = dateCreate;
        this.lastEdit = lastEdit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getDescImage() {
        return descImage;
    }

    public void setDescImage(String descImage) {
        this.descImage = descImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
