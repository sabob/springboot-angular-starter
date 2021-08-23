const PROXY_CONFIG = {

  "/sample-context/": {
    "target": "http://localhost:8080/sample-context/", // the hostname of the Spring Boot server
    "secure": false,
    pathRewrite: {'^/sample-context': ''}, // remove the context-path (sample-context) from the url that is passed onto the server
    "logLevel": "debug",
    "bypass": function (req, res, proxyOptions) {
      if (req.xhr || req.headers.accept.indexOf('json') > -1) {
        // forward  json requests to the server. This might need tweaking to include all valid requests
        console.log("forward: to server at localhost:3000")
      } else {
        console.log("bypassed: serve the url from angular server at localhost:4200")
        return req.url;
      }
      //return req.url;
    }
  }
}

module.exports = PROXY_CONFIG;
