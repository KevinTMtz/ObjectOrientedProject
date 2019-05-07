import java.io.Serializable;
public class Recording extends Recurso implements Serializable{
    private String duration;
    private String pathOfFile;

    public String getDuration() {
        return duration;
    }
    public String getPathOfFile() {
        return pathOfFile;
    }

    public void setDuration(String duration) throws EmptyFieldException{
        if(duration==null || duration.length()==0){
            throw new EmptyFieldException("The duration cannot be null");
        }
        this.duration = duration;
    }
    public void setPathOfFile(String pathOfFile) throws EmptyFieldException{
        if(pathOfFile==null || pathOfFile.length()==0){
            throw new EmptyFieldException("The path cannot be null");
        }
        this.pathOfFile = pathOfFile;
    }
}