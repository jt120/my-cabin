package json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ze.liu on 2015/1/9.
 */
public class HotelBookingPack {

    private String country;
    private String city;
    private String star;
    private List<Hotel> hotels = new ArrayList<Hotel>();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
