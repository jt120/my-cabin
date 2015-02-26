package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ze.liu on 2015/1/9.
 */
public class DetailParent {

    private boolean hasHotel;
    private HotelOrder hotelOrder;

    public boolean isHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    public HotelOrder getHotelOrder() {
        return hotelOrder;
    }

    public void setHotelOrder(HotelOrder hotelOrder) {
        this.hotelOrder = hotelOrder;
    }

    static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws JsonProcessingException {
        DetailParent parent = new DetailParent();
        parent.setHasHotel(true);

        HotelOrder order = new HotelOrder();
        parent.setHotelOrder(order);
        order.setConfirm(false);
        order.setCountry("中国");
        order.setCity("北京");
        order.setStar("4星");
        order.setRoomCount(1);
        order.setStartDate("2015-01-15");
        order.setEndDate("2015-01-18");

        String s = objectMapper.writeValueAsString(parent);
        System.out.println(s);

        order.setConfirm(true);
        HotelBase hotel = new HotelBase();
        hotel.setName("喜来登");
        hotel.setAddress("北京二环");
        hotel.setPhone("1234");
        hotel.setIntro("贵");
        order.setHotelInfo(hotel);


        String s1 = objectMapper.writeValueAsString(parent);

        System.out.println(s1);
    }
}
