package ir.xako.FCGI;

import ir.xako.FCGI.Core.FCGIEngine;
import ir.xako.FCGI.Request.*;
import ir.xako.FCGI.Response.FCGIResponse;
import ir.xako.FCGI.Utils.*;
import java.util.Map;

/**
 * Main FCGIClient class for interacting with the framework.
 *
 * @author Sako
 * @since 2021
 */

public class FCGIClient {

    private final int reqID;
    private final Map<String,String> envs;
    private final String postBody;
    private final FCGIEngine engine;

    public FCGIClient(Map<String,String> envs, String postBody){
        this.reqID = Utils.getID();
        this.envs = envs;
        this.postBody = postBody;
        //this.engine = new FCGIEngine(Configs.host,Configs.port,Configs.timeOut);
        this.engine = new FCGIEngine("127.0.0.1",9000,3000);
    }

    public void run(){


        FCGIBeginRequestBody brb = FCGIBeginRequestBody.getInstance(FCGIConstants.FCGI_ROLE_RESPONSER,0);
        FCGIRequestHeader brbh = new FCGIRequestHeader(1,FCGIConstants.FCGI_BEGIN_REQUEST,reqID,brb.getLength());
        engine.exec(new FCGIRequest(brbh,brb));

        FCGIParamsBody params = FCGIParamsBody.getInstance(envs);
        FCGIRequestHeader prmh = new FCGIRequestHeader(1,FCGIConstants.FCGI_PARAMS,reqID, params.getLength());
        engine.exec(new FCGIRequest(prmh,params));

        FCGIParamsBody endParams = FCGIParamsBody.getInstance(null);
        FCGIRequestHeader endParamsH = new FCGIRequestHeader(1,FCGIConstants.FCGI_PARAMS,reqID, endParams.getLength());
        engine.exec(new FCGIRequest(endParamsH,endParams));

        if (postBody != null && postBody.length() > 0){
            FCGIPostBody post = FCGIPostBody.getInstance(postBody);
            FCGIRequestHeader postH = new FCGIRequestHeader(1,FCGIConstants.FCGI_STDIN,reqID,post.getLength());
            engine.exec(new FCGIRequest(postH,post));

            FCGIPostBody endPost = FCGIPostBody.getInstance(null);
            FCGIRequestHeader endPostH = new FCGIRequestHeader(1,FCGIConstants.FCGI_STDIN,reqID,endPost.getLength());
            engine.exec(new FCGIRequest(endPostH,endPost));
        }
    }

    public FCGIResponse getResponse(){
        return engine.listenForResponse(reqID);
    }
}
