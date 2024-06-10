/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import ADT.HashedDictionary;
import ADT.Iterator;
import client.driver;
import java.util.InputMismatchException;
import java.util.Scanner;
import assets.ConsoleColors;
import ADT.SortedLinkedList;
import ADT.SortedListInterface;

/**
 *
 * @author star
 */
public class Treatment {

    private static int id = 1001;
    private String treatmentId;
    private String treatmentName;
    private String treatmentDesc;
    private String treatmentType;
    private double treatmentPrice;
    private SortedListInterface<Medicine> medicineList = new SortedLinkedList<Medicine>();

    Scanner sc = new Scanner(System.in);

    public Treatment(String treatmentId, String treatmentName, String treatmentDesc, String treatmentType, double treatmentPrice) {
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.treatmentDesc = treatmentDesc;
        this.treatmentType = treatmentType;
        this.treatmentPrice = treatmentPrice;
    }

    public Treatment(String treatmentName, String treatmentDesc, String treatmentType, double treatmentPrice, SortedListInterface<Medicine> medicineList) {
        this.treatmentId = addTreatmentId();
        this.treatmentName = treatmentName;
        this.treatmentDesc = treatmentDesc;
        this.treatmentType = treatmentType;
        this.treatmentPrice = treatmentPrice;
        this.medicineList = medicineList;
    }

    public Treatment(String treatmentName, String treatmentDesc, String treatmentType, double treatmentPrice) {
        this.treatmentId = addTreatmentId();
        this.treatmentName = treatmentName;
        this.treatmentDesc = treatmentDesc;
        this.treatmentType = treatmentType;
        this.treatmentPrice = treatmentPrice;
    }

    public Treatment() {
    }

    private String addTreatmentId() {
        String idIncrement = "T" + id++;
        return idIncrement;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getTreatmentDesc() {
        return treatmentDesc;
    }

    public void setTreatmentDesc(String treatmentDesc) {
        this.treatmentDesc = treatmentDesc;
    }

    public double getTreatmentPrice() {
        return treatmentPrice;
    }

    public void setTreatmentPrice(double treatmentPrice) {
        this.treatmentPrice = treatmentPrice;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Treatment.id = id;
    }

    public SortedListInterface<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(SortedListInterface<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    
    
    public void addTreatment() {
        SortedListInterface<Medicine> medicineList2 = new SortedLinkedList<Medicine>();
        String type = "", name = "", desc = "", medicineIdUsed = "";
        double price = 0;
        String[] idArray = null;
        char firstChar = '0';
        System.out.println("\nNEW TREATMENT DETAIL");
        do {
            System.out.print("Enter treatment name : ");
            name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment name cannot be empty!<ERROR>" + ConsoleColors.RESET);
            } else {
                firstChar = name.charAt(0);
                if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment name should start at alphabet!<ERROR>" + ConsoleColors.RESET);
                }
            }
        } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || name.isEmpty());

        firstChar = '0';
        do {
            System.out.print("Enter treatment description : ");
            desc = sc.nextLine();
            if (desc.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment description cannot be empty!<ERROR>" + ConsoleColors.RESET);
            } else {
                firstChar = desc.charAt(0);
                if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment description should start at alphabet!<ERROR>" + ConsoleColors.RESET);
                }
            }
        } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || desc.isEmpty());

        firstChar = '0';
        do {
            System.out.print("Enter treatment type : ");
            type = sc.nextLine();
            if (type.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment type cannot be empty!<ERROR>" + ConsoleColors.RESET);
            } else {
                firstChar = type.charAt(0);
                if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment type should start at alphabet!<ERROR>" + ConsoleColors.RESET);
                }
            }
        } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || type.isEmpty());

        boolean isDouble;
        do {
            System.out.print("Enter treatment price : ");
            if (sc.hasNextDouble()) {
                price = sc.nextDouble();
                isDouble = true;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter decimal value!<ERROR>" + ConsoleColors.RESET);
                isDouble = false;
                sc.next();
            }
        } while (!isDouble);
        sc.nextLine();

        boolean found = false;
        boolean isRepeat = false;
        do {
             found = false;
             isRepeat = false;
             firstChar = '0';
            System.out.println(driver.medicine.toString());
            System.out.print("Select the medicine that are used (use , to separate) : ");
            medicineIdUsed = sc.nextLine();
            if (medicineIdUsed.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id used cannot be empty!<ERROR>" + ConsoleColors.RESET);
            } else {
                idArray = medicineIdUsed.split(",");
                HashedDictionary map = new HashedDictionary<>();
                for (String value : idArray) {
                    
                    firstChar = value.charAt(0);
                    if (firstChar < '0' || firstChar > '9') {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id used should type in 1,2,3 !<ERROR>" + ConsoleColors.RESET);
                        break;
                    }
                    
                    if (map.contains(value)) {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id repeated!<ERROR>" + ConsoleColors.RESET);
                        isRepeat = true;
                        break;
                    }
                    map.add(value, value);
                }
                if (!(firstChar < '0' || firstChar > '9')) {
                    medicineList2.clear();

                    Iterator<Medicine> itr = driver.medicine.iterator();
                    while (itr.hasNext()) {

                        Medicine m = (Medicine) itr.next();
                        for (int i = 0; i < idArray.length; i++) {
                            if (m.getMedicineId() == Integer.parseInt(idArray[i])) {
                                medicineList2.add(m);
                                found = true;
                            }
                        }
                    }

                }
            }

        } while (medicineIdUsed.isEmpty() || !found || isRepeat);

        Treatment treatment = new Treatment(name, desc, type, price, medicineList2);
        driver.treatmentSet.add(treatment);
        medicineList=treatment.getMedicineList();
        tableFormat();
        System.out.println(String.format("%s\t%-20s %-40s %-15s %-15s %-20s", treatment.treatmentId, treatment.treatmentName, treatment.treatmentDesc, treatment.treatmentType, treatment.treatmentPrice, toStringMedicineId()));
        System.out.println("\nNew Treatment Added Successfully.");
    }

    public void displayTreatment() {

        System.out.println("\n\t\t\t\tDISPLAY ALL TREATMENT (TOTAL TREATMENT : " + driver.treatmentSet.getSize() + ")");
        Iterator<Treatment> itr = driver.treatmentSet.iterator();

        tableFormat();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    public void searchTreatment() {
        char firstChar = '0';
        String idSearch = "";

        do {
            boolean found = false;
            do {
                System.out.println("\nSEARCH TREATMENT BY ID, Exit enter (exit)");
                System.out.print("Enter treatment id exp(T1001) : ");
                idSearch = sc.nextLine();
                if (idSearch.isEmpty()) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment id cannot be empty!<ERROR>" + ConsoleColors.RESET);
                } else {
                    firstChar = idSearch.charAt(0);
                    if (!(firstChar == 'T') && !"exit".equals(idSearch.toLowerCase())) {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment id should like \"T1001\"!<ERROR>" + ConsoleColors.RESET);
                    }
                }

                Iterator<Treatment> itr = driver.treatmentSet.iterator();

                while (itr.hasNext()) {
                    Treatment t = (Treatment) itr.next();
                    if (t.getTreatmentId().equals(idSearch)) {
                        searchTableFormat();
                        System.out.println(t.toStringSearch());
                        found = true;
                    }
                }

                if (!found && !idSearch.isEmpty() && firstChar == 'T') {
                    System.out.println("Not Found");
                }

            } while (idSearch.isEmpty() || !(firstChar == 'T') && !"exit".equals(idSearch.toLowerCase()));
        } while (!"exit".equals(idSearch.toLowerCase()));
    }

    public void updateTreatment() {
        String type = "", name = "", desc = "", medicineIdUsed = "";
        double price = 0;
        String[] idArray = null;
        char firstChar = '0';
        String idSearch = "";
        int selectType = 0;
        boolean found;
        do {
            do {
                found = false;
                System.out.println("\nSEARCH TREATMENT BY ID, Exit enter (exit)");
                System.out.print("Enter treatment id exp(T1001) : ");
                idSearch = sc.nextLine();
                if (idSearch.isEmpty()) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment id cannot be empty!<ERROR>" + ConsoleColors.RESET);
                } else {
                    firstChar = idSearch.charAt(0);
                    if (!(firstChar == 'T') && !"exit".equals(idSearch.toLowerCase())) {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment id should like \"T1001\"!<ERROR>" + ConsoleColors.RESET);
                    }
                }

                Iterator<Treatment> itr = driver.treatmentSet.iterator();

                while (itr.hasNext()) {
                    Treatment t = (Treatment) itr.next();
                    if (t.getTreatmentId().equals(idSearch)) {
                        do {

                            searchTableFormat();
                            System.out.println(t.toStringSearch());
                            System.out.println("\nSELECT TYPE FOR UPDATE TREATMENT DETAIL");
                            System.out.println("1. NAME");
                            System.out.println("2. DESCRIPTION");
                            System.out.println("3. TYPE");
                            System.out.println("4. PRICE");
                            System.out.println("5. MEDICINE ID USED");
                            System.out.println("6. BACK TO CONTINUE SEARCH TREATMENT");
                            System.out.println("7. EXIT UPDATE PROGRAM");

                            System.out.print("Select type of detail for update 1-7 only : ");
                            selectType = sc.nextInt();
                            sc.nextLine();

                            switch (selectType) {
                                case 1:
                                    do {
                                        System.out.print("Update treatment name : ");
                                        name = sc.nextLine();
                                        if (name.isEmpty()) {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment name cannot be empty!<ERROR>" + ConsoleColors.RESET);
                                        } else {
                                            firstChar = name.charAt(0);
                                            if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment name should start at alphabet!<ERROR>" + ConsoleColors.RESET);
                                            }
                                        }
                                    } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || name.isEmpty());
                                    t.setTreatmentName(name);
                                    break;
                                case 2:
                                    firstChar = '0';
                                    do {
                                        System.out.print("Update treatment description : ");
                                        desc = sc.nextLine();
                                        if (desc.isEmpty()) {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment description cannot be empty!<ERROR>" + ConsoleColors.RESET);
                                        } else {
                                            firstChar = desc.charAt(0);
                                            if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment description should start at alphabet!<ERROR>" + ConsoleColors.RESET);
                                            }
                                        }
                                    } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || desc.isEmpty());
                                    t.setTreatmentDesc(desc);
                                    break;
                                case 3:
                                    firstChar = '0';
                                    do {
                                        System.out.print("Update treatment type : ");
                                        type = sc.nextLine();
                                        if (type.isEmpty()) {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment type cannot be empty!<ERROR>" + ConsoleColors.RESET);
                                        } else {
                                            firstChar = type.charAt(0);
                                            if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment type should start at alphabet!<ERROR>" + ConsoleColors.RESET);
                                            }
                                        }
                                    } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || type.isEmpty());
                                    t.setTreatmentType(type);
                                    break;
                                case 4:
                                    boolean isDouble;
                                    do {
                                        System.out.print("Update treatment price : ");
                                        if (sc.hasNextDouble()) {
                                            price = sc.nextDouble();
                                            isDouble = true;
                                        } else {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter decimal value!<ERROR>" + ConsoleColors.RESET);
                                            isDouble = false;
                                            sc.next();
                                        }
                                    } while (!isDouble);
                                    sc.nextLine();
                                    t.setTreatmentPrice(price);
                                    break;
                                case 5:
                                    SortedListInterface<Medicine> medicineList2 = new SortedLinkedList<Medicine>();
                                    boolean isRepeat = false;
                                    firstChar = '0';
                                    do {
                                        System.out.println(driver.medicine.toString());
                                        System.out.print("Select the medicine that are used (use , to separate) : ");
                                        medicineIdUsed = sc.nextLine();
                                        if (medicineIdUsed.isEmpty()) {
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id used cannot be empty!<ERROR>" + ConsoleColors.RESET);
                                        } else {
                                            idArray = medicineIdUsed.split(",");
                                            HashedDictionary map = new HashedDictionary<>();
                                            for (String value : idArray) {
                                                
                                                firstChar = value.charAt(0);
                                                if (firstChar < '0' || firstChar > '9') {
                                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id used should type in 1,2,3 !<ERROR>" + ConsoleColors.RESET);
                                                    break;
                                                }
                                                
                                                if (map.contains(value)) {
                                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine id repeated!<ERROR>" + ConsoleColors.RESET);
                                                    isRepeat = true;
                                                    break;
                                                }
                                                map.add(value, value);
                                            }
                                            if (!(firstChar < '0' || firstChar > '9')) {
                                                medicineList2.clear();
                                                Iterator<Medicine> medicineItr = driver.medicine.iterator();
                                                while (medicineItr.hasNext()) {
                                                    Medicine m = (Medicine) medicineItr.next();
                                                    for (int i = 0; i < idArray.length; i++) {
                                                        if (m.getMedicineId() == Integer.parseInt(idArray[i])) {
                                                            medicineList2.add(m);
                                                            found = true;
                                                        }
                                                    }
                                                }
                                                t.setMedicineList(medicineList2);
                                            }
                                        }
                                    } while (medicineIdUsed.isEmpty() || !found || isRepeat);
                                    break;
                                default:
                            }
                        } while (selectType != 6 && selectType != 7);
                        found = true;
                    }
                }

                if (!found && !idSearch.isEmpty() && firstChar == 'T') {
                    System.out.println("Not Found");
                }

            } while (idSearch.isEmpty() || !(firstChar == 'T') && !"exit".equals(idSearch.toLowerCase()) && selectType != 7);

        } while (!"exit".equals(idSearch.toLowerCase()) && selectType != 7);
    }

    public void deleteTreatment() {
        String idSearch = "";
        char firstChar = '0';

        do {
            boolean found = false;
            do {
                System.out.println("\nSEARCH TREATMENT BY ID, Exit enter (exit)");
                System.out.print("Enter treatment id exp(T1001) : ");
                idSearch = sc.nextLine();
                if (idSearch.isEmpty()) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment id cannot be empty!<ERROR>" + ConsoleColors.RESET);
                } else {
                    firstChar = idSearch.charAt(0);
                    if (!(firstChar == 'T') && !"exit".equals(idSearch.toLowerCase())) {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The treatment id should like \"T1001\"!<ERROR>" + ConsoleColors.RESET);
                    }
                }

                Iterator<Treatment> itr = driver.treatmentSet.iterator();

                while (itr.hasNext()) {
                    Treatment t = (Treatment) itr.next();
                    if (t.getTreatmentId().equals(idSearch)) {
                        searchTableFormat();
                        System.out.println(t.toStringSearch());
                        System.out.print("You sure want to delete it? (y) or (n) : ");
                        String confirm = sc.nextLine();
                        if (confirm.equals("y") || confirm.equals("Y")) {
                            driver.treatmentSet.remove(t);
                            System.out.println("\nDELETED SUCCESSFULLY");
                        } else {
                            System.out.println("\nDELETE CANCEL.");
                        }
                        found = true;
                        break;
                    }
                }

                if (!found && !idSearch.isEmpty() && firstChar == 'T') {
                    System.out.println("Not Found");
                }

            } while (idSearch.isEmpty() || !(firstChar == 'T') && !"exit".equals(idSearch.toLowerCase()));
        } while (!"exit".equals(idSearch.toLowerCase()));
    }

    public void clearTreatment() {

        System.out.print("You sure want to clear it all? (y) or (n) : ");
        String confirm = sc.nextLine();
        if (confirm.toLowerCase().equals("y")) {
            driver.treatmentSet.clear();
            if (driver.treatmentSet.isEmpty()) {
                System.out.println("\nCLEAR ALL TREATMENT DATA SUCCESSFULLY");
            }
        } else {
            System.out.println("\nCLEAR CANCEL.");
        }

    }

    public void treatmentMenu() {
        int selectMenu = 0;

        do {

            System.out.println("\nTREATMENT MODULE");
            System.out.println("1. ADD TREATMENT");
            System.out.println("2. DISPLAY ALL TREATMENT DETAIL");
            System.out.println("3. UPDATE TREATMENT DETAIL");
            System.out.println("4. DELETE TREATMENT");
            System.out.println("5. SEARCH TREATMENT");
            System.out.println("6. CLEAR ALL TREATMENT DATA");
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
                    addTreatment();
                    break;
                case 2:
                    displayTreatment();
                    break;
                case 3:
                    updateTreatment();
                    break;
                case 4:
                    deleteTreatment();
                    break;
                case 5:
                    searchTreatment();
                    break;
                case 6:
                    clearTreatment();
                    break;
                default:
            }

        } while (selectMenu != 7);

    }

    public void searchTableFormat() {
        System.out.println("\n=======================================================================================================================");
        System.out.println(String.format("%-20s %-40s %-15s %-15s %-20s", "TREATMENT_NAME", "DESCRIPTION", "TYPE", "PRICE", "MEDICINE_ID_USED"));
        System.out.println("=======================================================================================================================");
    }

    public void tableFormat() {
        System.out.println("\n=======================================================================================================================");
        System.out.println(String.format("ID\t%-20s %-40s %-15s %-15s %-20s", "TREATMENT_NAME", "DESCRIPTION", "TYPE", "PRICE", "MEDICINE_ID_USED"));
        System.out.println("=======================================================================================================================");
    }

    @Override
    public String toString() {
        String print = String.format("%s\t%-20s %-40s %-15s %-15s %-20s", treatmentId, treatmentName, treatmentDesc, treatmentType, treatmentPrice, toStringMedicineId());
        return print;
    }

    public String toStringSearch() {
        String print = String.format("%-20s %-40s %-15s %-15s %-20s", treatmentName, treatmentDesc, treatmentType, treatmentPrice, toStringMedicineId());
        return print;
    }

    public String toStringMedicineId() {
        String print = "";
        int count = 0;
       
        Iterator<Medicine> itr = medicineList.iterator();
        while (itr.hasNext()) {
              Medicine n = (Medicine) itr.next();
            if(count==0){
             print+=n.getMedicineId();
             count++;
            }else
                print+=","+n.getMedicineId();
     
        }

        return print;
    }

}
