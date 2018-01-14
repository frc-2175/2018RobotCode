import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class NetConsoleReceiver {

    private static Thread listener;

    public static DatagramSocket makeRecvSocket() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(null);
            socket.setReuseAddress(true);
            socket.bind(new InetSocketAddress(6666));
        } catch (final SocketException e) {
            e.printStackTrace();
            if (socket != null) {
                socket.close();
            }
            return null;
        }
        return socket;
    }

    public static byte[] getPacket(final DatagramSocket socket,
            final DatagramPacket buf) {
        try {
            socket.receive(buf);
        } catch (final IOException e) {
            return null;
        }
        final byte[] ret = new byte[buf.getLength()];
        System.arraycopy(buf.getData(), 0, ret, 0, ret.length);
        return ret;
    }

    public static void main(final String[] args)
            throws IOException, InterruptedException {
        listener = new Thread((Runnable) () -> {
            final DatagramSocket socket = makeRecvSocket();
            if (socket == null) {
                return;
            }
            final byte[] buf = new byte[4096];
            final DatagramPacket datagram = new DatagramPacket(buf, buf.length);
            while (!Thread.interrupted()) {
                final byte[] s = getPacket(socket, datagram);
                if (s != null) {
                    final String msg = new String(s);
                    System.out.print(msg);

                    if (msg.indexOf(
                            "Robot program successfully initialized!") > -1) {
                        exit(0, "Robot program started successfully!");
                    }
                    if (msg.indexOf(
                            "---> The startCompetition() method (or methods called by it) should have handled the exception above.") > -1) {
                        exit(1, "Robot program failed to start!");
                    }
                }
            }
            socket.close();
        }, "Netconsole-Listener");
        listener.start();

        // Don't immediately exit
        listener.join();
    }

    public static void exit(final int code, final String message) {
        listener.interrupt();
        System.out.println();
        System.out.println(message);
        System.exit(code);
    }
}
