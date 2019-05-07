import java.util.ArrayList;
public abstract class AppInformationalManager implements IPersist {
    private static ArrayList<Resource> resources = AppLogin.getArraylistResource();

        public void addToArray(Resource r) throws SameResourceException{
            //Check if it is different from other resources
            boolean different=true;
            for(int i=0; i<resources.size(); i++){
                if(r.getTitle().equals(resources.get(i).getTitle())){
                    different=false;
                }
            }
            if(different){
                //Check which child
                if(r instanceof Recording)  r=(Recording) r;
                if(r instanceof Textual)    r=(Textual) r;

                //Add it to the array list
                resources.add(r);
                
            } else throw new SameResourceException("You already add this resource");
        }

        public void deleteFromArrayList(int index){
            //Delete the object
            resources.remove(index);
        }

        public void updateResource(Resource r, int index) throws SameResourceException {
            //Delete the previous object
            deleteFromArrayList(index);

            //Add the updated one
            addToArray(r);
        }
}