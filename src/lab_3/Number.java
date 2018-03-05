package lab_3;

class Number implements Token{

    String s;
    public Number(String s){
        this.s = s;
    }
    public boolean isValid() {
        return s.matches("(\\d+)|((\\d+)\\.(\\d+))");
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
