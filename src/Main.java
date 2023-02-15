import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Main {

    //---------------------Color------------------------//
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    //-------------------------------------------------//
    static Scanner input = new Scanner(System.in);
    static ArrayList<StaffMember> empMembers = new ArrayList<>();

    static int count = 3;
    // validation method check if not number
    public static boolean validNumber(String checkN) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher match = pattern.matcher(checkN);
        return match.matches();
    }

    // ------------------- All insert method -------------------
    static void insertVolunteer(){

        System.out.println(ANSI_CYAN + "---------------------- Insert Volunteer --------------------------");

        System.out.println("ID: " + count);

        System.out.print("Employee Name: ");
        input.nextLine();
        String name = input.next();

        System.out.print("Employee Address: ");
        input.nextLine();
        String address = input.next();

        System.out.print("Employee Salary: ");
        input.nextLine();
        Double salary = Double.valueOf(input.next());

        Volunteer volunteer = new Volunteer(count, name, address, salary) {
            @Override
            double pay() {
                return getSalary();
            }
        };

        empMembers.add(volunteer);
    }

    static void insertSalaries(){
        System.out.println(ANSI_CYAN + "---------------------- Insert Salaries ---------------------------");

        System.out.println("ID: " + count);

        System.out.print("Enter Employee Name: ");
        input.nextLine();
        String name = input.next();

        System.out.print("Employee Address: ");
        input.nextLine();
        String address = input.next();

        System.out.print("Employee Salary: ");
        input.nextLine();
        Double salaryID = Double.valueOf(input.next());

        System.out.print("Employee Bonus: ");
        input.nextLine();
        Double bonus = Double.valueOf(input.next());

        SalariedExample salariedExample = new SalariedExample(count, name, address, salaryID, bonus) {
            @Override
            double pay() {
                return getSalary() + getBonus();
            }
        };

        empMembers.add(salariedExample);
    }

    static void insertHourly(){
        System.out.println(ANSI_CYAN + "------------------------ Insert Hourly ---------------------------");

        System.out.println("ID: " + count);

        System.out.print("Enter Employee Name: ");
        input.nextLine();
        String name = input.next();

        System.out.print("Employee Address: ");
        input.nextLine();
        String address = input.next();

        System.out.print("Working hours: ");
        input.nextLine();
        int hourWorked = Integer.parseInt(input.next());

        System.out.print("Rate/hour: ");
        input.nextLine();
        Double rate = Double.valueOf(input.next());

        HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(count, name, address, hourWorked, rate) {
            @Override
            double pay() {
                return getRate() * getHourWorked();
            }
        };

        empMembers.add(hourlySalaryEmployee);
    }
    // ---------------------------------------------------------

    static void insertEmployee(){

        String insertMenu = """ 
        %s==================================================================%s
        | 1- Volunteer                                                   |
        | 2- Salaries Employee                                           |
        | 3- Hourly Employee                                             |
        | 4- Back                                                        |%s
        ==================================================================""".formatted(ANSI_CYAN, ANSI_PURPLE, ANSI_CYAN);

        System.out.println(insertMenu);
        System.out.print(ANSI_PURPLE + "=> Choose option(1-4) : ");
        String choice = input.next();

        if (validNumber(choice)) {
            switch (choice) {
                case "1" -> insertVolunteer();
                case "2" -> insertSalaries();
                case "3" -> insertHourly();
                case "4" -> systemMenu();
                default -> {
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.out.printf("%sPlease choose to correct option!%n", ANSI_RED);
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                }
            }
        } else {
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.printf("%s=> Wrong input, %sPlease try again!%n", ANSI_RED, ANSI_BLUE);
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        }

        count++;
        insertEmployee();
    }

    static void updateEmployee(){

        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        System.out.print("Enter ID to update: ");
        int upID = input.nextInt();

        for (StaffMember staffMember : empMembers) {
            if (staffMember != null){
                if (staffMember.getId() == upID) {
                    System.out.println("Enter the new name: ");
                    String name = input.next();
                    staffMember.setName(name);

                    System.out.println("Enter the new address: ");
                    String address = input.next();
                    staffMember.setAddress(address);

                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.printf("%sStatus:%s Updated successfully!%n", ANSI_PURPLE, ANSI_YELLOW);
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    break;
                }
            } else {
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                System.out.printf("%sStatus:%s Employee not found!%n", ANSI_PURPLE, ANSI_RED);
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            }

        }
        systemMenu();
    }


    static void displayEmployee(){

        int column = 9;
        Table table = new Table(column, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);

        System.out.println(ANSI_CYAN + "------------------------- Display Employee --------------------------");
        table.addCell("Type");
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Address");
        table.addCell("Salary");
        table.addCell("Bonus");
        table.addCell("Hour");
        table.addCell("Rate");
        table.addCell("Pay");

        for (StaffMember staffMember : empMembers) {
            if (staffMember != null) {
                if (staffMember instanceof Volunteer){
                    table.addCell("Volunteer");
                    table.addCell(String.valueOf(staffMember.getId() + 1));
                    table.addCell(staffMember.getName());
                    table.addCell(staffMember.getAddress());
                    table.addCell("$" + String.valueOf((((Volunteer) staffMember).getSalary())));
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("$" + String.valueOf((((Volunteer) staffMember).pay())));
                } else if (staffMember instanceof SalariedExample){
                    table.addCell("Salaried Emp");
                    table.addCell(String.valueOf(staffMember.getId() + 1));
                    table.addCell(staffMember.getName());
                    table.addCell(staffMember.getAddress());
                    table.addCell("$" + String.valueOf((((SalariedExample) staffMember).getSalary())));
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("$" + String.valueOf((((SalariedExample) staffMember).pay())));
                } else if (staffMember instanceof HourlySalaryEmployee) {
                    table.addCell("Hourly Emp");
                    table.addCell(String.valueOf(staffMember.getId() + 1));
                    table.addCell(staffMember.getName());
                    table.addCell(staffMember.getAddress());
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("---");
                    table.addCell("---");
                }
            }
        }

        System.out.println(table.render());
        System.out.println(ANSI_PURPLE + "1: Next, 2: Back, 3: Return To Menu");
        System.out.print("Options: ");
        String tableInput = input.next();

        systemMenu();
    }

    static void removeEmployee(){
        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        System.out.print("Enter ID to remove: ");
        int id = input.nextInt();
        for (StaffMember staffMember : empMembers) {

            if (staffMember.getId() == id) {
                empMembers.remove(staffMember);
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                System.out.printf("%sEmployee ID: %s%s%n", ANSI_PURPLE, ANSI_YELLOW, id);
                System.out.printf("%sStatus:%s Removed successfully!%n", ANSI_PURPLE, ANSI_YELLOW);
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            } else {
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                System.out.printf("%sEmployee ID: %s%s%n", ANSI_PURPLE, ANSI_YELLOW, id);
                System.out.printf("%sStatus:%s Employee doesn't exist!%n", ANSI_PURPLE, ANSI_RED);
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            }
            break;

        }
        systemMenu();
    }

    static void systemMenu(){

        String menuBanner = """ 
        %s==================================================================%s
        | 1- Insert Employee                                             |
        | 2- Update Employee                                             |
        | 3- Display Employee                                            |
        | 4- Remove Employee                                             |
        | 5- Exit                                                        |%s
        ==================================================================""".formatted(ANSI_CYAN, ANSI_PURPLE, ANSI_CYAN);

        System.out.println(menuBanner);
        System.out.print(ANSI_PURPLE + "=> Choose option(1-5) : ");
        String choice = input.next();

        if (validNumber(choice)) {
            switch (choice) {
                case "1" -> insertEmployee();
                case "2" -> updateEmployee();
                case "3" -> displayEmployee();
                case "4" -> removeEmployee();
                case "5" -> {
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.out.printf("%s=> Good bye, %sSee you again!%n", ANSI_RED, ANSI_BLUE);
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.exit(0);
                }
                default -> {
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                    System.out.printf("%sPlease choose to correct option!%n", ANSI_RED);
                    System.out.println(ANSI_CYAN + "------------------------------------------------------------------");
                }
            }
        } else {
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.printf("%s=> Wrong input, %sPlease try again!%n", ANSI_RED, ANSI_BLUE);
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        }

        systemMenu();
    }
    public static void main(String[] args) {

        int vID = 0;
        String vName = "Yim Klok", vAddress = "Takeo";
        double vSalary = 250;
        Volunteer volunteer = new Volunteer(vID, vName, vAddress, vSalary) {
            @Override
            double pay() {
                return getSalary();
            }
        };

        empMembers.add(volunteer);


        int sID = 1;
        double salaryID = 300, bonus = 50;

        String sName = "Na Reach", sAddress = "Siem Reap";

        SalariedExample salariedExample = new SalariedExample(sID, sName, sAddress, salaryID, bonus) {
            @Override
            double pay() {
                return getSalary() + getBonus();
            }
        };

        empMembers.add(salariedExample);

        int id = 2, hourWorked = 24;
        double rate = 30;
        String name = "Heng Sok", address = "Phnom Penh";

        HourlySalaryEmployee hourlySalaryEmployee = new HourlySalaryEmployee(id, name, address, hourWorked, rate) {
            @Override
            double pay() {
                return getRate() * getHourWorked();
            }
        };

        empMembers.add(hourlySalaryEmployee);

        systemMenu();
    }
}