package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ze.liu on 2015/1/9.
 */
public class Parent {

    private boolean hasHotel;
    private HotelBookingPack hotelPack;

    public boolean isHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    public HotelBookingPack getHotelPack() {
        return hotelPack;
    }

    public void setHotelPack(HotelBookingPack hotelPack) {
        this.hotelPack = hotelPack;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Hotel hotel = new Hotel();
        hotel.setName("喜来登");
        hotel.setDays(5);
        hotel.setAddMoney(100);
        hotel.setAddress("北京二环");
        hotel.setIntro("贵");
        hotel.setPhone("1234");

        Hotel hotel2 = new Hotel();
        hotel2.setName("君悦");
        hotel2.setDays(4);
        hotel2.setAddMoney(200);
        hotel2.setAddress("北京三环");
        hotel2.setIntro("便宜");
        hotel2.setPhone("4567");

        HotelBookingPack pack = new HotelBookingPack();
        pack.setCity("北京");
        pack.setCountry("中国");
        pack.setStar("5");
        pack.getHotels().add(hotel);
        pack.getHotels().add(hotel2);

        Parent parent = new Parent();
        parent.setHasHotel(true);
        parent.setHotelPack(pack);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(parent);
        System.out.println(s);
    }
}
