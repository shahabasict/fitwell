package fitwell_project.com.Fitness_service.exception;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException (String message){
        super(message);
    }
}
