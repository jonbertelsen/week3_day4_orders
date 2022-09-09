package entities;

import javax.persistence.*;

@Entity
@Table(name = "orderline")
public class Orderline
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderline_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "products_id", nullable = false)
    private Product products;

    @Column(name = "quantity")
    private Integer quantity;

    public Orderline()
    {
    }

    public Orderline(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Product getProducts()
    {
        return products;
    }

    public void setProducts(Product products)
    {
        this.products = products;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "Orderline{" +
                "id=" + id +
                ", order=" + order +
                ", products=" + products +
                ", quantity=" + quantity +
                '}';
    }
}