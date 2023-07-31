package SecureMessages;

import java.util.Map;

public class Message {
	
public Map<String, String> headers;
public byte[] payload;
public Message(Map<String, String> headers, byte[] payload) {
    this.headers = headers;
    this.payload = payload;
}

public Map<String, String> getHeaders() {
    return headers;
}

public byte[] getPayload() {
    return payload;
}
}