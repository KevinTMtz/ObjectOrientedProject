import java.io.Serializable;
public abstract class Recurso implements Serializable{
    private String textualContent;
    private String translatedContent;
    private String title;
    private String language;
    private String currentStatus;
    private String regionOfOrigin;
    private String responsibleOfTheFinding;

    public String toString(){
        return title+" ("+responsibleOfTheFinding+")";
    }

    public void setTextualContent(String textualContent) throws EmptyFieldException{
        if(textualContent==null || textualContent.length()==0){
            throw new EmptyFieldException("The content cannot be null");
        }
        this.textualContent = textualContent;
    }
    public void setTitle(String title) throws EmptyFieldException{
        if(title==null || title.length()==0){
            throw new EmptyFieldException("The title cannot be null");
        }
        this.title=title;
    }
    public void setLanguage(String language) throws EmptyFieldException{
        if(language==null || language.length()==0){
            throw new EmptyFieldException("The language cannot be null");
        }
        this.language = language;
    }
    public void setCurrentStatus(String currentStatus) throws EmptyFieldException{
        if(currentStatus==null || currentStatus.length()==0){
            throw new EmptyFieldException("The status cannot be null");
        }
        this.currentStatus = currentStatus;
    }
    public void setRegionOfOrigin(String regionOfOrigin) throws EmptyFieldException{
        if(regionOfOrigin==null || regionOfOrigin.length()==0){
            throw new EmptyFieldException("The region cannot be null");
        }
        this.regionOfOrigin = regionOfOrigin;
    }
    public void setResponsibleOfTheFinding(String responsibleOfTheFinding) throws EmptyFieldException{
        if(responsibleOfTheFinding==null || responsibleOfTheFinding.length()==0){
            throw new EmptyFieldException("The responsible cannot be null");
        }
        this.responsibleOfTheFinding = responsibleOfTheFinding;
    }
    public void setTranslatedContent(String translatedContent){
        this.translatedContent = translatedContent;
    }

    public String getTextualContent() {
        return textualContent;
    }
    public String getTitle() {
        return title;
    }
    public String getLanguage() {
        return language;
    }
    public String getCurrentStatus() {
        return currentStatus;
    }
    public String getRegionOfOrigin() {
        return regionOfOrigin;
    }
    public String getResponsibleOfTheFinding() {
        return responsibleOfTheFinding;
    }
    public String getTranslatedConent(){
        return translatedContent;
    }
    
}
