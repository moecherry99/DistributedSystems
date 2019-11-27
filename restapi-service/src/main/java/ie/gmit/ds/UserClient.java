/*package ie.gmit.ds;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;

import ie.gmit.ds.PasswordServiceGrpc.PasswordServiceBlockingStub;
import ie.gmit.ds.PasswordServiceGrpc.PasswordServiceStub;

import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class UserClient {

	private static final Logger logger = Logger.getLogger(UserClient.class.getName());
	private final ManagedChannel channel;
	private final passwordServiceBlockingStub syncPasswordService;
	private final passwordServiceStub asyncPasswordService;
	private int userId;
	
	private String userPw;
	private ByteString hashedPassword;
	private ByteString salt;
	Scanner in = new Scanner(System.in);

	public void getUserInput() {
		System.out.println("Enter User ID:");
		userId = in.nextInt();
		System.out.println("Enter Password:");
		userPassword = in.next();
	}

	public static void main(String[] args) throws Exception {
		UserClient client = new UserClient("localhost", 50548);
		client.getUserInput();
		try {
			client.hashPassword();
			client.validatePassword();
		} finally

		{
			// Don't stop process, keep alive to receive async response
			Thread.currentThread().join();
		}

	}

	public UserClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		syncPasswordService = PasswordServiceGrpc.newBlockingStub(channel);
		asyncPasswordService = PasswordServiceGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void hashPassword() {
		logger.info("User ID: " + userId + "\nPassword: " + userPw);
		Hash newItem = Hash.newBuilder().setUserId(userId).setPw(userPw).build();
		HashResponse hashItem;
		try {
			hashItem = syncPasswordService.hash(newItem);
			hashedPassword=hashItem.getHashPassword();
			salt=hashItem.getSalt();
			logger.info(hashItem.toString());
		} catch (StatusRuntimeException ex) {
			logger.log(Level.WARNING, "RPC Failed:{0}", ex.getStatus());
			// return
		}
	}

	public void validatePassword() {
		StreamObserver<BoolValue> responseObserver = new StreamObserver<BoolValue>() {

			@Override
			public void onNext(BoolValue value) {
				if (value.getValue()) {
					logger.info("Login validation successful");
				} else {
					logger.info("User ID or PW is incorrect");
				}
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error! " + t.getLocalizedMessage());
			}

			@Override
			public void onCompleted() { // TODO Auto-generated method stub

			}
		};
		try { 
			logger.info("requesting validation");
			asyncPasswordService.validate(Validate.newBuilder().setPassword(userPassword)
					.setHashedPassword(hashedPassword).setSalt(salt).build(), responseObserver);
			logger.info("returning validation");
		} catch (StatusRuntimeException ex) {
			logger.log(Level.WARNING, "RPC failed: {0}", ex.getStatus());
			return;
		}
	}
}
*/
