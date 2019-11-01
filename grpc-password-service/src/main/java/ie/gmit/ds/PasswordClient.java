package ie.gmit.ds;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;


 // Basic client set up
 // Reference to code :  https://github.com/john-french/distributed-systems-labs/tree/master/grpc-async-inventory

public class PasswordClient {

	// constants
	private static final Logger LOGGER = Logger.getLogger(PasswordClient.class.getName());// PasswordClient
	private static final String LINESEPARATOR = System.lineSeparator(); // automatic new lines instead of \n
	private static final String HOST = "localhost";
	private static final int PORT = 50568;	
	private final PasswordServiceGrpc.PasswordServiceBlockingStub syncPasswordService; 
	private final PasswordServiceGrpc.PasswordServiceStub asyncPasswordService; 
	private final ManagedChannel channel;

	// Scanner
	private Scanner sc = new Scanner(System.in);

	// private constructor
	private String password;
	private ByteString hashedPassword;
	private ByteString salt;
	private int userId;

	//main
	public static void main(String[] args) throws Exception {
		PasswordClient client = new PasswordClient(HOST, PORT);
		try {
			LOGGER.info("\nUser input required to be hashed : ");
			
			// Build Hash
			HashRequest req = client.buildHashRequest();
			
			LOGGER.info(LINESEPARATOR + "User input received #1." + LINESEPARATOR);

			// Send Hash
			client.sendHashRequest(req);
			
			LOGGER.info(LINESEPARATOR + "Re-enter details entered earlier : ");
			
			// Send ValidationRequest
            client.sendValidationRequest();
            
			LOGGER.info(LINESEPARATOR + "User input received #2.");

			// Log for user confirmation
			LOGGER.info(LINESEPARATOR + "User ID: "
			+ client.getUserId() + LINESEPARATOR + "Password: "
			+ client.getPassword() + LINESEPARATOR + "Hashed PW: " 
			+ client.getHashedPassword() + LINESEPARATOR + "Salted Code: " 
			+ client.getSalt());
			

		} finally {
            // thread running
            Thread.currentThread().join();
        }
	}

	public PasswordClient(String host, int port) {
		this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		// stubs for simulation
		syncPasswordService = PasswordServiceGrpc.newBlockingStub(channel);
		asyncPasswordService = PasswordServiceGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		// 7 second timeout if client can't find server
		channel.shutdown().awaitTermination(7, TimeUnit.SECONDS);
	}

	// User Input
	public void getUserInput() {
		System.out.println("Enter User ID:");
		userId = sc.nextInt();
		
		System.out.println("Enter Password:");
		password = sc.next();
	}

	// Hash
	public HashRequest buildHashRequest() {
		getUserInput();

		LOGGER.info("\nHashing in progress");

		// Build Hash
		HashRequest hashRequest = HashRequest.newBuilder().setUserId(userId).setPassword(password).build();

		LOGGER.info("\nRequest hashed" + LINESEPARATOR);

		return hashRequest;
	}

	// send Hash
	public void sendHashRequest(HashRequest req) {

		HashResponse hashResponse;

		try {
			LOGGER.info("\nSending Hashed Input");

			// Build HashResponse
			hashResponse = syncPasswordService.hash(req);

			LOGGER.info("\nPassword hashed! Hash received." + LINESEPARATOR);

			// Store the password hash and the salt
			setHashedPassword(hashResponse.getHashedPassword());
			salt = hashResponse.getSalt();
		} catch (StatusRuntimeException e) {
			LOGGER.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
			return;
		}
	}

	public void sendValidationRequest() {
		// Boolean for true or false
		StreamObserver<BoolValue> responseObserver = new StreamObserver<BoolValue>() {
			
			@Override
			public void onNext(BoolValue value) {
				if (value.getValue()) {
					System.out.println("Login validation successful.");
				} else {
					System.out.println("User ID or PW is incorrect.");
				}
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error! " + t.getLocalizedMessage());
			}

			@Override
			public void onCompleted() {
				System.exit(0);
			}
		};
		
		try {
			getUserInput();
			asyncPasswordService.validate(ValidationRequest.newBuilder().setPassword(password)
					.setHashedPassword(hashedPassword).setSalt(salt).build(), responseObserver);
		} catch (StatusRuntimeException ex) {
			LOGGER.log(Level.WARNING, "RPC failed: {0}", ex.getStatus());
			return;
		}
	}

	// Getters and Setters
	public ByteString getHashedPassword() {
		return hashedPassword;
	}
	
	//uId
	public int getUserId() {
		return userId;
	}

	//setUId
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	//setHashedPw
	public void setHashedPassword(ByteString hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	//byteString
	public ByteString getSalt() {
		return salt;
	}

	//setSalt
	public void setSalt(ByteString salt) {
		this.salt = salt;
	}

	//getPw
	public String getPassword() {
		return password;
	}

	//setPw
	public void setPassword(String password) {
		this.password = password;
	}

}

