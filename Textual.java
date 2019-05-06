import java.io.Serializable;
public class Textual extends Resource implements Serializable{
    private String numberOfPages;
    private String numberOfWords;

    public String getNumberOfPages() {
        return numberOfPages;
    }
    public String getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfPages(String numberOfPages) throws EmptyFieldException{
        if(numberOfPages==null || numberOfPages.length()==0){
            throw new EmptyFieldException("The number of pages cannot be null");
        }
        this.numberOfPages = numberOfPages;
    }
    public void setNumberOfWords(String numberOfWords) throws EmptyFieldException{
        if(numberOfWords==null || numberOfWords.length()==0){
            throw new EmptyFieldException("The number of words cannot be null");
        }
        this.numberOfWords = numberOfWords;
    }
}