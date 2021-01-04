package ir.xako.FCGI.Request;

import ir.xako.FCGI.Utils.Configs;

import java.io.OutputStream;

/**
 * @author Sako
 * @since 2021
 */

public class FCGIRequest {

    private final FCGIRequestHeader header;
    private final FCGIRequestComponent body;

    public FCGIRequest(FCGIRequestHeader header, FCGIRequestComponent body){
        if (header != null && body != null){
            this.header = header;
            this.body = body;
        }else throw new NullPointerException("Header or body can not be null");
    }

    public void send(OutputStream os){
        try{
            header.send(os);
            body.send(os);
            byte[] paddingData = Configs.padding;
            if (paddingData != null && paddingData.length > 0) os.write(paddingData);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
