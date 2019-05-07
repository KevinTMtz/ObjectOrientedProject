import java.util.ArrayList;

public abstract class AppInformationalManager {
    private static ArrayList<Resource> resources = (ArrayList<Resource>)AppLogin.getArraylistResource();

        public static void addToArray(Recording r) throws SameResourceException{
            //Check if it is different from other resources
            boolean different=true;
            System.out.println(resources.size());
            for(int i=0; i<resources.size(); i++){
                if(r.getTitle().equals(((Resource) resources.get(i)).getTitle())
                 &&(r.getCurrentStatus().equals(((Resource) resources.get(i)).getCurrentStatus()))
                 &&(r.getLanguage().equals(((Resource) resources.get(i)).getLanguage()))
                 &&(r.getRegionOfOrigin().equals(((Resource) resources.get(i)).getRegionOfOrigin()))
                 &&(r.getResponsibleOfTheFinding().equals(((Resource) resources.get(i)).getResponsibleOfTheFinding()))
                 &&(r.getTextualContent().equals(((Resource) resources.get(i)).getTextualContent()))
                 &&(r.getTranslatedConent().equals(((Resource) resources.get(i)).getTranslatedConent()))
                 ){
                    different=false;
                }
            }
            if(different){
                //Add it to the array list
                resources.add((javax.annotation.Resource) r);
                //Send it back to AppLogin
                AppLogin.setArraylistResource(resources);
            } else throw new SameResourceException("You already add this resource");
        }

        public static void addToArray(Textual t) throws SameResourceException{
            //Check if it is different from other resources
            boolean different=true;
            for(int i=0; i<resources.size(); i++){
                if(t.getTitle().equals(((Resource) resources.get(i)).getTitle())
                 &&(t.getCurrentStatus().equals(((Resource) resources.get(i)).getCurrentStatus()))
                 &&(t.getLanguage().equals(((Resource) resources.get(i)).getLanguage()))
                 &&(t.getRegionOfOrigin().equals(((Resource) resources.get(i)).getRegionOfOrigin()))
                 &&(t.getResponsibleOfTheFinding().equals(((Resource) resources.get(i)).getResponsibleOfTheFinding()))
                 &&(t.getTextualContent().equals(((Resource) resources.get(i)).getTextualContent()))
                 &&(t.getTranslatedConent().equals(((Resource) resources.get(i)).getTranslatedConent()))
                 ){
                    different=false;
                }
            }
            if(different){
                //Add it to the array list
                resources.add((javax.annotation.Resource) t);
                //Send it back to AppLogin
                AppLogin.setArraylistResource(resources);
            } else throw new SameResourceException("You already add this resource");
        }
        public static void deleteFromArrayList(int index){
            //Delete the object
            resources.remove(index);
            AppLogin.setArraylistResource(resources);
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