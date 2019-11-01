/*
package ie.gmit.ds;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;

import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class PasswordServiceImpl<HashInput, HashOutput, ValidInput> {
    public void hash(HashInput request, StreamObserver<HashOutput> responseObserver)
    {
    	
        // Gets the password from the request and gives to char array
        char[] password = request.getPassword().toCharArray();
        //Calling and using method from password class
        byte[] salt = Passwords.getNextSalt();
        //Creates a hash of the password using given salt
        byte[] hashPassword = Passwords.hash(password, salt);


        //Response
        HashOutput response = HashOutput.newBuilder()
                .setUserId(request.getUserId())
                .setHashedPassword(ByteString.copyFrom(hashPassword))
                .setSalt(ByteString.copyFrom(salt)).build();

        //Gives the output to who asks
        responseObserver.onNext(response);
        //to say request is over
        responseObserver.onCompleted();

    }

    public void validate(ValidInput request, StreamObserver<BoolValue> responseObserver)
    {
        BoolValue response = BoolValue.of(Passwords.isExpectedPassword(
                request.getPassword().toCharArray(),
                request.getSalt().toByteArray(),
                request.getHashedPassword().toByteArray()));

        //Gives the output to who asks
        responseObserver.onNext(response);
        //to say request is over
        responseObserver.onCompleted();

    }

	public ServerServiceDefinition bindService() {
		// TODO Auto-generated method stub
		return null;
	}
}
*/
