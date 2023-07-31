package SecureMessages;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MessageInit {

	public static void main(String[] args) {
		System.out.println("main method begin");
		
			MessageCodec msgImpl = new MessageCodecImpl();
		
		// TODO Auto-generated method stub

		
		Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer xyz123");
        byte[] payload = "Sample data for assignment test.".getBytes(StandardCharsets.UTF_8);

        Message msg= new Message(headers, payload);
        byte[] encodedMessage = msgImpl.encodeMessage(msg);
        System.out.println("Encoded message size: " + encodedMessage.length + " bytes");

        Message decodedMessage = msgImpl.decodeMessage(encodedMessage);
        System.out.println("Decoded headers: " + decodedMessage.getHeaders());
        System.out.println("Decoded payload: " + new String(decodedMessage.getPayload(), StandardCharsets.UTF_8));
	}

}