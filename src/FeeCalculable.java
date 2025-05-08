public interface FeeCalculable {
    double calculateFee();
}

public class Subscription implements FeeCalculable {
    private Member.MembershipType membershipType;
    private int age;

    // Constructor, getters, setters...

    @Override
    public double calculateFee() {
        // Beregn kontingent baseret på regler
        return calculateFee();
    }
}

