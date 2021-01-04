package ir.xako.FCGI.Core;

import ir.xako.FCGI.Request.FCGIRequest;
import ir.xako.FCGI.Response.FCGIResponse;
import java.net.Socket;
import java.io.*;

public class FCGIEngine {

    private Socket sock;
    private OutputStream out;
    private InputStream in;

    public FCGIEngine(String host, int port, int timeout){
        try{
            this.sock = new Socket(host,port);
            this.sock.setSoTimeout(timeout);
            this.out = this.sock.getOutputStream();
            this.in = this.sock.getInputStream();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exec(FCGIRequest request){
        request.send(this.out);
    }

    public FCGIResponse listenForResponse(int reqID){
        FCGIResponse res = new FCGIResponse(this.in,reqID);
        this.closeConnection();
        return res;
    }

    public void closeConnection(){
        try{
            this.sock.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
