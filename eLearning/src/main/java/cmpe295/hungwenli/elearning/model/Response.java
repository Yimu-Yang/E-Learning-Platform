package cmpe295.hungwenli.elearning.model;

public class Response {

    private String response;

    public Response() {}

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response='" + response + '\'' +
                '}';
    }

}
