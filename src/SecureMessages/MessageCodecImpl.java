package SecureMessages;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MessageCodecImpl implements MessageCodec {

	@Override
	public byte[] encodeMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("encode  called");
		Map<String, String> headers = message.getHeaders();
		byte[] payload=message.getPayload();
	        // Ensure the number of headers does not exceed 63
	        if (headers.size() > 63) {
	            throw new IllegalArgumentException("Number of headers exceeds the limit of 63.");
	        }

	        // Calculate total message length (headers + payload)
	        int totalLength = 1; // for the number of headers

	        for (Map.Entry<String, String> entry : headers.entrySet()) {
	            String headerName = entry.getKey();
	            String headerValue = entry.getValue();

	            int headerNameLen = headerName.getBytes(StandardCharsets.US_ASCII).length;
	            int headerValueLen = headerValue.getBytes(StandardCharsets.US_ASCII).length;

	            if (headerNameLen > 1023 || headerValueLen > 1023) {
	                throw new IllegalArgumentException("Header name or value exceeds the limit of 1023 bytes.");
	            }

	            totalLength += 2 + headerNameLen + 2 + headerValueLen;
	        }

	        int payloadLen = payload.length;

	        if (payloadLen > 256 * 1024) {
	            throw new IllegalArgumentException("Payload size exceeds the limit of 256 KiB.");
	        }

	        totalLength += 4 + payloadLen;

	        // Create the byte buffer with the calculated length
	        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

	        // Add the number of headers
	        buffer.put((byte) headers.size());

	        // Add each header name-value pair
	        for (Map.Entry<String, String> entry : headers.entrySet()) {
	            String headerName = entry.getKey();
	            String headerValue = entry.getValue();

	            byte[] headerNameBytes = headerName.getBytes(StandardCharsets.US_ASCII);
	            byte[] headerValueBytes = headerValue.getBytes(StandardCharsets.US_ASCII);

	            buffer.putShort((short) headerNameBytes.length);
	            buffer.put(headerNameBytes);
	            buffer.putShort((short) headerValueBytes.length);
	            buffer.put(headerValueBytes);
	        }

	        // Add the payload length and the payload
	        buffer.putInt(payloadLen);
	        buffer.put(payload);

	        return buffer.array();
	    
	}

	public Message decodeMessage(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);

        // Read the number of headers
        int numHeaders = buffer.get() & 0xFF;

        // Read each header name-value pair
        Map<String, String> headers = new HashMap<>();

        for (int i = 0; i < numHeaders; i++) {
            int headerNameLen = buffer.getShort() & 0xFFFF;
            byte[] headerNameBytes = new byte[headerNameLen];
            buffer.get(headerNameBytes);
            String headerName = new String(headerNameBytes, StandardCharsets.US_ASCII);

            int headerValueLen = buffer.getShort() & 0xFFFF;
            byte[] headerValueBytes = new byte[headerValueLen];
            buffer.get(headerValueBytes);
            String headerValue = new String(headerValueBytes, StandardCharsets.US_ASCII);

            headers.put(headerName, headerValue);
        }

        // Read the payload length and payload
        int payloadLen = buffer.getInt();
        byte[] payload = new byte[payloadLen];
        buffer.get(payload);

        return new Message(headers, payload);
    }

}