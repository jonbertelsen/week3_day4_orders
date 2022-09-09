import dtos.OrderSumDTO;
import entities.Customer;
import entities.Order;
import entities.Orderline;
import entities.Product;
import facades.OrderFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Tester
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        OrderFacade orderFacade = OrderFacade.getInstance(emf);

        System.out.println("**** Create Customer ******");
        Customer c1 = orderFacade.createCustomer("Ole", "Olsen");
        System.out.println(c1.toString());

        System.out.println("**** Find customer ******");
        Customer c2 = orderFacade.findCustomer(1);
        System.out.println(c2.toString());

        System.out.println("**** Find all customers ******");
        List<Customer> customerList = orderFacade.getCustomers();
        for (Customer customer : customerList)
        {
            System.out.println(customer.toString());
        }

        System.out.printf("***** Create Product ****** ");
        Product product = orderFacade.createProduct("Nike Pegasus X", "Nice running shoe for long distances", 999);
        System.out.println(product.toString());

        System.out.println("***** Find Product ******");
        Product p1 = orderFacade.findProduct(1);
        System.out.println(product.toString());

        System.out.println("***** Add order to Customer ");
        Order order = orderFacade.addOrderToCustomer(2);
        System.out.println(order.toString());

        System.out.println("**** Create Orderline ****");
        Orderline ol1 = orderFacade.addOrderlineToOrder(1,1,2);
        System.out.println(ol1);

        System.out.println("***** Find all orders for a specific customer");
        List<Order> orderList = orderFacade.getOrdersForCustomer(1);

        // Imperativ programmering (paradigme)
        for (Order o : orderList)
        {
            System.out.println(o);
        }

        // Declarativ programmering eller "functional"
        orderList.forEach(o -> System.out.println(o) );




        System.out.println("*** Find total price for order *****");
        OrderSumDTO totalOrderPrice = orderFacade.getTotalOrderPrice(1);
        System.out.println(String.format("OrderId: %d har total summer kr. %d",
                totalOrderPrice.getOrder(),
                totalOrderPrice.getTotalsum()));
    }

    public void udskriv(Order order)
    {
        System.out.println(order);
    }
}
