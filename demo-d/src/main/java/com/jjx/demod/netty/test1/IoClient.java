package com.jjx.demod.netty.test1;

import com.jjx.demod.netty.pool.ThreadPoolSingle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author jiangjx
 */
public class IoClient {

    public static void main(String[] args) {
        ThreadPoolSingle.getSingle().getThreadPool().execute(()->{
            try {
                Socket socket = new Socket("127.0.0.1", 8300);
                while (true) {
                    OutputStream os = socket.getOutputStream();
                    os.write((new Date()+" hello world").getBytes());
                    os.flush();
                    Thread.sleep(2000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
