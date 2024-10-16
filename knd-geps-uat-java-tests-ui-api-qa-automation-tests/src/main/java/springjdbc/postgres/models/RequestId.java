package springjdbc.postgres.models;

public class RequestId {

    String request_id;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return  request_id;
    }

    public RequestId() {
  }

    public RequestId(String request_id) {
        this.request_id = request_id;
    }
}
