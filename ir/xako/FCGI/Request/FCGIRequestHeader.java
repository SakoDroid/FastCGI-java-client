package ir.xako.FCGI.Request;

import ir.xako.FCGI.Utils.Configs;

/**
 * @author Sako
 * @since 2021
 */

public class FCGIRequestHeader extends FCGIRequestComponent {

    private final int version;
    private final int type;
    private final int reqIdB1;
    private final int reqIdB0;
    private final int contLenB1;
    private final int contLenB0;
    private final int paddingLength;
    private final int reserved;

    public FCGIRequestHeader(int version, int type, int requestID, int contentLength){
        this.version = (byte) version;
        this.type = (byte) type;
        this.reqIdB1 = (byte) ((requestID >> 8) & 0xFF);
        this.reqIdB0 = (byte) (requestID & 0xFF);
        this.contLenB1 = (byte) ((contentLength >> 8) & 0xFF);
        this.contLenB0 = (byte) (contentLength & 0xFF);
        byte[] paddingData = Configs.padding;
        if (paddingData != null && paddingData.length > 0)
            this.paddingLength = paddingData.length;
        else this.paddingLength = 0;
        this.reserved = 0;
        this.makeReadyForSend();
    }


    @Override
    public void makeReadyForSend(){
        try{
            out.write(this.version);
            out.write(this.type);
            out.write(this.reqIdB1);
            out.write(this.reqIdB0);
            out.write(this.contLenB1);
            out.write(this.contLenB0);
            out.write(this.paddingLength);
            out.write(this.reserved);
            this.isReady = true;
        }catch (Exception ex) {

        }
    }

}
