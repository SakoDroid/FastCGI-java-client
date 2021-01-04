package ir.xako.FCGI.Response;

import java.io.InputStream;

/**
 * @author Sako
 * @since 2021
 */

public class FCGIEndRequestBody {

    private final int appStatus;
    private final int protocolStatus;

    public FCGIEndRequestBody(int length, InputStream in){
        byte[] body = new byte[length];
        try{
            in.read(body);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        this.appStatus = (body[0] << 24) + (body[1] << 16) + (body[2] << 8) + body[3];
        this.protocolStatus = body[4];
    }

    public int getAppStatus(){
        return this.appStatus;
    }

    public int getProtocolStatus(){
        return this.protocolStatus;
    }
}
