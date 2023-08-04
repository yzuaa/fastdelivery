package com.laioffer.fastdelivery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "order_item")
@JsonDeserialize(builder = OrderItem.Builder.class)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Double weight;

    private String description;

    @JsonProperty("pickup_address")
    private String pickupAddress;

    @JsonProperty("delivery_address")
    private String deliveryAddress;

    public OrderItem() {}

    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.weight = builder.weight;
        this.description = builder.description;
        this.pickupAddress = builder.pickupAddress;
        this.deliveryAddress = builder.deliveryAddress;
    }


    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("order")
        private Order order;

        @JsonProperty("weight")
        private Double weight;

        @JsonProperty("description")
        private String description;

        @JsonProperty("pickup_address")
        private String pickupAddress;

        @JsonProperty("delivery_address")
        private String deliveryAddress;


        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setWeight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPickupAddress(String pickupAddress) {
            this.pickupAddress = pickupAddress;
            return this;
        }


        public Builder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
