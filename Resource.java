public abstract class Resource {
    private String textualContent;
    private String language;
    private String currentStatus;
    private String regionOfOrigin;
    private String responsibleOfTheFinding;
    
    public void setTextualContent(String textualContent){
        this.textualContent = textualContent;
    }
    
    public String getTextualContent(){
        return textualContent;
    }
    
    public void setLanguage(String language){
        this.language = language;
    }
    
    public String getLanguage(){
        return language;
    }
    
    public void setCurrentStatus(String currentStatus){
        this.currentStatus = currentStatus;
    }
    
    public String getCurrentStatus(){
        return currentStatus;
    }
    
    public void setRegionOfOrigin(String regionOfOrigin){
        this.regionOfOrigin = regionOfOrigin;
    }
    
    public String getRegionOfOrigin(){
        return regionOfOrigin;
    }
    
    public void setResponsibleOfTheFinding(String responsibleOfTheFinding){
        this.responsibleOfTheFinding = responsibleOfTheFinding;
    }
    
    public String getResponsibleOfTheFinding(){
        return responsibleOfTheFinding;
    }
}
