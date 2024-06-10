/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.DictionaryInterface;
import ADT.HashedDictionary;
import ADT.Iterator;
import client.driver;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Staff extends Person {

    private static int id = 1001;
    private String staffId;
    private String password;
    private String status;

   
    
    Scanner sc = new Scanner(System.in);

    public Staff(){
        super("", "", 0, "");
    }
    

    public Staff(String name, String IC, int age, String phoneNum,String password, String status) {
        super(name, IC, age, phoneNum);
        this.staffId = addStaffId();
        this.password = password;
        this.status = status;
    }

    private String addStaffId() {
        String idIncrement = "S" + id++;
        return idIncrement;
    }

    public String getStaffId() {
        return staffId;
    }

    public static int getId() {
        return id;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
    
    

    public void addStaff() {
        String name = "", ic = "", phoneNum = "", status = "",password="";
        int age = 0;
        int selectStatus = 0;
        boolean ageInt = true;
        driver d = new driver();

        System.out.println("\nNEW STAFF DETAIL");
        
        
        System.out.print("Enter staff name : ");
        name = sc.nextLine();
        
        System.out.print("Enter staff password : ");
        password = sc.nextLine();
        
        do{
            System.out.print("Enter staff ic exp:(02-1111-22-3333): ");
        ic = sc.nextLine();
        if(!isIcDuplicate(ic)){
            System.out.println("\nError IC duplicate with others.\n");
        }
        }while(!isIcDuplicate(ic));
        
        
        while (ageInt) {
            System.out.print("Enter staff age : ");
        if (sc.hasNextInt()){
            age = sc.nextInt();
            sc.nextLine();
        }
        else {
            sc.next();
            System.out.println("\nPlease insert number only\n");
            continue;
        }
        ageInt = false;
        }
        
        System.out.print("Enter staff phone number exp:(011-2222-333): ");
        phoneNum = sc.nextLine();

        while (selectStatus != 1 && selectStatus != 2) {
            System.out.println("STATUS");
            System.out.println("1. Active");
            System.out.println("2. Inactive");

            try {
                System.out.print("Select 1 or 2 : ");
                selectStatus = sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("That was not a number.  Please try again.");
            }

            sc.nextLine();
            if (selectStatus == 1) {
                System.out.println("Status = Active");
                status = "Active";
            } else if (selectStatus == 2) {
                System.out.println("Status = Inactive");
                status = "Inactive";
            } else {
                System.out.print("Please select 1 or 2 only.");
            }
        }

        Staff staff15 = new Staff(name, ic, age, phoneNum,password, status);
        d.staffList.add(staff15.getStaffId(), staff15);
        
        tableFormat();
        System.out.println(String.format("%s\t%-30s %-20s %-20s %-20s %-20s",staff15.staffId,name,ic,age,phoneNum,status));
        System.out.println("\nNew Staff Added Successfully.");
    }

    public void displayStaff(){
        driver d = new driver();
        
        System.out.println("\n\t\t\t\tDISPLAY ALL STAFF (TOTAL STAFF : "+d.staffList.getSize()+")");

        tableFormat();
        d.staffList.display(); 
        
//         Iterator<String> keyIterator = d.staffList.getKeyIterator();
//        Iterator<Staff> valueIterator = d.staffList.getValueIterator();
//
//        while (keyIterator.hasNext() && valueIterator.hasNext()) {
//            System.out.println(valueIterator.next());
//        }
        
    }
    
    
     public void searchStaff(){
        driver d = new driver();
        String idSearch = "";
        do{
            
            System.out.println("\nSEARCH STAFF BY ID, Exit enter (exit)");
            System.out.print("Enter staff id exp(S1002) : ");
            idSearch = sc.nextLine();

            if (d.staffList.contains(idSearch)) {
                searchTableFormat();
                System.out.println(d.staffList.getValue(idSearch));
            } else if (idSearch.equals("exit")) {
                System.out.println("\nExit Search Program");
            } else {
                System.out.println("\nNOT FOUND");
            }

        }while(!"exit".equals(idSearch.toLowerCase()));
    }
     
     public void updateStaff(){
        String name = "", ic = "", phoneNum = "", status = "",password="";
        int age = 0;
        int selectStatus = 0;
        driver d = new driver();
        String idSearch = "";
        int selectType = 0;
        
        do{
            System.out.println("\nSEARCH STAFF ID TO UPDATE, Exit enter (exit)");
            System.out.print("Enter staff id exp(S1002) : ");
            idSearch = sc.nextLine();

            if (d.staffList.contains(idSearch)) {
               
                do{
                   boolean ageInt = true,isOldPsw=false;
                   Staff staff = d.staffList.getValue(idSearch);
                   
                    searchTableFormat();
                    System.out.println(staff);
                    
                    System.out.println("\nSELECT TYPE FOR UPDATE STAFF DETAIL");
                    System.out.println("1. NAME");
                    System.out.println("2. IC");
                    System.out.println("3. AGE");
                    System.out.println("4. PHONENUMBER");
                    System.out.println("5. STATUS");
                    System.out.println("6. PASSWORD");
                    System.out.println("7. EXIT UPDATE PROGRAM");
                    
                    System.out.print("Select type of detail for update 1-8 only : ");
                    selectType = sc.nextInt();
                    sc.nextLine();
                    
                    switch(selectType) {
                      case 1:
                          System.out.print("Update staff name : ");
                          name = sc.nextLine();
//                           d.staffList.getValue(idSearch).setName(name);
                          staff.setName(name);
                          d.staffList.replace(idSearch, staff);
                        break;
                      case 2:
                          do {
                              System.out.print("Enter staff ic exp:(02-1111-22-3333): ");
                              ic = sc.nextLine();
                              if (!isIcDuplicate(ic)) {
                                  System.out.println("\nError IC duplicate with others.\n");
                              }
                          } while (!isIcDuplicate(ic));
                          d.staffList.getValue(idSearch).setIC(ic);
                        break;
                      case 3:
                          while (ageInt) {
                              System.out.print("Enter staff age : ");
                              if (sc.hasNextInt()) {
                                  age = sc.nextInt();
                                  sc.nextLine();
                              } else {
                                  sc.next();
                                  System.out.println("\nPlease insert number only\n");
                                  continue;
                              }
                              ageInt = false;
                          }
                          d.staffList.getValue(idSearch).setAge(age);
                        break;
                      case 4:
                          System.out.print("Enter staff phone number exp:(011-2222-333): ");
                          phoneNum = sc.nextLine();
                             d.staffList.getValue(idSearch).setPhoneNum(phoneNum);
                        break;
                      case 5:
                        while (selectStatus != 1 && selectStatus != 2) {
                              System.out.println("STATUS");
                              System.out.println("1. Active");
                              System.out.println("2. Inactive");

                              try {
                                  System.out.print("Select 1 or 2 : ");
                                  selectStatus = sc.nextInt();

                              } catch (InputMismatchException e) {
                                  System.out.println("That was not a number.  Please try again.");
                              }

                              sc.nextLine();
                              if (selectStatus == 1) {
                                  System.out.println("Status = Active");
                                  status = "Active";
                                  d.staffList.getValue(idSearch).setStatus(status);
                              } else if (selectStatus == 2) {
                                  System.out.println("Status = Inactive");
                                  status = "Inactive";
                                  d.staffList.getValue(idSearch).setStatus(status);
                              } else {
                                  System.out.print("Please select 1 or 2 only.");
                              }
                          }
                          d.staffList.getValue(idSearch).setStatus(status);
                        break;
                        case 6:
                            while(!isOldPsw){
                             System.out.print("Enter old password : ");
                            password = sc.nextLine();
                            if( d.staffList.getValue(idSearch).password.equals(password)){
                                 System.out.print("\nPassword Match");
                                System.out.print("Enter new password : ");
                                   password = sc.nextLine();
                                 d.staffList.getValue(idSearch).setPassword(password);
                                 isOldPsw=true;
                            }else{
                              System.out.print("\nOLD PASSWORD NOT MATCH\n");
                                isOldPsw=false;
                            }
                            }
                        
                        break;
                      default:
                    }
                             
                }while(selectType!=7);
                      
            } else if (idSearch.equals("exit")) {
                System.out.println("\nExit Search Program");
            } else {
                System.out.println("\nNOT FOUND");
            }

        }while(!"exit".equals(idSearch.toLowerCase())&&selectType!=7);
    }
     
    public void deleteStaff(){
        driver d = new driver();
        String idSearch = "";

        int selectType = 0;
        do{
            System.out.println("\nSEARCH STAFF ID TO DELETE, Exit enter (exit)");
            System.out.print("Enter staff id exp(S1002) : ");
            idSearch = sc.nextLine();

            if (d.staffList.contains(idSearch)) {
   
                 searchTableFormat();
                 System.out.println(d.staffList.getValue(idSearch));
                 
                 System.out.print("You sure want to delete ir? (y) or (n) : ");
                 String confirm = sc.nextLine();
                 if(confirm.equals("y")){
                     d.staffList.remove(idSearch);
                     System.out.println("\nDELETED SUCCESSFULLY");
                 }else
                     System.out.println("\nDELETE CANCEL.");
                      
            } else if (idSearch.equals("exit")) {
                System.out.println("\nExit Search Program");
            } else {
                System.out.println("\nNOT FOUND");
            }

        }while(!"exit".equals(idSearch.toLowerCase())&&selectType!=7);
    }
    
    
    public void clearStaff(){
        driver d = new driver();
        
        System.out.print("You sure want to clear it all? (y) or (n) : ");
        String confirm = sc.nextLine();
        if (confirm.toLowerCase().equals("y")) {
            d.staffList.clear();
            if(d.staffList.isEmpty())
            System.out.println("\nCLEAR ALL STAFF DATA SUCCESSFULLY");
        } else
            System.out.println("\nCLEAR CANCEL.");
        
    }
    
    public void staffMenu(){
        int selectMenu=0;
        while(selectMenu!=7){    
        System.out.println("\nSTAFF MODULE");
        System.out.println("1. ADD STAFF");
        System.out.println("2. DISPLAY ALL STAFF DETAIL");
        System.out.println("3. UPDATE STAFF DETAIL");
        System.out.println("4. DELETE STAFF");
        System.out.println("5. SEARCH STAFF");    
        System.out.println("6. CLEAR ALL STAFF DATA"); 
        System.out.println("7. EXIT BACK TO MAIN MENU");
        
        try {
        System.out.print("Enter number (1-7) : ");    
        selectMenu = sc.nextInt();
        
        }catch (InputMismatchException e) {
        System.out.println("That was not a number.  Please try again.");
        }
        sc.nextLine();
        
         switch(selectMenu) {
          case 1:
              addStaff();      
            break;
          case 2:
              displayStaff();
            break;
          case 3:
              updateStaff();
            break;
          case 4:
              deleteStaff();
            break;
          case 5:
              searchStaff();
              break;
          case 6:
              clearStaff();
            break;
          default:
         }      
        }
         
       
        
    }
    
    private boolean isIcDuplicate(String inputIc){
        driver d = new driver();
        Iterator<String> keyIterator = d.staffList.getKeyIterator();
        Iterator<Staff> valueIterator = d.staffList.getValueIterator();

        while (keyIterator.hasNext() && valueIterator.hasNext()) {
            if(inputIc.equals(valueIterator.next().getIC())){
                return false;
            }  
        }
        return true;
    }
    

    
    public void tableFormat(){
        System.out.println("\n====================================================================================================================");
        System.out.println(String.format("ID\t%-30s %-20s %-20s %-20s %-20s","NAME","IC","AGE","PHONE NUMBER","STATUS"));
        System.out.println("====================================================================================================================");
    }
    
        public void searchTableFormat(){
        System.out.println("\n====================================================================================================================");
        System.out.println(String.format("%-30s %-20s %-20s %-20s %-20s","NAME","IC","AGE","PHONE NUMBER","STATUS"));
        System.out.println("====================================================================================================================");
    }
    
    @Override
    public String toString() {
        String print = String.format("%-30s %-20s %-20s %-20s %-20s",super.getName(),super.getIC(),super.getAge(),super.getPhoneNum(),status);
        return print;
    }



}
