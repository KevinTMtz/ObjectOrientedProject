import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import javafx.scene.control.*;

public abstract class AppCulturalHeritage implements IPersist {
    private Resource[] resources;
    private int resourceCounter;
    private static User[] users = new User[10];
    private static int userCounter;

    // Sign in
    public static void signIn() {
        try {
            File userCheck = new File("users/"+GUICulturalHeritage.getUsernameLoginValue()+".bro");

            if ((0 == GUICulturalHeritage.getUsernameLoginValue().length()) || (0 == GUICulturalHeritage.getPasswordLoginValue().length())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while signing in");
                alert.setHeaderText("Enter user and password");
                alert.setContentText("Enter all info");

                alert.showAndWait();

            } else if (userCheck.exists()){
                    //Make this a separated string ("storageFiles/+users+.bro")
                    FileInputStream userFile = new FileInputStream("users/"+GUICulturalHeritage.getUsernameLoginValue()+".bro");
                    ObjectInputStream userRead = new ObjectInputStream(userFile);

                    User tempUser = (User) userRead.readObject();
                    userRead.close();

                    if (tempUser.getPassword().equals(GUICulturalHeritage.getPasswordLoginValue())) {
                        String userType = tempUser.getUserType();

                        if (userType.equals("Manager")) {
                            GUICulturalHeritage.changeScene(new GUIManager());
                        } else if (userType.equals("Information Manager")) {
                            GUICulturalHeritage.changeScene(new GUIInformationManager());
                        } else if (userType.equals("Translator")) {
                            GUICulturalHeritage.changeScene(new GUITranslator());
                        } else if (userType.equals("Consultant")) {
                            GUICulturalHeritage.changeScene(new GUIConsultant());
                        }
 
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error while signing in");
                        alert.setHeaderText("Incorrect password");
                        alert.setContentText("Try another one");
            
                        alert.showAndWait();
                    }
                
            } else if (!userCheck.exists()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error while signing in");
                alert.setHeaderText("This user does not exist");
                alert.setContentText("Register or try another one");

                alert.showAndWait();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error while signing in");
            alert.setHeaderText("Incorrect password");
            alert.setContentText("Try another password");

            alert.showAndWait();

            System.out.println(cnfe.getMessage());
        }
    }

    // Add user in the register window
    public static void addUser() {
        try {
            File userCheck = new File("users/"+GUICulturalHeritage.getUsernameRegisterValue()+".bro");
            if ((0 == GUICulturalHeritage.getUsernameRegisterValue().length()) || (0 == GUICulturalHeritage.getPasswordRegisterValue().length())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Enter all info");
                alert.setHeaderText("Enter user and password");
    
                alert.showAndWait();
            } else if (!userCheck.exists()) {
                //Make this a separated string ("storageFiles/+users+.bro")
                FileOutputStream userFile = new FileOutputStream("users/"+GUICulturalHeritage.getUsernameRegisterValue()+".bro");
                ObjectOutputStream userWrite = new ObjectOutputStream(userFile);

                User newUser = null;
    
                if (GUICulturalHeritage.getUserTypeRegister().equals("Manager")) {
                    newUser = new Manager();
                } else if (GUICulturalHeritage.getUserTypeRegister().equals("Information Manager")) {
                    newUser = new InformationManager();
                } else if (GUICulturalHeritage.getUserTypeRegister().equals("Translator")) {
                    newUser = new Translator();
                } else if (GUICulturalHeritage.getUserTypeRegister().equals("Consultant")) {
                    newUser = new Consultant();
                }
    
                newUser.setUsername(GUICulturalHeritage.getUsernameRegisterValue());
                newUser.setPassword(GUICulturalHeritage.getPasswordRegisterValue());
                newUser.setUserType(GUICulturalHeritage.getUserTypeRegister());
    
                userWrite.writeObject(newUser);
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