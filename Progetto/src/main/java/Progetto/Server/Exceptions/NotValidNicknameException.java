package progetto.server.exceptions;

public class NotValidNicknameException extends Exception {
    public NotValidNicknameException(String message){
        super(message);
    }
}
