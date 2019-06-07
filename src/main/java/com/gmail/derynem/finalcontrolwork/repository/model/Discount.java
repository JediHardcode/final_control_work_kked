package com.gmail.derynem.finalcontrolwork.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "T_DISCOUNT")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "F_NAME")
    private String name;
    @Column(name = "F_PERCENT")
    private String percent;
    @Column(name = "F_DATE")
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_USER_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) &&
                Objects.equals(name, discount.name) &&
                Objects.equals(percent, discount.percent) &&
                Objects.equals(date, discount.date) &&
                Objects.equals(user.getId(), discount.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, percent, date, user.getId());
    }
}