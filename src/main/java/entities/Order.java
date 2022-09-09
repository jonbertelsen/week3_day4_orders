package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "created", nullable = false, length = 45)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private Set<Orderline> orderlines = new LinkedHashSet<>();

    public Order()
    {
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Set<Orderline> getOrderlines()
    {
        return orderlines;
    }

    public void setOrderlines(Set<Orderline> orderlines)
    {
        this.orderlines = orderlines;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", customer=" + customer +
                '}';
    }

    public void add(Orderline ol)
    {
        if (ol != null)
        {
            this.orderlines.add(ol);
            ol.setOrder(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }
}