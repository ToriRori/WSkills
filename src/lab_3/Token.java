package lab_3;

public interface Token extends Cloneable {
    public boolean isValid();
    public void set(String s) throws TokenError;
    public String ToString() throws TokenError;
}
