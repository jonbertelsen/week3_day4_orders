package dtos;

public class OrderSumDTO
{
    private int order;
    private long totalsum;

    public OrderSumDTO(int order, long totalsum)
    {
        this.order = order;
        this.totalsum = totalsum;
    }

    public int getOrder()
    {
        return order;
    }

    public long getTotalsum()
    {
        return totalsum;
    }

    @Override
    public String toString()
    {
        return "OrderSumDTO{" +
                "order=" + order +
                ", totalsum=" + totalsum +
                '}';
    }
}
