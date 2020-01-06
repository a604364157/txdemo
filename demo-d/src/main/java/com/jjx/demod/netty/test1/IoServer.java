package com.jjx.demod.netty.test1;

import com.jjx.demod.netty.pool.ThreadPoolSingle;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这是一个传统的Socket编程
 * 客户端每两秒给服务端发送一个hello world
 * <p>
 * 这里客户端连接后会生产一个线程并由一个死循环维护,
 * 连接量上来后,就会有非常多的死循环和线程
 * 在实际环境是不可取的
 *
 * 解决这个问题,需要非阻塞,将所有的打印全交给一个线程
 * 见test2
 *
 * @author jiangjx
 */
public class IoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8300);
        ThreadPoolSingle.getSingle().getThreadPool().execute(() -> {
            while (true) {
                try {
                    //阻塞方法获取新连接
                    Socket sk = socket.accept();
                    new Thread(() -> {
                        byte[] data = new byte[1024];
                        try {
                            InputStream is = sk.getInputStream();
                            while (true) {
                                int len;
                                while ((len = is.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
