package pojos;

public class PojoHerokuappBookingdates {

    // 1- Tum variable’lari "private" olarak olusturalim

    private String checkin;
    private String checkout;

    // 2- Tum variable’lar icin getter( ) and setter( ) metodlari olusturalim

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 4- Tum parametreleri iceren bir constructor olusturalim

    public PojoHerokuappBookingdates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }


    // 5- Default constructor (parametresiz) olusturalim

    public PojoHerokuappBookingdates() {
    }

    // 6- toString( ) metodu olusturalim

    @Override
    public String toString() {
        return "PojoHerokuappBookingdates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
