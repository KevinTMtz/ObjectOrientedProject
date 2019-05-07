import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
import java.util.ArrayList;

import javax.annotation.Resource;

public abstract class AppLogin implements IPersist {
    private static ArrayList<User> arrayListUsers;
    private static ArrayList<Resource> arrayListResources;

    // Sign in
    public static void signIn() {
        Boolean userCheck = false;
        int userIndex = 0;
        
        for (int i=0; i<arrayListUsers.size(); i++) {
            if ((arrayListUsers.get(i).getUsername()).equals(GUILogin.getUsernameLoginValue())) {
                userCheck = true;
                userIndex = i;
            }
        }

        if ((0 == GUILogin.getUsernameLoginValue().length()) || (0 == GUILogin.getPasswordLoginValue().length())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while signing in");
            alert.setHeaderText("Enter user and password");
            alert.setContentText("Enter all info");
            alert.showAndWait();

        } else if (userCheck){
            if ((arrayListUsers.get(userIndex).getPassword()).equals(GUILogin.getPasswordLoginValue())) {
                String userType = arrayListUsers.get(userIndex).getUserType();

                if (userType.equals("Manager")) {
                    GUILogin.changeScene(new GUIManager());
                } else if (userType.equals("Information Manager")) {
                    GUILogin.changeScene(new GUIInformationManager());
                } else if (userType.equals("Translator")) {
                    GUILogin.changeScene(new GUITranslator());
                } else if (userType.equals("Consultant")) {
                    GUILogin.changeScene(new GUIConsultant());
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while signing in");
                alert.setHeaderText("Incorrect password");
                alert.setContentText("Try another one");
                alert.showAndWait();
            }
            
        } else if (!userCheck){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while signing in");
            alert.setHeaderText("This user does not exist");
            alert.setContentText("Register or try another one");
            alert.showAndWait();
        }
    }

    // For loading all the saved users
    public static void retrieveResourcesAndUsers() {
        try {
            File checkUserArrayList = new File("build/arrayListUsers");
            File checkResourceArrayList = new File("build/arrayListResources");

            if (checkUserArrayList.exists()) {
                FileInputStream fis = new FileInputStream("build/arrayListUsers");
                ObjectInputStream ois = new ObjectInputStream(fis);

                arrayListUsers = new ArrayList<User>();
                arrayListUsers = (ArrayList<User>) ois.readObject();
                
                ois.close();
            } else {
                arrayListUsers = new ArrayList<User>();

                FileOutputStream fos = new FileOutputStream("build/arrayListUsers");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(arrayListUsers);
                
                oos.close();
                fos.close();
            }

            if (checkResourceArrayList.exists()) {
                FileInputStream fis = new FileInputStream("build/arrayListResources");
                ObjectInputStream ois = new ObjectInputStream(fis);

                arrayListResources = new ArrayList<Resource>();
                arrayListResources = (ArrayList<Resource>) ois.readObject();
                
                ois.close();
            } else {
                arrayListResources = new ArrayList<Resource>();

                FileOutputStream fos = new FileOutputStream("build/arrayListResources");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(arrayListResources);
                
                oos.close();
                fos.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

    // Add user in the register window
    public static void addUser() {
        try {
            Boolean userCheck = false;

            for (int i=0; i<arrayListUsers.size(); i++) {
                if ((arrayListUsers.get(i).getUsername()).equals(GUILogin.getUsernameRegisterValue())) {
                    userCheck = true;
                }
            }
            
            if ((0 == GUILogin.getUsernameRegisterValue().length()) || (0 == GUILogin.getPasswordRegisterValue().length())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Enter all info");
                alert.setHeaderText("Enter user and password");
                alert.showAndWait();
            } else if (!userCheck) {
                FileOutputStream userFile = new FileOutputStream("build/arrayListUsers");
                ObjectOutputStream userWrite = new ObjectOutputStream(userFile);

                User newUser = null;
    
                if (GUILogin.getUserTypeRegister().equals("Manager")) {
                    newUser = new Manager();
                } else if (GUILogin.getUserTypeRegister().equals("Information Manager")) {
                    newUser = new InformationManager();
                } else if (GUILogin.getUserTypeRegister().equals("Translator")) {
                    newUser = new Translator();
                } else if (GUILogin.getUserTypeRegister().equals("Consultant")) {
                    newUser = new Consultant();
                }
    
                newUser.setUsername(GUILogin.getUsernameRegisterValue());
                newUser.setPassword(GUILogin.getPasswordRegisterValue());
                newUser.setUserType(GUILogin.getUserTypeRegister());

                arrayListUsers.add(newUser);
    
                userWrite.writeObject(arrayListUsers);
                userWrite.close();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User not available");
                alert.setHeaderText("This user, already exist");
                alert.showAndWait();
            }
        } catch (EmptyFieldException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Enter all info");
            alert.setHeaderText("Enter user and password");
            alert.showAndWait();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } 
    }
}