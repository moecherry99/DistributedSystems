
/*
package ie.gmit.ds;


import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.BindableService;
import io.grpc.ServerBuilder;

public class Server {
    private io.grpc.Server Server;
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static final int PORT = 50551;

    private void start() throws IOException {
        Server = ServerBuilder.forPort(PORT)
                .addService((BindableService) new PasswordServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + PORT);

    }

    private void stop() {
        if (Server != null) {
            Server.shutdown();
        }
    }


    private void blockUntilShutdown() throws InterruptedException {
        if (Server != null) {
            Server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Server server = new Server();
        server.start();
        server.blockUntilShutdown();
    }
}
*/
