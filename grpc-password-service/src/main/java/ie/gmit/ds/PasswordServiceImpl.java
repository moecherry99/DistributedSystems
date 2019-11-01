package ie.gmit.ds;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;

import io.grpc.stub.StreamObserver;

public class PasswordServiceImpl extends PasswordServiceGrpc.PasswordServiceImplBase {

	@Override
	public void hash(HashRequest hashRequest, StreamObserver<HashResponse> responseObserver) {

		// Generate salt
		byte[] salt = Passwords.getNextSalt();

		// Password goes to char array (letters and numbers)
		char[] passwordAsCharArray = hashRequest.getPassword().toCharArray();

		// Hashed password
		byte[] hashedPassword = Passwords.hash(passwordAsCharArray, salt);

		// Build Hashes response 
		HashResponse hashResponse = HashResponse.newBuilder().setUserId(hashRequest.getUserId())
				
		// copies bytes to byteString
		.setHashedPassword(ByteString.copyFrom(hashedPassword)) 
		
		// Copies byteString to salt
		.setSalt(ByteString.copyFrom(salt)).build();

		// Gives hashed response
		responseObserver.onNext(hashResponse);
		responseObserver.onCompleted();

	}

	@Override
	public void validate(ValidationRequest validationRequest, StreamObserver<BoolValue> responseObserver) {
		try {
			// objects
			char[] userPassword = validationRequest.getPassword().toCharArray();
			byte[] hashedPassword = validationRequest.getHashedPassword().toByteArray();
			byte[] salt = validationRequest.getSalt().toByteArray();

			// Gives true if pw matches
			if (Passwords.isExpectedPassword(userPassword, salt, hashedPassword)) {
				responseObserver.onNext(BoolValue.newBuilder().setValue(true).build());
			}
			// If hash isn't correct, returns false
			else {
				responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
			}
		}

		// returns false on runtime exception
		catch (RuntimeException ex) {
			responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
		}
	}
}
