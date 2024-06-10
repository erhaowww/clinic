/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import ADT.*;
import Entity.*;
import assets.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class driver {

    public static DictionaryInterface<String, Staff> staffList = new HashedDictionary<>();
    public static QueueInterface<Patient> patientList = new LinkedQueue<Patient>();
    public static SortedListInterface<Medicine> medicine = new SortedLinkedList<Medicine>();
    public static SetInterface<Treatment> treatmentSet = new ArraySet<>();
    public static SortedArrayListInterface paymentList = new SortedArrayList();

    Scanner sc = new Scanner(System.in);

    public void menu() {
        String id = "", password = "";
        boolean idFound = false, pass = false;

        System.out.println("\nSTAFF LOGIN\n");

        do {
            System.out.print("Enter id (exp: S1003): ");
            id = sc.nextLine();
            System.out.print("Enter password (exp: aaa): ");
            password = sc.nextLine();

            if (staffList.contains(id)) {
                String pss = staffList.getValue(id).getPassword();
                idFound = true;
                if (pss.equals(password)) {
                    pass = true;
                } else {
                    System.out.println("Password wrong.");
                }
            } else {
                System.out.println("Id not found.");
            }

        } while (!idFound || !pass);
        int selectNo;
        do {
            selectNo = 0;
            while (true) {
                System.out.println("\n      MENU");
                System.out.println("===============");
                System.out.println("1. Staff Module");
                System.out.println("2. Patient Module");
                System.out.println("3. Medicine Module");
                System.out.println("4. Treatment Module");
                System.out.println("5. Payment Module");
                System.out.println("6. Exit");

                System.out.print("\nEnter No (1 to 6) : ");
                try {
                    selectNo = sc.nextInt();
                    if (selectNo > 0 && selectNo < 7) {
                        sc.nextLine();
                        break;
                    }

                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 6.<ERROR>" + ConsoleColors.RESET);
                } catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                }
                sc.nextLine();

            }
            switch (selectNo) {
                case 1:
                    staffModule();
                    break;
                case 2:
                    patientModule();
                    break;
                case 3:
                    medicineModule();
                    break;
                case 4:
                    treatmentModule();
                    break;
                case 5:
                    paymentModule();
                    break;
                default:
            }
        } while (selectNo != 6);
    }

    public void initElement() {
//        initStaff();
//        initPatient();
//        initMedicine();
//        initTreatment();
//        initPayment();

//Staff
        Staff staff1 = new Staff("Dirk", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff2 = new Staff("Abel", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Inactive");
        Staff staff3 = new Staff("Miguel", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff4 = new Staff("Tabatha", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Inactive");
        Staff staff5 = new Staff("Zello", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff6 = new Staff("Tom", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff7 = new Staff("Sam", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Inactive");
        Staff staff8 = new Staff("Reiss", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff9 = new Staff("Carole", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Inactive");
        Staff staff10 = new Staff("Derek", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff11 = new Staff("Tom", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff12 = new Staff("Sam", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Inactive");
        Staff staff13 = new Staff("Reiss", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Active");
        Staff staff14 = new Staff("Carole", "02-1111-22-3333", 22, "011-2222-333", "aaa", "Inactive");
        staffList.add(staff1.getStaffId(), staff1);
        staffList.add(staff2.getStaffId(), staff2);
        staffList.add(staff3.getStaffId(), staff3);
        staffList.add(staff4.getStaffId(), staff4);
        staffList.add(staff5.getStaffId(), staff5);
        staffList.add(staff6.getStaffId(), staff6);
        staffList.add(staff7.getStaffId(), staff7);
        staffList.add(staff8.getStaffId(), staff8);
        staffList.add(staff9.getStaffId(), staff9);
        staffList.add(staff10.getStaffId(), staff10);
        staffList.add(staff11.getStaffId(), staff11);
        staffList.add(staff12.getStaffId(), staff12);
        staffList.add(staff13.getStaffId(), staff13);
        staffList.add(staff14.getStaffId(), staff14);
//End Staff

//Medicine
        Medicine medicine1 = new Medicine("Ativan", "For Flu", 28.00);
        Medicine medicine2 = new Medicine("Omeprazole", "For Heart System", 69.69);
        Medicine medicine3 = new Medicine("Glucose", "For Fever", 18.00);
        Medicine medicine4 = new Medicine("Antibiotic", "For Urine System", 35.00);
        Medicine medicine5 = new Medicine("Yeoh Mei Li", "For Nerve System", 830.00);
        Medicine medicine6 = new Medicine("Antiseptic", "For Muscle", 72.00);
        Medicine medicine7 = new Medicine("Capsule", "For Brain", 10.00);
        Medicine medicine8 = new Medicine("Caplet", "For Skin", 9.00);
        Medicine medicine9 = new Medicine("Vitamin", "For Growth", 59.00);
        Medicine medicine10 = new Medicine("Losartan", "For Covid", 27.00);
        Medicine medicine11 = new Medicine("Amlodipine", "For Fever", 99.00);
        medicine.add(medicine1);
        medicine.add(medicine2);
        medicine.add(medicine3);
        medicine.add(medicine4);
        medicine.add(medicine5);
        medicine.add(medicine6);
        medicine.add(medicine7);
        medicine.add(medicine8);
        medicine.add(medicine9);
        medicine.add(medicine10);
        medicine.add(medicine11);
        SortedListInterface<Medicine> medicineList = new SortedLinkedList<>();
        SortedListInterface<Medicine> medicineList2 = new SortedLinkedList<>();
        SortedListInterface<Medicine> medicineList3 = new SortedLinkedList<>();
        medicineList.add(medicine1);
        medicineList.add(medicine2);
        medicineList.add(medicine3);
        medicineList2.add(medicine4);
        medicineList2.add(medicine5);
        medicineList2.add(medicine6);
        medicineList3.add(medicine7);
        medicineList3.add(medicine8);
        medicineList3.add(medicine9);
//End Medicine

//Treatment
        Treatment treatment1 = new Treatment("Treatment1", "hand treatment", "cure", 200.00, medicineList);
        Treatment treatment2 = new Treatment("Treatment2", "hand treatment", "cure", 200.00, medicineList2);
        Treatment treatment3 = new Treatment("Treatment3", "hand treatment", "cure", 200.00, medicineList3);
        treatmentSet.add(treatment1);
        treatmentSet.add(treatment2);
        treatmentSet.add(treatment3);
        SetInterface<Treatment> treatmentSet1 = new ArraySet<>();
        SetInterface<Treatment> treatmentSet2 = new ArraySet<>();
        SetInterface<Treatment> treatmentSet3 = new ArraySet<>();
        SetInterface<Treatment> treatmentSet4 = new ArraySet<>();
        SetInterface<Treatment> treatmentSet5 = new ArraySet<>();
        treatmentSet1.add(treatment1);
        treatmentSet1.add(treatment2);
        treatmentSet2.add(treatment2);
        treatmentSet2.add(treatment3);
        treatmentSet3.add(treatment1);
        treatmentSet3.add(treatment3);
        treatmentSet4.add(treatment1);
        treatmentSet4.add(treatment2);
        treatmentSet5.add(treatment3);
        treatmentSet5.add(treatment2);
        treatmentSet5.add(treatment1);
//End Treatment

//Patient
        Patient patient1 = new Patient("Ulric Fleming", "050916-01-0025", 18, "011-12924488");
        Patient patient2 = new Patient("Wayne Bates", "010717-11-3380", 22, "012-3626118");
        Patient patient3 = new Patient("Brody Romero", "930816-03-3467", 30, "017-06326366");
        Patient patient4 = new Patient("Cyrus Jacobson", "890211-01-0341", 34, "018-03282498");
        Patient patient5 = new Patient("Emerson Gilbert", "730421-45-1246", 50, "011-3820247");
        Patient patient6 = new Patient("Serena", "040101-22-4729", 19, "019-84188674");
        Patient patient7 = new Patient("Lucy", "010512-81-0183", 22, "017-1383623");
        Patient patient8 = new Patient("Rae", "970826-05-0245", 26, "011-9562023");
        Patient patient9 = new Patient("Camilla", "950917-66-2353", 28, "012-8425052");
        Patient patient10 = new Patient("Winifred", "850308-01-5335", 38, "018-8228284");
        Patient patient11 = new Patient("Dora", "750410-51-3691", 48, "019-98826787");
        Patient patient12 = new Patient("Adele", "780501-81-9225", 45, "010-11321446");
        Patient patient13 = new Patient("Melodie", "711207-88-5347", 52, "011-66846234");
        Patient patient14 = new Patient("Basia", "000414-01-0193", 23, "010-2983153");
        Patient patient15 = new Patient("Zenaida", "931231-01-7812", 30, "010-30487443");
        patientList.enqueue(patient1);
        patientList.enqueue(patient2);
        patientList.enqueue(patient3);
        patientList.enqueue(patient4);
        patientList.enqueue(patient5);
        patientList.enqueue(patient6);
        patientList.enqueue(patient7);
        patientList.enqueue(patient8);
        patientList.enqueue(patient9);
        patientList.enqueue(patient10);
        patientList.enqueue(patient11);
        patientList.enqueue(patient12);
        patientList.enqueue(patient13);
        patientList.enqueue(patient14);
        patientList.enqueue(patient15);
//End Patient

//Payment
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Payment p = new Payment(patient1, "Cash1", 1200, dateFormat.format(date), treatmentSet1);
        Payment p2 = new Payment(patient2, "Cash2", 120000, dateFormat.format(date), treatmentSet2);
        Payment p3 = new Payment(patient3, "Cash3", 1200, dateFormat.format(date), treatmentSet3);
        Payment p4 = new Payment(patient4, "Cash4", 1200, dateFormat.format(date), treatmentSet4);
        Payment p5 = new Payment(patient5, "Cash5", 120000, dateFormat.format(date), treatmentSet5);
        Payment p6 = new Payment(patient6, "Cash6", 1200, dateFormat.format(date), treatmentSet1);
        Payment p7 = new Payment(patient7, "Cash7", 1200, dateFormat.format(date), treatmentSet2);
        Payment p8 = new Payment(patient8, "Cash8", 120000, dateFormat.format(date), treatmentSet3);
        Payment p9 = new Payment(patient9, "Cash9", 1200, dateFormat.format(date), treatmentSet4);
        paymentList.add(p);
        paymentList.add(p2);
        paymentList.add(p3);
        paymentList.add(p4);
        paymentList.add(p5);
        paymentList.add(p6);
        paymentList.add(p7);
        paymentList.add(p8);
        paymentList.add(p9);
//End Payment

    }

    public void staffModule() {
        Staff staff = new Staff();
        staff.staffMenu();
    }

    public void patientModule() {
        Patient patient = new Patient();
        patient.patientMenu();
    }

    public void medicineModule() {
        Medicine medicine = new Medicine();
        medicine.medicineMenu();
    }

    public void treatmentModule() {
        Treatment treatment = new Treatment();
        treatment.treatmentMenu();
    }

    public void paymentModule() {
        Payment p = new Payment();
        p.paymentMenu();
    }

    public static void main(String[] args) {
        System.out.println("CLINIC SERVICE");
        driver d = new driver();
        d.initElement();
        d.menu();

    }

}
