public abstract class SalariedExample extends StaffMember {
    private final double salary, bonus;

    public SalariedExample(int id, String name, String address, double salary, double bonus) {
        super(id, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "SalariedExample{" +
                "salary=" + salary +
                ", bonus=" + bonus +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    abstract double pay();

    public void setSalary(Double salary) {
    }
}
