package SecureMessages;

public interface MessageCodec {
	byte [] encodeMessage(Message message);
	Message decodeMessage(byte[] data);

}