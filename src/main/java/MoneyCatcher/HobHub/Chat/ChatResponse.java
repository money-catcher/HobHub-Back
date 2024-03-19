package MoneyCatcher.HobHub.Chat;

public class ChatResponse {
    private String message;

    public ChatResponse() {
        // Default constructor
    }

    public ChatResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
