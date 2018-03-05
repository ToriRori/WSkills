package lab_3;

abstract class TokenError extends Exception{
    public TokenError() {
        super();
    }

    public TokenError(String message) {
        super(message);
    }
}
