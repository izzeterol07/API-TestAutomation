package pojos;

public class PojoHerokuappRequestBody {

//      1- Tum variable’lari "private" olarak olusturalim

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private PojoHerokuappBookingdates bookingdates;
    private String additionalneeds;

//      2- Tum variable’lar icin getter( ) and setter( ) metodlari olusturalim

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public PojoHerokuappBookingdates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(PojoHerokuappBookingdates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

//      4- Tum parametreleri iceren bir constructor olusturalim

    public PojoHerokuappRequestBody(String firstname, String lastname, int totalprice, boolean depositpaid, PojoHerokuappBookingdates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

//      5- Default constructor (parametresiz) olusturalim

    public PojoHerokuappRequestBody() {
    }

//      6- toString( ) metodu olusturalim

    @Override
    public String toString() {
        return "PojoHerokuappRequestBody{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
