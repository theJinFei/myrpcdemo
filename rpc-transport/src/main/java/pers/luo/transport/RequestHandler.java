package pers.luo.transport;

import java.io.InputStream;
import java.io.OutputStream;

public interface RequestHandler {
    void onRequest(InputStream inputStream, OutputStream outputStream);
}
