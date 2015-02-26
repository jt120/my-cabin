package json;

/**
 * Created by ze.liu on 2015/1/9.
 */
public class HotelOrder {

    private boolean confirm;
    private String country;
    private String city;
    private String star;
    private int roomCount;
    private int days;
    private String startDate;
    private String endDate;
    private HotelBase hotelInfo;

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

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

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public HotelBase getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelBase hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public static void main(String[] args) {

    }
}
