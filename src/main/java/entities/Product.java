package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product
{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "products")
    private Set<Orderline> orderlines = new LinkedHashSet<>();

    public Product()
    {
    }

    public Product(String name, String description, Integer price)
    {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public void add(Orderline ol)
    {
        if (ol != null)
        {
            this.orderlines.add(ol);
            ol.setProducts(this);
        }
    }
}