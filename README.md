
#Java FastCGI Client

#####A `Java` FastCGI Client for accessing `FastCGI` server.


AUTHOR
================

Sako


How to use :
========================
Fist you need to start the FastCGI server. For PHP, you can use PHP-FPM. Then edit Config.java in the Utils package. This class contains
the address of the FastCGI server and some other configurations. After editing Configs.java you can use the below syntax :

        //Post body
        String content = "name=john&lastname=snow&address=winterfell";
        
        //URI which is sent in the Http header.
        String uri = "/example.php";

        //Environment varibales
        Map<String, String> envs = new HashMap<String, String>();
        String documentRoot = "/Users/baidu/php_workspace";
        params.put("GATEWAY_INTERFACE", "FastCGI/1.0");
        params.put("REQUEST_METHOD", "POST");
        params.put("SCRIPT_FILENAME", documentRoot + uri);
        params.put("SCRIPT_NAME", uri);
        params.put("QUERY_STRING", "");
        params.put("REQUEST_URI", uri);
        params.put("DOCUMENT_ROOT", documentRoot);
        params.put("REMOTE_ADDR", "127.0.0.1");
        params.put("REMOTE_PORT", "9985");
        params.put("SERVER_ADDR", "127.0.0.1");
        params.put("SERVER_NAME", "localhost");
        params.put("SERVER_PORT", "80");
        params.put("SERVER_PROTOCOL", "HTTP/1.1");
        params.put("CONTENT_TYPE", "application/x-www-form-urlencoded");
        params.put("CONTENT_LENGTH", content.length() + "");

        //Creating a new client.
        FCGIClient client = new FCGIClient(params,content);

        //Sending request
        client.run();
        
        //Receving response
        FCGIResponse res = client.getResponse();

        //Staus of the response (see FCGIConstants.java)
        int status = res.status

        //Received content from FastCGI server (can be empty)
        String content = res.getContent();

        //Error message received from FastCGI server (can be empty if no error occurred)
        String error = res.getError();