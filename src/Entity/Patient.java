/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import ADT.ArraySet;
import ADT.HashedDictionary;
import ADT.SetInterface;
import java.util.Iterator;
import assets.ConsoleColors;
import client.driver;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class Patient extends Person {

    private static int id = 1001;
    private String patientId;

    Scanner sc = new Scanner(System.in);

    public Patient() {
        super("", "", 0, "");
    }

    public Patient(String name, String IC, int age, String phoneNum) {
        super(name, IC, age, phoneNum);
        this.patientId = addPatientId();
    }

    private String addPatientId() {
        String idIncrement = "P" + id++;
        return idIncrement;
    }

    public int getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void patientMenu() {

        int selectMenu;
        do {
            selectMenu = 0;
            while (true) {
                System.out.println("\nPATIENT MODULE");
                System.out.println("1. ADD PATIENT");
                System.out.println("2. DISPLAY ALL PATIENT DETAIL");
                System.out.println("3. UPDATE PATIENT DETAIL");
                System.out.println("4. DELETE PATIENT");
                System.out.println("5. SEARCH PATIENT");
                System.out.println("6. CLEAR ALL PATIENT DATA");
                System.out.println("7. EXIT BACK TO MAIN MENU");
                System.out.print("\nEnter number (1-7) : ");

                try {
                    selectMenu = sc.nextInt();
                    if (selectMenu > 0 && selectMenu < 8) {
                        sc.nextLine();
                        break;
                    }

                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 7.<ERROR>" + ConsoleColors.RESET);
                } catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                }
                sc.nextLine();
            }

            switch (selectMenu) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    displayPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    searchPatient();
                    break;
                case 6:
                    clearPatient();
                    break;
                default:
            }
        } while (selectMenu != 7);
    }

    public void addPatient() {
        String name = "", ic = "", phoneNum = "";
        int age = 0;

        System.out.println("\nNEW PATIENT DETAIL");

        while (true) {
            System.out.print("Enter patient name : ");
            name = sc.nextLine();
            if (Pattern.matches("[a-zA-Z][a-zA-Z ]{2,}", name)) {
                break;
            }
            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter full name with character only.<ERROR>" + ConsoleColors.RESET);
        }

        boolean isIcDuplicate = false;
        while (true) {
            System.out.print("Enter patient ic exp:(020916-01-0025): ");
            ic = sc.nextLine();
            if (Pattern.matches("[0-9]{6}-[0-9]{2}-[0-9]{4}", ic)) {
                do {
                    if (!isIcDuplicate(ic)) {
                        isIcDuplicate = true;
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Error IC duplicate with others. Please try again.<ERROR>" + ConsoleColors.RESET);
                        break;
                    }
                } while (!isIcDuplicate(ic));
                if (!isIcDuplicate) {
                    break;
                }
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter correct IC NO format (XXXXXX-XX-XXXX).<ERROR>" + ConsoleColors.RESET);
            }
        }

        while (true) {
            System.out.print("Enter patient age : ");
            try {
                age = sc.nextInt();
                if (age >= 0 && age < 130) {
                    sc.nextLine();
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 0 to 130.<ERROR>" + ConsoleColors.RESET);
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
            }
            sc.nextLine();
        }

        while (true) {
            System.out.print("Enter patient phone number exp:(011-2222333): ");
            phoneNum = sc.nextLine();
            if (Pattern.matches("[0-9]{3}-[0-9]{7,}", phoneNum)) {
                break;
            }
            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter correct phone no format (XXX-XXXXXXX)<ERROR>" + ConsoleColors.RESET);
        }

        Patient patient = new Patient(name, ic, age, phoneNum);

        driver.patientList.enqueue(patient);

        tableFormat();

        System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s", patient.getPatientId(), name, ic, age, phoneNum));
        System.out.println(
                "\n" + ConsoleColors.GREEN + "New Patient Added Successfully." + ConsoleColors.RESET);
    }

    public void displayPatient() {
        int selection;
        while (true) {
            System.out.println("\nDISPLAY ALL PATIENT");
            System.out.println("1. Ascending Order");
            System.out.println("2. Descending Order");
            System.out.print("\nEnter number : ");
            try {
                selection = sc.nextInt();
                if (selection == 1 || selection == 2) {
                    sc.nextLine();
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number 1 or 2.<ERROR>" + ConsoleColors.RESET);
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
            }
            sc.nextLine();
        }
        tableFormatWithNo();
        if (selection == 1) {
            driver.patientList.displayLinkedQueue();
        } else {
            driver.patientList.displayReverseLinkedQueue();
        }

    }

    public void updatePatient() {
        String name, ic, phoneNum, idSearch, continueUpdate = "";
        int age = 0;
        int selectMethod;
        int selectNo = 0;
        int selectType = 0;
//        do {
        while (true) {
            System.out.println("\nUpdate Method");
            System.out.println("1. Select table row to update");
            System.out.println("2. Enter patient id to update");
            System.out.println("3. Exit Update Patient Module");
            System.out.print("\nPlease select an update method : ");
            try {
                selectMethod = sc.nextInt();
                if (selectMethod == 1 || selectMethod == 2 || selectMethod == 3) {
                    sc.nextLine();
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 3.<ERROR>" + ConsoleColors.RESET);
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
            }
        }

        if (selectMethod != 3) {
            Patient patient;
            if (selectMethod == 1) {
                tableFormatWithNo();
                driver.patientList.displayLinkedQueue();
                while (true) {
                    System.out.print("\nENTER NO(1 to " + driver.patientList.size() + ") TO UPDATE : ");
                    try {
                        selectNo = sc.nextInt();
                        if (selectNo > 0 && selectNo <= driver.patientList.size()) {
                            sc.nextLine();
                            break;
                        }
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to " + driver.patientList.size() + ".<ERROR>" + ConsoleColors.RESET);
                    } catch (InputMismatchException e) {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                    }
                    sc.nextLine();
                }
                patient = driver.patientList.searchByNo(selectNo);

            } else {
                while (true) {
                    System.out.print("\nENTER PATIENT ID TO UPDATE : ");
                    idSearch = sc.nextLine();
                    if (idSearch.startsWith("P")) {
                        break;
                    }
                    System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>Please enter patient id by format PXXXX.<ERROR>" + ConsoleColors.RESET);
                }

                patient = driver.patientList.searchById(idSearch);
            }

            if (patient != null) {
                do {
                    while (true) {
                        tableFormat();
                        System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s", patient.getPatientId(), patient.getName(), patient.getIC(), patient.getAge(), patient.getPhoneNum()));

                        System.out.println("\nSELECT TYPE FOR UPDATE PATIENT DETAIL");
                        System.out.println("1. NAME");
                        System.out.println("2. IC");
                        System.out.println("3. AGE");
                        System.out.println("4. PHONE NUMBER");
                        System.out.println("5. Exit Update Patient Module");

                        System.out.print("ENTER NO(1 to 5)  : ");
                        try {
                            selectType = sc.nextInt();
                            if (selectType > 0 && selectType < 6) {
                                sc.nextLine();
                                break;
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 5.<ERROR>" + ConsoleColors.RESET);
                        } catch (InputMismatchException e) {
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                        }
                        sc.nextLine();
                    }

                    Patient oldPatient = patient;
                    boolean isUpdated = false;
                    switch (selectType) {
                        case 1:
                            name = "";
                            while (true) {
                                System.out.print("Update patient name : ");
                                name = sc.nextLine();
                                if (Pattern.matches("[a-zA-Z][a-zA-Z ]{2,}", name)) {
                                    break;
                                }
                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter full name with character only.<ERROR>" + ConsoleColors.RESET);
                            }
                            patient.setName(name);
                            if (selectMethod == 1) {
                                isUpdated = driver.patientList.replaceNodeByNo(selectNo, patient);
                            } else {
                                isUpdated = driver.patientList.replaceNodeById(oldPatient, patient);
                            }
                            break;
                        case 2:
                            ic = "";
                            boolean isIcDuplicate = false;
                            while (true) {
                                System.out.print("Update patient ic exp:(020916-01-0025): ");
                                ic = sc.nextLine();
                                if (Pattern.matches("[0-9]{6}-[0-9]{2}-[0-9]{4}", ic)) {
                                    do {
                                        if (!isIcDuplicate(ic)) {
                                            isIcDuplicate = true;
                                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Error IC duplicate with others. Please try again.<ERROR>" + ConsoleColors.RESET);
                                            break;
                                        }
                                    } while (!isIcDuplicate(ic));
                                    if (!isIcDuplicate) {
                                        break;
                                    }
                                } else {
                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter correct IC NO format (XXXXXX-XX-XXXX).<ERROR>" + ConsoleColors.RESET);
                                }
                            }
                            patient.setIC(ic);
                            if (selectMethod == 1) {
                                driver.patientList = driver.patientList.replaceQueueByNo(selectNo, patient);
                                isUpdated = true;
                            } else {
                                driver.patientList.replaceQueueById(oldPatient, patient);
                                isUpdated = true;
                            }
                            break;
                        case 3:
                            while (true) {
                                System.out.print("Update patient age : ");
                                try {
                                    age = sc.nextInt();
                                    if (age >= 0 && age < 130) {
                                        sc.nextLine();
                                        break;
                                    }
                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 0 to 130.<ERROR>" + ConsoleColors.RESET);
                                } catch (InputMismatchException e) {
                                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                                }
                                sc.nextLine();
                            }
                            patient.setAge(age);
                            if (selectMethod == 1) {
                                isUpdated = driver.patientList.replaceNodeByNo(selectNo, patient);
                            } else {
                                isUpdated = driver.patientList.replaceNodeById(oldPatient, patient);
                            }
                            break;
                        case 4:
                            phoneNum = "";
                            while (true) {
                                System.out.print("Enter patient phone number exp:(011-2222333): ");
                                phoneNum = sc.nextLine();
                                if (Pattern.matches("[0-9]{3}-[0-9]{7,}", phoneNum)) {
                                    break;
                                }
                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter correct phone no format (XXX-XXXXXXX)<ERROR>" + ConsoleColors.RESET);
                            }
                            patient.setPhoneNum(phoneNum);
                            if (selectMethod == 1) {
                                isUpdated = driver.patientList.replaceNodeByNo(selectNo, patient);
                            } else {
                                isUpdated = driver.patientList.replaceNodeById(oldPatient, patient);
                            }
                            break;
                        default:
                    }

                    if (isUpdated) {
                        System.out.println("\n" + ConsoleColors.GREEN + "Patient Updated Successfully." + ConsoleColors.RESET);
//                        System.out.println("\nUpdated row : ");
//                        tableFormat();
//                        System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s", patient.getPatientId(), patient.getName(), patient.getIC(), patient.getAge(), patient.getPhoneNum()));
                    }
                } while (selectType != 5);
            } else {
                System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>RECORD NOT FOUND<ERROR>" + ConsoleColors.RESET);
            }

//                if (selectType != 5) {
//                    continueUpdate = "";
//                    while (true) {
//                        System.out.print("\nContinue to update? [Y = Yes , N = No] : ");
//                        continueUpdate = sc.nextLine();
//                        if (continueUpdate.equals("Y") || continueUpdate.equals("y") || continueUpdate.equals("N") || continueUpdate.equals("n")) {
//                            break;
//                        }
//                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);
//
//                    }
//                }
//            } else {
//                continueUpdate = "N";
//            }
//
//        } while (continueUpdate.equals("Y") || continueUpdate.equals("y"));
        }
    }

    public void deletePatient() {
        int start = 0, end = 0;
        int selectNo = 0, selectMethod;
        String confirmDelete, continueDelete, idSearch;
        do {
            while (true) {
                System.out.println("\nDelete Method");
                System.out.println("1. Select table row to delete");
                System.out.println("2. Enter patient id to delete");
                System.out.println("3. Delete by number range");
                System.out.println("4. Exit Delete Patient Module");
                System.out.print("\nPlease select an delete method : ");
                try {
                    selectMethod = sc.nextInt();
                    if (selectMethod == 1 || selectMethod == 2 || selectMethod == 3 || selectMethod == 4) {
                        sc.nextLine();
                        break;
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 4.<ERROR>" + ConsoleColors.RESET);
                } catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                }
            }
            if (selectMethod != 4) {
                Patient patient = null;
                Patient[] patientList = null;
                if (selectMethod == 1) {
                    tableFormatWithNo();
                    driver.patientList.displayLinkedQueue();
                    while (true) {
                        System.out.print("\nENTER NO(1 to " + driver.patientList.size() + ") TO DELETE : ");
                        try {
                            selectNo = sc.nextInt();
                            if (selectNo > 0 && selectNo <= driver.patientList.size()) {
                                sc.nextLine();
                                break;
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to " + driver.patientList.size() + ".<ERROR>" + ConsoleColors.RESET);
                        } catch (InputMismatchException e) {
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                        }
                        sc.nextLine();
                    }
                    patient = driver.patientList.searchByNo(selectNo);
                } else if (selectMethod == 2) {
                    while (true) {
                        System.out.print("\nENTER PATIENT ID TO DELETE : ");
                        idSearch = sc.nextLine();
                        if (idSearch.startsWith("P")) {
                            break;
                        }
                        System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>Please enter patient id by format PXXXX.<ERROR>" + ConsoleColors.RESET);
                    }
                    patient = driver.patientList.searchById(idSearch);
                } else {
                    String noRange = "";
                    tableFormatWithNo();
                    driver.patientList.displayLinkedQueue();
                    System.out.print("\nENTER TABLE NO RANGE TO DELETE (EXP: 1-10) : ");
                    noRange = sc.nextLine();
                    start = 0;
                    end = 0;
                    for (int i = 0; i < noRange.length(); i++) {
                        if (noRange.charAt(i) == '-') {
                            start = Integer.parseInt(noRange.substring(0, i));
                            end = Integer.parseInt(noRange.substring(i + 1, noRange.length()));
                            break;
                        }
                    }
                    int size = (end - start) + 1;
                    patientList = new Patient[size];
                    int index = 0;
                    for (int i = start; i <= end; i++) {
                        patientList[index] = driver.patientList.searchByNo(i);
                        index++;
                    }

                }

                if (selectMethod == 1 || selectMethod == 2) {
                    if (patient != null) {
                        tableFormat();
                        System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s", patient.getPatientId(), patient.getName(), patient.getIC(), patient.getAge(), patient.getPhoneNum()));

                        confirmDelete = "";
                        while (true) {
                            System.out.print("\nConfirm to delete? [Y = Yes , N = No] : ");
                            confirmDelete = sc.nextLine();
                            if (confirmDelete.equals("Y") || confirmDelete.equals("y") || confirmDelete.equals("N") || confirmDelete.equals("n")) {
                                break;
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);

                        }
                        if (confirmDelete.equals("y") || confirmDelete.equals("Y")) {
                            if (selectMethod == 1) {
                                driver.patientList.removeByNo(selectNo);
                            } else {
                                driver.patientList.removeById(patient);
                            }

                            System.out.println("\n" + ConsoleColors.GREEN + "Patient Deleted Successfully." + ConsoleColors.RESET);

                        } else {
                            System.out.println("\nDELETE CANCEL.");
                        }

                    } else {
                        System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>RECORD NOT FOUND<ERROR>" + ConsoleColors.RESET);
                    }
                } else {
                    if (patientList != null) {
                        tableFormat();
                        for (int i = 0; i < patientList.length; i++) {
                            Patient patient1 = patientList[i];
                            System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s", patient1.getPatientId(), patient1.getName(), patient1.getIC(), patient1.getAge(), patient1.getPhoneNum()));
                        }
                        confirmDelete = "";
                        while (true) {
                            System.out.print("\nConfirm to delete? [Y = Yes , N = No] : ");
                            confirmDelete = sc.nextLine();
                            if (confirmDelete.equals("Y") || confirmDelete.equals("y") || confirmDelete.equals("N") || confirmDelete.equals("n")) {
                                break;
                            }
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);

                        }
                        if (confirmDelete.equals("y") || confirmDelete.equals("Y")) {
                            driver.patientList.removeByNoRange(start, end);

                            System.out.println("\n" + ConsoleColors.GREEN + "Patient Deleted Successfully." + ConsoleColors.RESET);

                        } else {
                            System.out.println("\nDELETE CANCEL.");
                        }
                    } else {
                        System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>RECORD NOT FOUND<ERROR>" + ConsoleColors.RESET);
                    }
                }

                continueDelete = "";
                while (true) {
                    System.out.print("\nContinue to delete? [Y = Yes , N = No] : ");
                    continueDelete = sc.nextLine();
                    if (continueDelete.equals("Y") || continueDelete.equals("y") || continueDelete.equals("N") || continueDelete.equals("n")) {
                        break;
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);

                }
            } else {
                continueDelete = "N";
            }

        } while (continueDelete.equals("Y") || continueDelete.equals("y"));
    }

    public void searchPatient() { //cannot exit
        String idSearch = "";
        do {
            while (true) {
                System.out.println("\nSEARCH PATIENT BY ID, Exit enter (exit)");
                System.out.print("Enter patient id (exp: P1001) : ");
                idSearch = sc.nextLine();
                if (idSearch.startsWith("P") || idSearch.toLowerCase().equals("exit")) {
                    break;
                }
                System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>Please enter patient id by format PXXXX.<ERROR>" + ConsoleColors.RESET);
            }

            Patient patient = driver.patientList.searchById(idSearch);
            if (patient != null) {
                tableFormat();
                System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s", patient.getPatientId(), patient.getName(), patient.getIC(), patient.getAge(), patient.getPhoneNum()));
            } else if ("exit".equals(idSearch.toLowerCase())) {
                System.out.println("\nExit Search Program");
            } else {
                System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>RECORD NOT FOUND<ERROR>" + ConsoleColors.RESET);
            }

        } while (!"exit".equals(idSearch.toLowerCase()));
    }

    public void clearPatient() {
        String confirmDeleteAll = "";
        while (true) {
            System.out.print("\nConfirm to clear all patient data? [Y = Yes , N = No] : ");
            confirmDeleteAll = sc.nextLine();
            if (confirmDeleteAll.equals("Y") || confirmDeleteAll.equals("y") || confirmDeleteAll.equals("N") || confirmDeleteAll.equals("n")) {
                break;
            }
            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);
        }

        if (confirmDeleteAll.toLowerCase().equals("y")) {
            driver.patientList.clear();
            if (driver.patientList.isEmpty()) {
                System.out.println("\n" + ConsoleColors.GREEN + "CLEAR ALL PATIENT DATA SUCCESSFULLY." + ConsoleColors.RESET);
            }
        } else {
            System.out.println("\nCLEAR CANCEL.");
        }
    }

    public boolean isIcDuplicate(String inputIc) {
        Iterator<Patient> iterator = driver.patientList.getIterator();

        while (iterator.hasNext()) {
            if (inputIc.equals(iterator.next().getIC())) {
                return false;
            }
        }
        return true;
    }

    public void tableFormat() {
        System.out.println("\n==============================================================================================================================");
        System.out.println(String.format("ID\t%-30s %-20s %-20s %-20s", "NAME", "IC", "AGE", "PHONE NUMBER"));
        System.out.println("==============================================================================================================================");
    }

    public void tableFormatWithNo() {
        System.out.println("\n=========================================================================================================================================================");
        System.out.println(String.format("NO\t%-7s\t\t%-30s %-20s %-20s %-20s", "ID", "NAME", "IC", "AGE", "PHONE NUMBER"));
        System.out.println("=========================================================================================================================================================");
    }

    @Override
    public String toString() {
        return String.format("%-7s\t\t%-30s %-20s %-20s %-20s", patientId, getName(), getIC(), getAge(), getPhoneNum());
    }

}
