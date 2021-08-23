package my.sample.util;

public class Feedback {

    private int code;

    private String feedbackMessage;

    public Feedback() {
    }

    public Feedback( String msg ) {
        feedbackMessage = msg;
    }

    public Feedback( String msg, int code ) {
        feedbackMessage = msg;
        this.code = code;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage( String feedbackMessage ) {
        this.feedbackMessage = feedbackMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode( int code ) {
        this.code = code;
    }
}

