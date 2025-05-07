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

    public double calculateFee() {
        double fee = 0;

        if (membershipType == MembershipType.PASSIV) {
            fee = 500;
        } else if (membershipType == MembershipType.AKTIV) {
            if (age < 18) {
                fee = 1000;
            } else if (age >= 60) {
                fee = 1600 * 0.75; // 25% rabat for senior 60+
            } else {
                fee = 1600;
            }
        }

        return fee;
    }

    // Getters og setters
    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
