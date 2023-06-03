package pojos;

public class PojoHerokuappResponseBody {

//          1- Tum variable’lari "private" olarak olusturalim

    private int bookingid;
    private PojoHerokuappRequestBody booking;

//          2- Tum variable’lar icin getter( ) and setter( ) metodlari olusturalim

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public PojoHerokuappRequestBody getBooking() {
        return booking;
    }

    public void setBooking(PojoHerokuappRequestBody booking) {
        this.booking = booking;
    }

//          4- Tum parametreleri iceren bir constructor olusturalim

    public PojoHerokuappResponseBody(int bookingid, PojoHerokuappRequestBody booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

//          5- Default constructor (parametresiz) olusturalim

    public PojoHerokuappResponseBody() {
    }

//          6- toString( ) metodu olusturalim

    @Override
    public String toString() {
        return "PojoHerokuappResponseBody{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
