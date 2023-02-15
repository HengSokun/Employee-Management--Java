public abstract class HourlySalaryEmployee extends StaffMember {
    private final int hourWorked;
    private final double rate;

    public HourlySalaryEmployee(int id, String name, String address, int hourWorked, double rate) {
        super(id, name, address);
        this.hourWorked = hourWorked;
        this.rate = rate;
    }

    public int getHourWorked() {
        return hourWorked;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "HourlySalaryEmployee{" +
                "hourWorked=" + hourWorked +
                ", rate=" + rate +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    abstract double pay();
}
