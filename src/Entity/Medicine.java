package Entity;

import java.util.*;
import java.util.InputMismatchException;

import ADT.Iterator;
import assets.ConsoleColors;
import client.driver;
import java.util.regex.Pattern;

public class Medicine implements Comparable<Medicine> {

    private static int countId = 1;
    private int medicineId;
    private String medicineName;
    private String medicineDesc;
    private double medicinePrice;

    Scanner sc = new Scanner(System.in);

    public Medicine() {

    }

    public Medicine(String medicineName, String medicineDesc, double medicinePrice) {
        this.medicineId = addMedicineId();
        this.medicineName = medicineName;
        this.medicineDesc = medicineDesc;
        this.medicinePrice = medicinePrice;
    }

    private int addMedicineId() {
        return countId++;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDesc() {
        return medicineDesc;
    }

    public void setMedicineDesc(String medicineDesc) {
        this.medicineDesc = medicineDesc;
    }

    public double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public void medicineMenu() {
        int selectMenu = 0;

        while (selectMenu != 6) {

            System.out.print("\n==============================");
            System.out.println("\nWelcome To Medicine Module");
            System.out.println("==============================");
            System.out.println("1. Add Medicine");
            System.out.println("2. Display Medicine");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Search Medicine");
            System.out.println("6. Exit");
            System.out.println("==============================");

            try {
                System.out.print("Enter Your Selection (1-6) : ");
                selectMenu = sc.nextInt();
                if (selectMenu > 0 && selectMenu < 6) {
                    sc.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
            }

            switch (selectMenu) {
                case 1:
                    addMedicine();
                    break;
                case 2:
                    displayMedicine();
                    break;
                case 3:
                    updateMedicine();
                    break;
                case 4:
                    deleteMedicine();
                    break;
                case 5:
                    searchMedicine();
                    break;
                case 6:
                    break;

            }

        }
    }

    public void addMedicine() {

        String continueAdd;
        char firstChar = '0';

        System.out.println("Medicine Id : " + (countId));

        do {
            System.out.print("Enter Medicine Name : ");
            medicineName = sc.nextLine();
            if (medicineName.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>This Section Cannot Be Blank !<ERROR>" + ConsoleColors.RESET);
            } else {
                firstChar = medicineName.charAt(0);
                if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The Medicine Name Should Start with an Alphabet!<ERROR>" + ConsoleColors.RESET);
                }
            }
        } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || medicineName.isEmpty());

        firstChar = '0';
        do {
            System.out.print("Enter Medicine description : ");
            medicineDesc = sc.nextLine();
            if (medicineDesc.isEmpty()) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please Enter The Correct Medicine Description<ERROR>" + ConsoleColors.RESET);
            } else {
                firstChar = medicineDesc.charAt(0);
                if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine description should start with an Alphabet!<ERROR>" + ConsoleColors.RESET);
                }
            }
        } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || medicineDesc.isEmpty());

        boolean isDouble;
        do {
            System.out.print("Enter Medicine price : ");
            if (sc.hasNextDouble()) {
                medicinePrice = sc.nextDouble();
                isDouble = true;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter decimal value!<ERROR>" + ConsoleColors.RESET);
                isDouble = false;
                sc.next();
            }
        } while (!isDouble);

        Medicine m = new Medicine(medicineName, medicineDesc, medicinePrice);
        driver.medicine.add(m);

        tableFormat();
        System.out.println(m);
        tableClose();
        System.out.println("\n" + ConsoleColors.GREEN + "New Medicine Added Successfully." + ConsoleColors.RESET);

        sc.nextLine();
        continueAdd = "";
        while (true) {
            System.out.print("\nContinue to add? [Y = Yes , N = No] : ");
            continueAdd = sc.nextLine();
            if (continueAdd.equals("Y") || continueAdd.equals("y") || continueAdd.equals("N") || continueAdd.equals("n")) {
                break;
            }
            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);
        }
    }

    public void displayMedicine() {
        System.out.println("\n\t\t\t\tDISPLAY ALL MEDICINE (TOTAL MEDICINE : " + driver.medicine.getNumberOfEntries() + ")");
        tableFormatWithNo();
        Iterator<Medicine> itr = driver.medicine.iterator();
        int i = 1;
        while (itr.hasNext()) {
            Medicine m = itr.next();
            System.out.println((i) + "\t" + m.toString());
            i++;
        }
        tableClose();
    }

    public void deleteMedicine() {

        int selectNo = 0, selectMethod, idSearch;

        while (true) {
            System.out.print("==============================");
            System.out.println("\nDelete Method");
            System.out.print("==============================");
            System.out.println("\n1. Enter medicine id to delete");
            System.out.println("2. Delete all medicine");
            System.out.println("3. Exit Delete Patient Module");
            System.out.println("==============================");

            System.out.print("\nPlease select an delete method (1-3) : ");
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
        String confirmDeleteAll = "";
        if (selectMethod == 1) {
            displayMedicine();
            Medicine m = null;
            System.out.print("\nEnter Medicine Id To Delete : ");
            idSearch = sc.nextInt();
            sc.nextLine();
            boolean found = false;

            Iterator<Medicine> itr = driver.medicine.iterator();
            while (itr.hasNext()) {
                m = itr.next();

                if (m.medicineId == idSearch) {
                    tableFormat();
                    System.out.println(m.toString());
                    tableClose();
                    found = true;
                    break;
                }

            }
            if (!found) {
                System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>NO MEDICINE ID FOUND<ERROR>" + ConsoleColors.RESET);
            } else {
                while (true) {
                    System.out.print("\nConfirm to delete the medicine data? [Y = Yes , N = No] : ");
                    confirmDeleteAll = sc.nextLine();
                    if (confirmDeleteAll.equals("Y") || confirmDeleteAll.equals("y") || confirmDeleteAll.equals("N") || confirmDeleteAll.equals("n")) {
                        break;
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);
                }

                if (confirmDeleteAll.toLowerCase().equals("y")) {
                    if (driver.medicine.remove(m)) {
                        System.out.println("\n" + ConsoleColors.GREEN + "Delete Successfully." + ConsoleColors.RESET);
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Delete Unsuccessfully. Please try again.<ERROR>" + ConsoleColors.RESET);
                    }

                } else {
                    System.out.println("\nCLEAR CANCEL.");
                }
            }

        }

        if (selectMethod == 2) {
            confirmDeleteAll = "";
            displayMedicine();
            while (true) {
                System.out.print("\nConfirm to clear all medicine data? [Y = Yes , N = No] : ");
                confirmDeleteAll = sc.nextLine();
                if (confirmDeleteAll.equals("Y") || confirmDeleteAll.equals("y") || confirmDeleteAll.equals("N") || confirmDeleteAll.equals("n")) {
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter \"Y\" or \"y\" if YES, else enter \"N\" or \"n\"<ERROR>" + ConsoleColors.RESET);
            }

            if (confirmDeleteAll.toLowerCase().equals("y")) {
                driver.medicine.clear();

                if (driver.medicine.isEmpty()) {
                    System.out.println("\n" + ConsoleColors.GREEN + "CLEAR ALL PATIENT DATA SUCCESSFULLY." + ConsoleColors.RESET);
                }
            } else {
                System.out.println("\nCLEAR CANCEL.");
            }
        } else {
            medicineMenu();
        }

    }

    public void updateMedicine() {
        tableFormatWithNo();
        Iterator<Medicine> itr = driver.medicine.iterator();
        int i = 1, selectNo;
        while (itr.hasNext()) {
            Medicine m = itr.next();
            System.out.println((i) + "\t" + m.toString());
            i++;
        }

        while (true) {
            System.out.print("\nENTER NO(1 to " + driver.medicine.getNumberOfEntries() + ") TO UPDATE : ");
            try {
                selectNo = sc.nextInt();
                if (selectNo > 0 && selectNo <= driver.medicine.getNumberOfEntries()) {
                    sc.nextLine();
                    break;
                }
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to " + driver.medicine.getNumberOfEntries() + ".<ERROR>" + ConsoleColors.RESET);
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
            }
            sc.nextLine();
        }

        int j = 1;
        Iterator<Medicine> itr2 = driver.medicine.iterator();
        Medicine selectedMedicine = null;
        while (itr2.hasNext()) {
            Medicine m2 = itr2.next();
            if (j == selectNo) {
                selectedMedicine = m2;
                break;
            }
            j++;
        }

        if (selectedMedicine != null) {
            tableFormat();
            System.out.println(selectedMedicine.toString());
            tableClose();

            int selectType = 0;
            while (true) {
                System.out.println("\nSELECT TYPE FOR UPDATE MEDICINE DETAIL");
                System.out.println("1. NAME");
                System.out.println("2. DESCRIPTION");
                System.out.println("3. PRICE");

                System.out.print("ENTER NO(1 to 3)  : ");
                try {
                    selectType = sc.nextInt();
                    if (selectType > 0 && selectType < 4) {
                        sc.nextLine();
                        break;
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter number between 1 to 3.<ERROR>" + ConsoleColors.RESET);
                } catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.RED_BOLD + "<ERROR>That was not a number.  Please try again.<ERROR>" + ConsoleColors.RESET);
                }
                sc.nextLine();
            }

            boolean isUpdated = false;
            char firstChar = '0';

            switch (selectType) {
                case 1:
                    String name;
                    firstChar = '0';

                    do {
                        System.out.print("Update Medicine Name : ");
                        name = sc.nextLine();
                        if (name.isEmpty()) {
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>This Section Cannot Be Blank !<ERROR>" + ConsoleColors.RESET);
                        } else {
                            firstChar = name.charAt(0);
                            if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The Medicine Name Should Start with an Alphabet!<ERROR>" + ConsoleColors.RESET);
                            }
                        }
                    } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || name.isEmpty());
                    selectedMedicine.setMedicineName(name);
                    isUpdated = driver.medicine.replace(selectNo, selectedMedicine);
                    break;

                case 2:
                    String description;
                    firstChar = '0';

                    do {
                        System.out.print("Update Medicine Description : ");
                        description = sc.nextLine();
                        if (description.isEmpty()) {
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please Enter The Correct Medicine Description<ERROR>" + ConsoleColors.RESET);
                        } else {
                            firstChar = description.charAt(0);
                            if (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z')) {
                                System.out.println(ConsoleColors.RED_BOLD + "<ERROR>The medicine description should start with an Alphabet!<ERROR>" + ConsoleColors.RESET);
                            }
                        }
                    } while (!(firstChar >= 'A' && firstChar <= 'Z' || firstChar >= 'a' && firstChar <= 'z') || description.isEmpty());

                    selectedMedicine.setMedicineDesc(description);
                    isUpdated = driver.medicine.replace(selectNo, selectedMedicine);
                    break;
                case 3:
                    double price;
                    boolean isDouble;

                    do {
                        System.out.print("Update Medicine price : ");
                        if (sc.hasNextDouble()) {
                            price = sc.nextDouble();
                            isDouble = true;
                            selectedMedicine.setMedicinePrice(price);
                            isUpdated = driver.medicine.replace(selectNo, selectedMedicine);

                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "<ERROR>Please enter decimal value!<ERROR>" + ConsoleColors.RESET);
                            isDouble = false;
                            sc.next();
                        }

                    } while (!isDouble);

                    break;
                default:
            }

            if (isUpdated) {
                System.out.println("\n" + ConsoleColors.GREEN + "Medicine Updated Successfully." + ConsoleColors.RESET);
                System.out.println("\nUpdated row : ");
                tableFormat();
                System.out.println(selectedMedicine.toString());
                tableClose();
            }
        }
    }

    public void searchMedicine() {
        Iterator<Medicine> itr = driver.medicine.iterator();

        int selection = 0;

        System.out.print("==============================");
        System.out.println("\nSearch With Criteria : ");
        System.out.print("==============================");
        System.out.println("\n1. By Medicine ID ");
        System.out.println("2. By Medicine Price ");
        System.out.println("3. Back To Menu ");
        System.out.print("==============================");
        System.out.print("\nPlease Enter Your Selection (1-3) : ");

        selection = sc.nextInt();
        sc.nextLine();

        if (selection == 1) {
            int inpId;
            System.out.print("Please Enter The Medicine Id To Search (Eg:1) : ");
            inpId = sc.nextInt();
            boolean found = false;
            sc.nextLine();
            while (itr.hasNext()) {
                Medicine m = itr.next();
                if (m.medicineId == inpId) {
                    tableFormat();
                    System.out.println(m.toString());
                    tableClose();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("\n" + ConsoleColors.RED_BOLD + "<ERROR>NO MEDICINE ID FOUND<ERROR>" + ConsoleColors.RESET);
            }
        }

        if (selection == 2) {
            int priceSelection = 0;
            System.out.println("===============================");
            System.out.println("Select The Range of the price ");
            System.out.println("1. > $50");
            System.out.println("2. < $50");
            System.out.println("3. Exit");
            System.out.println("===============================");
            System.out.print("Enter Your Selection (1-3) : ");
            priceSelection = sc.nextInt();
            sc.nextLine();

            if (priceSelection == 1) {

                while (itr.hasNext()) {
                    Medicine m = itr.next();
                    if (m.medicinePrice > 50.00) {
                        System.out.println("==========================");
                        System.out.println("Showing the result : ");
                        System.out.print(m.medicineName + " : " + m.medicinePrice + "\n");
                        System.out.println("==========================");

                    }
                }
            }
            if (priceSelection == 2) {
                while (itr.hasNext()) {
                    Medicine m = itr.next();
                    if (m.medicinePrice < 50.00) {
                        System.out.println("==========================");
                        System.out.println("Showing the result : ");
                        System.out.print(m.medicineName + " : " + m.medicinePrice + "\n");
                        System.out.println("==========================");
                    }
                }
            } else {
                medicineMenu();
            }
        }
    }

    @Override
    public int compareTo(Medicine o) {
        return (int) (medicinePrice - o.medicinePrice);
    }

    @Override
    public String toString() {
        return String.format("%-7s\t\t%-30s %-50s %-50s ", getMedicineId(), getMedicineName(), getMedicineDesc(), getMedicinePrice());
    }

    public void tableFormat() {
        System.out.println("\n====================================================================================================================");
        System.out.println(String.format("%-10s\t%-30s %-50s %-50s", "ID", "Name", "Description", "Price"));
        System.out.println("====================================================================================================================");
    }

    public void tableFormatWithNo() {
        System.out.println("\n====================================================================================================================");
        System.out.println(String.format("%-2s\t%-13s%-29s %-50s %-50s", "NO", "ID", "Name", "Description", "Price"));
        System.out.println("====================================================================================================================");
    }

    public void tableClose() {
        System.out.println("\n====================================================================================================================");
        System.out.println("====================================================================================================================");

    }

}
