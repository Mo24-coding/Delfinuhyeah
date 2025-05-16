package model;

public class Subscription {

    public enum MembershipType {
        AKTIV,
        PASSIV
    }

    private MembershipType membershipType;
    private int age;

    public Subscription(MembershipType membershipType, int age) {
        this.membershipType = membershipType;
        this.age = age;
    }

    /**
     * Beregner årligt kontingent ud fra medlemskab og alder.
     * AKTIV:
     *   Under 18 år: 1000 kr
     *   18-59 år:    1600 kr
     *   60+ år:      1200 kr (25% rabat)
     * PASSIV:        500 kr
     */
    public double calculateFee() {
        if (membershipType == MembershipType.PASSIV) {
            return 500;
        } else {
            if (age < 18) {
                return 1000;
            } else if (age >= 60) {
                return 1200;
            } else {
                return 1600;
            }
        }
    }
}
