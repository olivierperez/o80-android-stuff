package fr.o80.androidstuff.net;

import android.support.annotation.Keep;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * How to use : https://github.com/square/okhttp/blob/e5c84e1aef9572adb493197c1b6c4e882aca085b/okhttp/src/main/java/okhttp3/OkHttpClient.java#L691
 */
public class TLSSocketFactory extends SSLSocketFactory {

    // DO NOT RENAME THE 'delegate' FIELD OTHERWISE NETWORK WILL BREAK
    // The use of the @Keep annotation will make the class survives refactor when using proguard (better than adding a proguard configuration file)
    @Keep
    private SSLSocketFactory delegate;

    /**
     * Constructor
     *
     * @throws KeyManagementException exception
     * @throws NoSuchAlgorithmException exception
     */
    public TLSSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);
        this.delegate = context.getSocketFactory();
    }

    public TLSSocketFactory(SSLSocketFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[] {"TLSv1", "TLSv1.1", "TLSv1.2"});
        }
        return socket;
    }
}
