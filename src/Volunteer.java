public abstract class Volunteer extends StaffMember {
    private final double salary;

    public Volunteer(int id, String name, String address, double salary) {
        super(id, name, address);
        this.salary = salary;
    }

    public Volunteer(double salary) {

        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    abstract double pay();
}
