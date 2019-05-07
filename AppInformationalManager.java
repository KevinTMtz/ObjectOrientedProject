import java.util.ArrayList;
public abstract class AppInformationalManager {
    private static ArrayList<Resource> resources = AppLogin.getArraylistResource();

        public static void addToArray(Recording r) throws SameResourceException{
            //Check if it is different from other resources
            boolean different=true;
            for(int i=0; i<resources.size(); i++){
                if(r.getTitle().equals(resources.get(i).getTitle())){
                    different=false;
                }
            }
            if(different){
                //Add it to the array list
                resources.add(r);
                //Send it back to AppLogin
                
            } else throw new SameResourceException("You already add this resource");
        }

        public static void addToArray(Textual t) throws SameResourceException{
            //Check if it is different from other resources
            boolean different=true;
            for(int i=0; i<resources.size(); i++){
                if(t.getTitle().equals(resources.get(i).getTitle())){
                    different=false;
                }
            }
            if(different){
                //Add it to the array list
                resources.add(t);
                //Send it back to AppLogin
            } else throw new SameResourceException("You already add this resource");
        }
        public static void deleteFromArrayList(int index){
            //Delete the object
            resources.remove(index);
        }

        public static void updateResource(Recording r, int index) throws SameResourceException {
            //Delete the previous object
            deleteFromArrayList(index);

            //Add the updated one
            addToArray(r);
        }

        public static void updateResource(Textual t, int index) throws SameResourceException {
            //Delete the previous object
            deleteFromArrayList(index);

            //Add the updated one
            addToArray(t);
        }
}