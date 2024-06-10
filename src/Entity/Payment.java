/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.ArraySet;
import ADT.HashedDictionary;
import ADT.Iterator;
import ADT.SetInterface;
import assets.ConsoleColors;
import client.driver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
//import java.util.Iterator;

/**
 *
 * @author User
 */
public class Payment implements Comparable<Payment> {

    private static int id = 1000;
    private int paymentId;
    private String paymentMethod;
    private double totalAmount;
    private String paymentDate;
    private Patient patient;
    private SetInterface<Treatment> treatmentSet = new ArraySet<>();

    Scanner sc = new Scanner(System.in);

    public Payment(int paymentId, String paymentMethod, double totalAmount, String paymentDate) {
        this.paymentId = addPaymentId();
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
    }

    public Payment(Patient patient, String paymentMethod, double totalAmount, String paymentDate, SetInterface<Treatment> treatmentSet) {
        this.paymentId = addPaymentId();
        this.patient = patient;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.treatmentSet = treatmentSet;
    }

    public Payment() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Payment.id = id;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public Patient getPatient() {
        return patient;
    }

    public SetInterface<Treatment> getTreatmentSet() {
        return treatmentSet;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTreatmentSet(SetInterface<Treatment> treatmentSet) {
        this.treatmentSet = treatmentSet;
    }

    private int addPaymentId() {
        id++;
        return id;
    }

    @Override
    public int compareTo(Payment e) {
        return (int) (this.paymentId - e.paymentId);
    }

    public void searchPayment() {
        driver d = new driver();
        int idSearch = 0;
        displayPayment();
        do {

            System.out.println("\nSEARCH PAYMENT BY ID, Exit enter (999)");
            System.out.print("Enter staff id exp(1002) : ");
            idSearch = sc.nextInt();

            Iterator<Payment> itr = d.paymentList.iterator();

            while (itr.hasNext()) {

                Payment p = (Payment) itr.next();
                if (p.getPaymentId() == idSearch) {
                    System.out.println(p);
                }

            }

        } while (idSearch == 999);

    }

    public void displayPayment() {
        System.out.println("\n\t\t\t\tDISPLAY ALL Payment (TOTAL Treatment : " + driver.paymentList.getSize() + ")");

        tableFormat();
        Iterator<Payment> itr = driver.paymentList.iterator();
        while (itr.hasNext()) {
            Payment payment = itr.next();
            System.out.println(String.format("%s\t%-30s %-20s %-20s %-20.2f %-20s %-20s", payment.getPaymentId(), payment.getPatient().getPatientId(), payment.getPatient().getIC(), payment.getPaymentMethod(), payment.getTotalAmount(), getTreatmentIdUsed(payment.getTreatmentSet()), payment.getPaymentDate()));

        }
    }

    public void deletePayment() {
        driver d = new driver();
        String idSearch = "";
        int select = 0;
        displayPayment();
        int selectType = 0;
        do {
            System.out.println("\n1. Delete by id");
            System.out.println("2. Delete by number range");
            System.out.print("Select 1 or 2 method : ");
            select = sc.nextInt();
            sc.nextLine();
            System.out.println("\nSEARCH Payment ID TO DELETE, Exit enter (exit)");
            if (select == 1) {
                System.out.print("Enter Payment id exp(1002) ");
            } else if (select == 2) {
                System.out.print("Enter index (2-5): ");
            }
            idSearch = sc.nextLine();
            Iterator<Payment> itr = d.paymentList.iterator();

            while (itr.hasNext()) {

                Payment p = (Payment) itr.next();
                if (select == 1) {
                    int id = Integer.parseInt(idSearch);
                    if (p.getPaymentId() == id) {
                        d.paymentList.remove(p);
                        break;
                    }
                } else if (select == 2) {
                    int start = Integer.parseInt(idSearch.substring(0).substring(0, 1));
                    int end = Integer.parseInt(idSearch.substring(0).substring(2, 3));
                    d.paymentList.removeStartToEnd(start, end);
                    break;
                }

            }

        } while (idSearch.equals("exit") && selectType != 7);

        displayPayment();
    }

    public void paymentMenu() {

        int selectMenu = 0;

        while (selectMenu != 7) {

            System.out.println("\nPayment MODULE");
            System.out.println("1. ADD Payment");
            System.out.println("2. DISPLAY ALL Payment history DETAIL");
            System.out.println("3. UPDATE Payment history DETAIL");
            System.out.println("4. DELETE Payment history");
            System.out.println("5. SEARCH Payment history");
            System.out.println("6. CLEAR ALL Payment history DATA");
            System.out.println("7. EXIT BACK TO MAIN MENU");

            try {
                System.out.print("Enter number (1-7) : ");
                selectMenu = sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("That was not a number.  Please try again.");
            }
            sc.nextLine();

            switch (selectMenu) {
                case 1:
                    addPayment();
                    break;
                case 2:
                    displayPayment();
                    break;
                case 3:
                    updatePayment();
                    break;
                case 4:
                    deletePayment();
                    break;
                case 5:
                    searchPayment();
                    break;
                case 6:
                    clearPayment();
                    break;
                default:
            }

        }

    }

    public void clearPayment() {
        System.out.print("You sure want to clear it all? (y) or (n) : ");
        String confirm = sc.nextLine();
        if (confirm.toLowerCase().equals("y")) {
            driver.paymentList.clear();
            if (driver.paymentList.isEmpty()) {
                System.out.println("\nCLEAR ALL PAYMENT DATA SUCCESSFULLY");
            }
        } else {
            System.out.println("\nCLEAR CANCEL.");
        }
    }

    public void addPayment() {
        SetInterface<Treatment> treatmentSet2 = new ArraySet<>();
        String name = "", ic = "";
        Patient patient2 = null;
        System.out.println("\nNEW PAYMENT DETAIL");

        while (true) {
            while (true) {
                System.out.print("Enter patient name : ");
                name = sc.nextLine();
                if (Pattern.matches("[a-zA-Z][a-zA-Z ]{2,}", name)) {
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter full name with character only.<ERROR>" + ConsoleColors.RESET);
            }

            while (true) {
                System.out.print("Enter patient ic exp:(020916-01-0025): ");
                ic = sc.nextLine();
                if (Pattern.matches("[0-9]{6}-[0-9]{2}-[0-9]{4}", ic)) {
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter correct IC NO format (XXXXXX-XX-XXXX).<ERROR>" + ConsoleColors.RESET);
            }

            java.util.Iterator<Patient> patientList = driver.patientList.getIterator();
            while (patientList.hasNext()) {
                Patient patientDetail = patientList.next();
                if (patientDetail.getName().equals(name) && patientDetail.getIC().equals(ic)) {
                    patient2 = patientDetail;
                    break;
                }
            }
            if (patient2 != null) {
                break;
            }
            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter correct patient details that exist in patient list.<ERROR>" + ConsoleColors.RESET);

        }

        int chooseMethod = 0;
        String payMethod = "";
        while (true) {
            System.out.println("\n+---------------------------+");
            System.out.println("|       Payment Method      |");
            System.out.println("+---------------------------+");
            System.out.println("|    1. Credit Card         |");
            System.out.println("|    2. Online Banking      |");
            System.out.println("|    3. Cash                |");
            System.out.println("+---------------------------+");
            System.out.print("\nPlease select an option: ");

            try {
                chooseMethod = sc.nextInt();
                if (chooseMethod >= 1 && chooseMethod <= 3) {
                    sc.nextLine();
                    if (chooseMethod == 1) {
                        payMethod = "Credit Card";
                    } else if (chooseMethod == 2) {
                        payMethod = "Online Banking";
                    } else {
                        payMethod = "Cash";
                    }
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 3.<ERROR>" + ConsoleColors.RESET);
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
            }
            sc.nextLine();
        }

        String[] idArray = null;
        String treatmentIdUsed = "";
        char firstChar = '0';
        boolean found = false;
        boolean isRepeat = false;
        do {
            found = false;
            isRepeat = false;
            firstChar = '0';
            System.out.println("\n==================================================================================================================================");
            System.out.println(String.format("NO\tID\t%-20s %-40s %-15s %-15s %-20s", "TREATMENT_NAME", "DESCRIPTION", "TYPE", "PRICE", "MEDICINE_ID_USED"));
            System.out.println("=================================================================================================================================");
            ADT.Iterator<Treatment> displayTreatment = driver.treatmentSet.iterator();
            int no = 1;
            while (displayTreatment.hasNext()) {
                System.out.println((no) + "\t" + displayTreatment.next());
                no++;
            }
            System.out.print("Select the treatment NO that are used (use , to separate) : ");
            treatmentIdUsed = sc.nextLine();
            if (treatmentIdUsed.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id used cannot be empty!<ERROR>" + ConsoleColors.RESET);
            } else {
                idArray = treatmentIdUsed.split(",");
                HashedDictionary map = new HashedDictionary<>();
                for (String value : idArray) {

                    firstChar = value.charAt(0);
                    if (firstChar < '0' || firstChar > '9') {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment NO used should type in 1,2,3 !<ERROR>" + ConsoleColors.RESET);
                        break;
                    }

                    if (map.contains(value)) {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment NO repeated!<ERROR>" + ConsoleColors.RESET);
                        isRepeat = true;
                        break;
                    }
                    map.add(value, value);
                }
                if (!(firstChar < '0' || firstChar > '9')) {
                    treatmentSet2.clear();
                    ADT.Iterator<Treatment> getTreatmentId = driver.treatmentSet.iterator();
                    int tableNo = 1, ArrayIndex = 0;
                    String[] getTreatmentIdArray = new String[idArray.length];
                    while (getTreatmentId.hasNext()) {
                        Treatment helperT = getTreatmentId.next();
                        for (int i = 0; i < idArray.length; i++) {
                            if (tableNo == Integer.parseInt(idArray[i])) {
                                getTreatmentIdArray[ArrayIndex] = helperT.getTreatmentId();
                                ArrayIndex++;
                            }
                        }
                        tableNo++;
                    }

                    ADT.Iterator<Treatment> itr = driver.treatmentSet.iterator();
                    while (itr.hasNext()) {
                        Treatment t = (Treatment) itr.next();
                        for (int i = 0; i < idArray.length; i++) {
                            if (t.getTreatmentId().equals(getTreatmentIdArray[i])) {
                                treatmentSet2.add(t);
                                found = true;
                            }
                        }
                    }

                }
            }

        } while (treatmentIdUsed.isEmpty() || !found || isRepeat);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String addpaymentDate;
        addpaymentDate = dateFormat.format(date);

        double addTotalAmount;
        addTotalAmount = calculateTotalAmount(treatmentSet2);

        Payment payment = new Payment(patient2, payMethod, addTotalAmount, addpaymentDate, treatmentSet2);
        driver.paymentList.add(payment);
        tableFormat();
        System.out.println(String.format("%s\t%-30s %-20s %-20s %-20.2f %-20s %-20s", payment.getPaymentId(), name, ic, payMethod, addTotalAmount, treatmentIdUsed, addpaymentDate));
        System.out.println("\n" + ConsoleColors.GREEN + "New Payment Added Successfully." + ConsoleColors.RESET);
    }

    public void updatePayment() {
        SetInterface<Treatment> treatmentSet3 = new ArraySet<>();
        String name = "", ic = "";
        int idSearch = 0;
        int selectType = 0;

        do {
            System.out.println("\nSEARCH PAYMENT ID TO UPDATE, Exit enter (-1)");
            System.out.print("Enter payment id exp(1001) : ");
            idSearch = sc.nextInt();

            Payment payment = null;
            Iterator<Payment> paymentList = driver.paymentList.iterator();
            while (paymentList.hasNext()) {
                Payment paymentDetail = paymentList.next();
                if (paymentDetail.getPaymentId() == idSearch) {
                    payment = paymentDetail;
                    break;
                }
            }

            if (payment != null) {
                Payment Oldpayment = payment;
                do {
                    tableFormat();
                    System.out.println(String.format("%s\t%-30s %-20s %-20s %-20.2f %-20s %-20s", payment.getPaymentId(), payment.getPatient().getName(), payment.getPatient().getIC(), payment.getPaymentMethod(), payment.getTotalAmount(), getTreatmentIdUsed(payment.getTreatmentSet()), payment.getPaymentDate()));

                    System.out.println("\nSELECT TYPE FOR UPDATE PAYMENT DETAIL");
                    System.out.println("1. NAME");
                    System.out.println("2. IC");
                    System.out.println("3. PAYMENT METHOD");
                    System.out.println("4. TOTAL AMOUNT");
                    System.out.println("5. PAYMENT DATE");
                    System.out.println("6. TREATMENT ID USED");
                    System.out.println("7. EXIT UPDATE PROGRAM");

                    System.out.print("Select type of detail for update 1-7 only : ");
                    selectType = sc.nextInt();
                    sc.nextLine();

                    switch (selectType) {
                        case 1:
                            System.out.print("Update patient name : ");
                            name = sc.nextLine();
                            payment.getPatient().setName(name);
                            driver.paymentList.replace(Oldpayment, payment);
                            break;
                        case 2:
                            do {
                                System.out.print("Enter patient ic exp:(02-1111-22-3333): ");
                                ic = sc.nextLine();
                                if (!payment.getPatient().isIcDuplicate(ic)) {
                                    System.out.println("\nError IC duplicate with others.\n");
                                }
                            } while (!payment.getPatient().isIcDuplicate(ic));
                            payment.getPatient().setIC(ic);
                            driver.paymentList.replace(Oldpayment, payment);
                            break;
                        case 3:
                            int chooseMethod = 0;
                            String payMethod = "";
                            while (true) {
                                System.out.println("\n+---------------------------+");
                                System.out.println("|       Payment Method      |");
                                System.out.println("+---------------------------+");
                                System.out.println("|    1. Credit Card         |");
                                System.out.println("|    2. Online Banking      |");
                                System.out.println("|    3. Cash                |");
                                System.out.println("+---------------------------+");
                                System.out.print("\nPlease select an option: ");

                                try {
                                    chooseMethod = sc.nextInt();
                                    if (chooseMethod >= 1 && chooseMethod <= 3) {
                                        sc.nextLine();
                                        if (chooseMethod == 1) {
                                            payMethod = "Credit Card";
                                        } else if (chooseMethod == 2) {
                                            payMethod = "Online Banking";
                                        } else {
                                            payMethod = "Cash";
                                        }
                                        break;
                                    }
                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 3.<ERROR>" + ConsoleColors.RESET);
                                } catch (InputMismatchException e) {
                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                                }
                                sc.nextLine();
                            }
                            payment.setPaymentMethod(payMethod);
                            driver.paymentList.replace(Oldpayment, payment);
                            break;
                        case 4:
                            double totalAmount;
                            System.out.print("Enter total amount : ");
                            totalAmount = sc.nextDouble();
                            payment.setTotalAmount(totalAmount);
                            driver.paymentList.replace(Oldpayment, payment);
                            break;
                        case 5:
                            String inputDate;
                            System.out.print("Enter payment date : ");
                            inputDate = sc.nextLine();
                            payment.setPaymentDate(inputDate);
                            break;
                        case 6:
                            String[] idArray = null;
                            String treatmentIdUsed = "";
                            char firstChar = '0';
                            boolean found = false;
                            boolean isRepeat = false;
                            do {
                                found = false;
                                isRepeat = false;
                                firstChar = '0';
                                System.out.println("\n==================================================================================================================================");
                                System.out.println(String.format("NO\tID\t%-20s %-40s %-15s %-15s %-20s", "TREATMENT_NAME", "DESCRIPTION", "TYPE", "PRICE", "MEDICINE_ID_USED"));
                                System.out.println("=================================================================================================================================");
                                ADT.Iterator<Treatment> displayTreatment = driver.treatmentSet.iterator();
                                int no = 1;
                                while (displayTreatment.hasNext()) {
                                    System.out.println((no) + "\t" + displayTreatment.next());
                                    no++;
                                }
                                System.out.print("Select the treatment NO that are used (use , to separate) : ");
                                treatmentIdUsed = sc.nextLine();
                                if (treatmentIdUsed.isEmpty()) {
                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id used cannot be empty!<ERROR>" + ConsoleColors.RESET);
                                } else {
                                    idArray = treatmentIdUsed.split(",");
                                    HashedDictionary map = new HashedDictionary<>();
                                    for (String value : idArray) {

                                        firstChar = value.charAt(0);
                                        if (firstChar < '0' || firstChar > '9') {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment NO used should type in 1,2,3 !<ERROR>" + ConsoleColors.RESET);
                                            break;
                                        }

                                        if (map.contains(value)) {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment NO repeated!<ERROR>" + ConsoleColors.RESET);
                                            isRepeat = true;
                                            break;
                                        }
                                        map.add(value, value);
                                    }
                                    if (!(firstChar < '0' || firstChar > '9')) {
                                        treatmentSet3.clear();
                                        ADT.Iterator<Treatment> getTreatmentId = driver.treatmentSet.iterator();
                                        int tableNo = 1, ArrayIndex = 0;
                                        String[] getTreatmentIdArray = new String[idArray.length];
                                        while (getTreatmentId.hasNext()) {
                                            Treatment helperT = getTreatmentId.next();
                                            for (int i = 0; i < idArray.length; i++) {
                                                if (tableNo == Integer.parseInt(idArray[i])) {
                                                    getTreatmentIdArray[ArrayIndex] = helperT.getTreatmentId();
                                                    ArrayIndex++;
                                                }
                                            }
                                            tableNo++;
                                        }

                                        ADT.Iterator<Treatment> itr = driver.treatmentSet.iterator();
                                        while (itr.hasNext()) {
                                            Treatment t = (Treatment) itr.next();
                                            for (int i = 0; i < idArray.length; i++) {
                                                if (t.getTreatmentId().equals(getTreatmentIdArray[i])) {
                                                    treatmentSet3.add(t);
                                                    found = true;
                                                }
                                            }
                                        }
                                        payment.setTreatmentSet(treatmentSet3);
                                        driver.paymentList.replace(Oldpayment, payment);
                                    }
                                }

                            } while (treatmentIdUsed.isEmpty() || !found || isRepeat);
                            break;
                        default:
                    }
                } while (selectType != 7);
            } else {
                System.out.println("\nNOT FOUND");
            }
        } while (idSearch != -1 && selectType != 7);

    }

    public double calculateTotalAmount(SetInterface<Treatment> treatmentSet) {
        double totalPrice = 0;
        Iterator<Treatment> treatment = treatmentSet.iterator();
        while (treatment.hasNext()) {
            Treatment treatmentDetail = treatment.next();
            Iterator<Medicine> medicine = treatmentDetail.getMedicineList().iterator();
            while (medicine.hasNext()) {
                Medicine medicineDetail = medicine.next();
                totalPrice += medicineDetail.getMedicinePrice();
            }
            totalPrice += treatmentDetail.getTreatmentPrice();

        }

        return totalPrice;
    }

    public String getTreatmentIdUsed(SetInterface<Treatment> treatmentSet) {
        String treatmentIdUsed = "";
        Iterator<Treatment> treatmentList = treatmentSet.iterator();
        for (int i = 0; i < treatmentSet.getSize(); i++) {
            Treatment treatment = treatmentList.next();
            if (i == treatmentSet.getSize() - 1) {
                treatmentIdUsed += treatment.getTreatmentId();
            } else {
                treatmentIdUsed += treatment.getTreatmentId() + ",";
            }

        }
        return treatmentIdUsed;
    }

    public void tableFormat() {
        System.out.println("\n=====================================================================================================================================");
        System.out.println(String.format("ID\t%-30s %-20s %-20s %-20s %-20s %-20s", "NAME", "IC", "PAYMENT_METHOD", "TOTAL_AMOUNT", "TREATMENT_ID_USED", "PAYMENT_DATE"));
        System.out.println("=====================================================================================================================================");
    }

    public void tableFormatWithNo() {
        System.out.println("\n=========================================================================================================================================================");
        System.out.println(String.format("NO\t%-7s\t\t%-30s %-20s %-20s %-20s %-20s %-20s", "ID", "NAME", "IC", "PAYMENT_METHOD", "TOTAL_AMOUNT", "TREATMENT_ID_USED", "PAYMENT_DATE"));
        System.out.println("=========================================================================================================================================================");
    }

    @Override
    public String toString() {
        String print = String.format("%-20s %-20s %-20s %-20s %-20s", paymentId, patient.getName(), paymentMethod, totalAmount, paymentDate);
        return print;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
