package com.laioffer.fastdelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "order")
@JsonDeserialize(builder = Order.Builder.class)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("created_date")
    private LocalDate createdDate;

    private Double price;

    @JsonProperty("delivery_method")
    private String deliveryMethod;

    @JsonProperty("expected_delivery_time")
    private Double expectedDeliveryTime;

    private Boolean status;

    public Order() {}

    private Order(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.createdDate = builder.createdDate;
        this.price = builder.price;
        this.deliveryMethod = builder.deliveryMethod;
        this.expectedDeliveryTime = builder.expectedDeliveryTime;
        this.status = builder.status;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setExpectedDeliveryTime(Double expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Double getPrice() {
        return price;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public Double getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("user")
        private User user;

        @JsonProperty("created_date")
        private LocalDate createdDate;

        @JsonProperty("price")
        private Double price;

        @JsonProperty("delivery_method")
        private String deliveryMethod;

        @JsonProperty("expected_delivery_time")
        private Double expectedDeliveryTime;

        @JsonProperty("status")
        private Boolean status;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setDeliveryMethod(String deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
            return this;
        }

        public Builder setExpectedDeliveryTime(Double expectedDeliveryTime) {
            this.expectedDeliveryTime = expectedDeliveryTime;
            return this;
        }

        public Builder setStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
