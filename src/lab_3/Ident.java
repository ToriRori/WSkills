package lab_3;

public class Ident implements Token{
    String s;
    public Ident(String s){
        this.s = s;
    }

    @Override
    public boolean isValid() {
        return s.matches("((_+)|(\\w+))*+");
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
