package club.haozheng.restaurant.pojo;


import java.util.List;

public class OrderTemp {
    private OrderUser orderUser;
    private List<OrderGoods> orderGoods;

    @Override
    public String toString() {
        return "orderTemp{" +
                "order=" + orderUser +
                ", orderGoods=" + orderGoods +
                '}';
    }

    public OrderUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(OrderUser orderUser) {
        this.orderUser = orderUser;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
