package ir.xako.FCGI.Request;


/**
 * @author Sako
 * @since 2021
 */

public class FCGIPostBody extends FCGIRequestComponent {

    private FCGIPostBody(byte[] data){
        try{
            if (data.length > 0) out.write(data);
            this.makeReadyForSend();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static FCGIPostBody getInstance(String body){
        if (body != null) return new FCGIPostBody(body.getBytes());
        else return new FCGIPostBody(new byte[]{});
    }

    @Override
    protected void makeReadyForSend(){
        this.isReady = true;
    }
}
