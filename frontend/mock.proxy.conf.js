const PROXY_CONFIG = {

  "/sample-context/": {
    "target": "http://localhost:3000/",
    "secure": false,
    "logLevel": "debug",
    "bypass": function (req, res, proxyOptions) {
      console.log("mock.proxy.request url: ", req.url)

      if (req.url.endsWith('/login')) {
        console.log("forward login request: to localhost:3000")
        return;
      }

      if (req.xhr || req.headers.accept.indexOf('json') > -1) {
        // forward  json requests to the server. This might need tweaking to include all valid requests
        console.log("forward: to localhost:3000")
        return;

      } else {
        console.log("bypassed: serve the url from angular server at localhost:4200")
        return req.url;
      }
    }
  },

  // "/claims-manager/logout": {
  //   "target": "http://localhost:3000/",
  //   "secure": false,
  //   "logLevel": "debug"
  // },
  //
  // "/claims-manager/api": {
  //   "target": "http://localhost:3000/",
  //   "secure": false,
  //   //pathRewrite: {'^/claims-manager/api' : ''}, // remove claims-manager when forwarding to remote host
  //   "logLevel": "debug"
  // },
  //
  // "/requirements-api": {
  //   "target": "http://localhost:3000/claims-manager/",
  //   "secure": false,
  //   "logLevel": "debug"
  // },
  //
  // "/claims-api/*": {
  //   "target": "http://localhost:3000/claims-manager/",
  //   "secure": false,
  //   "logLevel": "debug"
  // },

  // "/risk-party-api/*": {
  //   "target": "http://localhost:3000/claims-manager/",
  //   "secure": false,
  //   "logLevel": "debug",
  //
  //   // "bypass": function (req, res, proxyOptions) {
  //   //   if (req.url.endsWith(".html")) {
  //   //     return true;
  //   //   }
  //   //   if (req.url.endsWith("/")) {
  //   //     return true;
  //   //   }
  //   //
  //   //   if (req.url.endsWith(".css")) {
  //   //     return true;
  //   //   }
  //   //
  //   //   if (req.url.endsWith(".js")) {
  //   //     return true;
  //   //   }
  //   //   return false;
  //   // }
  // }
}

module.exports = PROXY_CONFIG;
