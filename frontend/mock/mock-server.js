const express = require('express');
const path = require('path');
const fs = require('fs');
let bodyParser = require('body-parser');
let morgan = require('morgan')

const app = express()

const port = 3000

const contextPath = '/sample-context';
const JSESSIONID = 'JSESSIONID'
const jwtTokenName = 'JwtToken'
const sampleTokenName = 'sampleToken'
const dataDir = __dirname + '/mock-data';

setup = function (app) {
  app.use(bodyParser.json());
  app.use(morgan('dev'))

  app.use(function (error, req, res, next) {
    let fullUrl = req.protocol + '://' + req.get('host') + req.originalUrl;
  });

  app.post(contextPath + '/login', (req, res) => {
    console.log("LOGIN called")
    let tokenPath = path.join(dataDir, 'jwtToken.json');
    let jwtToken = fs.readFileSync(tokenPath, 'utf8');
    let base64Value = base64(jwtToken);
    res.cookie(jwtTokenName, base64Value, {maxAge: 90000000, httpOnly: true, path: contextPath});

    tokenPath = path.join(dataDir, 'sampleToken.json');
    let sampleToken = fs.readFileSync(tokenPath, 'utf8');
    base64Value = base64(sampleToken);
    res.cookie(jwtTokenName, base64Value, {maxAge: 90000000, httpOnly: true, path: contextPath});
    res.cookie(sampleTokenName, base64Value, {maxAge: 90000000, httpOnly: false, path: contextPath});
    res.cookie(JSESSIONID, null, {maxAge: 90000000, httpOnly: true, path: contextPath});
    res.redirect(302, contextPath);
  });

  app.post(contextPath + '/logout', (req, res) => {
    res.cookie(jwtTokenName, "", {maxAge: -1, httpOnly: true, path: contextPath});
    res.cookie(sampleTokenName, "", {maxAge: -1, httpOnly: false, path: contextPath});
    res.cookie(JSESSIONID, "", {maxAge: -1, httpOnly: true, path: contextPath});
    res.send();
  });

  app.get(contextPath + '/api/sample', (req, res) => {
    let query = req.query.query;
    let pageSize = req.query.pageSize;
    let page = req.query.page;

    let file = path.join(dataDir, 'sample.json');
    let json = fs.readFileSync(file, "utf8");
    const samples = JSON.parse(json)
    let result = filter(samples, query, page, pageSize);
    res.send(result);

  });

  app.get(contextPath + '/api/sample/:id', (req, res) => {

    // set a timeout to create an artificial delay in response
    setTimeout(() => {
      let file = path.join(dataDir, 'samples.json');
      res.send(file);
    }, 1000);
  });
}

setup(app);

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
});

function base64(str) {
  let buff = new Buffer(str);
  let base64Value = buff.toString('base64');
  return base64Value;
}

function filter(samples, query, page, pageSize) {
  if (query == null) query = '';

  query = query.toLowerCase();
  let result = samples.filter(option => option.description.toLowerCase().indexOf(query) >= 0);

  result = paginate(result, page, pageSize);
  return result;
}

function paginate(array, page, pageSize) {
  page = parseInt(page);
  pageSize = parseInt(pageSize);
  page = isNaN(page) ? 0 : page;

  pageSize = isNaN(pageSize) ? 20 : pageSize;
  const indexMin = page * pageSize;
  const indexMax = indexMin + pageSize;


  const paginatedArray = array.filter(
    (x, index) => index >= indexMin && index < indexMax
  )

  return paginatedArray;
}

