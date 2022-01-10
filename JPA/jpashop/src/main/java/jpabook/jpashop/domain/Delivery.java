package jpabook.jpashop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    private DeliveryStatus status;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
