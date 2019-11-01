package ie.gmit.ds;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class PasswordServer {

	// static variables
	private Server grpcServer;
	private static final int PORT = 50548;
	private static final Logger logger = Logger.getLogger(PasswordServer.class.getName());


	// main
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// runs password server
		final PasswordServer passwordServer = new PasswordServer();
		passwordServer.start();
		passwordServer.blockUntilShutdown();
	}

	// Starts server on port listed in static variable
	private void start() throws IOException {
		grpcServer = ServerBuilder.forPort(PORT).addService(new PasswordServiceImpl()).build().start();
		logger.info("Server listening on port :  " + PORT);

	}

	// Stops server
	private void stop() {
		if (grpcServer != null) {
			
			grpcServer.shutdown();
		}
	}

	// awaiting program termination
	private void blockUntilShutdown() throws InterruptedException {
		if (grpcServer != null) {
			
			grpcServer.awaitTermination();
		}
	}
}

