package ir.xako.FCGI.Utils;

public class ComponentNotReadyException extends Exception{

    String clName;

    public ComponentNotReadyException(String className){
        this.clName = className;
    }

    @Override
    public String toString(){
        return clName + " is not ready to be sent!";
    }
}
