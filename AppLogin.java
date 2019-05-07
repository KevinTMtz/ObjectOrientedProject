import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
import java.util.ArrayList;

public abstract class AppLogin implements IPersist {
    private static ArrayList<User> arrayListUsers;
    private static ArrayList<Recurso> arrayListResources;

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

    // For writing users and resources in files
    public static void persist() {
        try {
            FileOutputStream userFile = new FileOutputStream("build/arrayListUsers");
            ObjectOutputStream userWrite = new ObjectOutputStream(userFile);
            
            userWrite.writeObject(arrayListUsers);
            userFile.close();
            userWrite.close();

            FileOutputStream userResource = new FileOutputStream("build/arrayListResources");
            ObjectOutputStream resourceWrite = new ObjectOutputStream(userResource);
            
            resourceWrite.writeObject(arrayListResources);
            userResource.close();
            resourceWrite.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    // For loading all the saved resources & users
    public static void retrieveResourcesAndUsers() {
        try {
            File checkUserArrayList = new File("build/arrayListUsers");
            File checkResourceArrayList = new File("build/arrayListResources");

            if (checkUserArrayList.exists()) {
                FileInputStream fis1 = new FileInputStream("build/arrayListUsers");
                ObjectInputStream ois1 = new ObjectInputStream(fis1);

                arrayListUsers = new ArrayList<User>();
                arrayListUsers = (ArrayList<User>) ois1.readObject();
                
                fis1.close();
                ois1.close();
            } else {
                arrayListUsers = new ArrayList<User>();
                
            }

            if (checkResourceArrayList.exists()) {
                FileInputStream fis2 = new FileInputStream("build/arrayListResources");
                ObjectInputStream ois2 = new ObjectInputStream(fis2);

                arrayListResources = new ArrayList<Recurso>();
                arrayListResources = (ArrayList<Recurso>) ois2.readObject();
                
                fis2.close();
                ois2.close();
            } else {
                arrayListResources = new ArrayList<Recurso>();

            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

    // Add user in the register window
    public static void registerUser() {
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
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User not available");
                alert.setHeaderText("This user, already exist");
                alert.showAndWait();
            } 
        } catch (EmptyFieldException efe) {
            System.out.println(efe.getMessage());
        }
    }

    public static ArrayList<User> getArraylistUser() {
        return arrayListUsers;
    }

    public static void setArraylistUser(ArrayList<User> tempArrayList) {
        arrayListUsers = tempArrayList;
    }

    public static ArrayList<Recurso> getArraylistResource() {
        return arrayListResources;
    }

    public static void setArraylistResource(ArrayList<Recurso> tempArrayList) {
        arrayListResources = tempArrayList;
    }
}