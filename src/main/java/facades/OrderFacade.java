package facades;

import dtos.OrderSumDTO;
import entities.Customer;
import entities.Order;
import entities.Orderline;
import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderFacade
{
    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    private OrderFacade()
    {
    }

    public static OrderFacade getInstance(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    public Customer createCustomer(String name, String email)
    {
        EntityManager em = emf.createEntityManager();
        Customer customer = new Customer(name, email);

        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
        return customer;
    }


    public Customer findCustomer(int id)
    {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }

    public List<Customer> getCustomers()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public Product createProduct(String name, String description, int price)
    {
        EntityManager em = emf.createEntityManager();
        Product product = new Product(name, description, price);

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
        return product;
    }

    public Product findProduct(int id)
    {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public Order addOrderToCustomer(int customerId)
    {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, customerId);
        Order order = new Order();

        em.getTransaction().begin();
            em.persist(order);
            customer.addOrder(order);
        em.getTransaction().commit();
        em.close();
        return order;


    }

    public Orderline addOrderlineToOrder(int orderId, int productId, int quantity)
    {
        EntityManager em = emf.createEntityManager();
        Order order = em.find(Order.class, orderId);
        Product product = em.find(Product.class, productId);
        Orderline ol = new Orderline(quantity);
        if (order != null && product != null)
        {
            em.getTransaction().begin();
                em.persist(ol);
                order.add(ol);
                product.add(ol);
            em.getTransaction().commit();
        }
        return ol;
    }

    public List<Order> getOrdersForCustomer(int customerId)
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o Join o.customer c WHERE c.id = :c_id", Order.class);
        query.setParameter("c_id", customerId);
        List<Order> orderList = query.getResultList();
        em.close();
        return orderList;
    }

    public OrderSumDTO getTotalOrderPrice(int orderId)
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<OrderSumDTO> query = em.createQuery(
                "SELECT NEW dtos.OrderSumDTO(o.id, sum(p.price * ol.quantity)) " +
                "FROM Order o JOIN o.orderlines ol " +
                "JOIN ol.products p WHERE o.id = :order_id", OrderSumDTO.class);
        query.setParameter("order_id", orderId);
        OrderSumDTO result = query.getSingleResult();
        em.close();
        return result;
    }


}
