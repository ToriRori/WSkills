package lab_3;

public class USign implements Token {
    String s;
    public USign(String s){
        this.s = s;
    }

    @Override
    public boolean isValid() {
        return s.matches("\\-|\\+");
    }

    @Override
    public void set(String s) throws TokenError{
        this.s = "" + s;
    }

    @Override
    public String ToString(){
        return s;
    }
}
