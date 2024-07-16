const express = require('express');
const axios = require('axios');
const path = require('path');
const bodyParser = require('body-parser');
const app = express();
const backend_url = process.env.BACKEND_URL ? process.env.BACKEND_URL : 'http://localhost:8080';

// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

// Middleware to parse JSON bodies
app.use(bodyParser.json());

// Middleware to parse URL-encoded bodies
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/health',(req,res)=>{
  res.send('Hello World')
})

app.get('/score', (req, res) => {
  const userId = req.query.userId;
  console.log(`Getting ${userId} score - ${Date.now()}`)
  axios.get(`${backend_url}/read/score?userId=${userId}`)
    .then(response => {
      res.json(response.data);
    })
    .catch(error => {
      console.error('Error fetching leaderboard data:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    });
})

app.get('/rank', (req, res) => {
  const userId = req.query.userId;
  console.log(`Getting ${userId} rank - ${Date.now()}`)
  axios.get(`${backend_url}/read/rank?userId=${userId}`)
    .then(response => {
      // Send the leaderboard data received from the external API as JSON response
      res.json(response.data);
    })
    .catch(error => {
      // Handle errors if the request fails
      console.error('Error fetching leaderboard data:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    });
});

app.get('/leaderboard', (req, res) => {
  // Assume you have an array of leaderboard data
  console.log(`Getting leaderboard - ${Date.now()}`)
  axios.get(`${backend_url}/read/leaderboard`)
    .then(response => {
      // Send the leaderboard data received from the external API as JSON response
      res.json(response.data);
    })
    .catch(error => {
      // Handle errors if the request fails
      console.error('Error fetching leaderboard data:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    });
});

app.post('/update', async (req, res) => {
  console.log(`Updating score - ${Date.now()}`)
  try {
    const headers = req.headers;
    const body = req.body;

    // Make HTTP request to another server
    const response = await axios.post(`${backend_url}/write/score`, body, {
      headers: headers
    });

    res.send(response.data);
  } catch (error) {
    console.error('Error:', error.message);
    res.status(500).send('Internal Server Error');
  }
})

app.get('/unique', (req, res) => {
  const userId = req.query.userId;
  console.log(`Checking ${userId} uniqueness - ${Date.now()}`)
  axios.get(`${backend_url}/read/unique?userId=${userId}`)
    .then(response => {
      // Send the leaderboard data received from the external API as JSON response
      res.json(response.data);
    })
    .catch(error => {
      // Handle errors if the request fails
      console.error('Error validating userId data:', error);
      res.status(500).json({ error: 'Internal Server Error' });
    });
})


app.post('/login', async (req, res) => {
  const { userId, password } = req.body;
  console.log(`${userId} trying to login - ${Date.now()}`)
  const anotherServerUrl = `${backend_url}/read/authenticate`;
  try {
    // Make a POST request to the other server
    const response = await axios.post(anotherServerUrl, {
      userId,
      password
    });

    // If successful, send the response from the other server as the response for this request
    res.send(response.data);
  } catch (error) {
    // If an error occurs during the request to the other server, handle it
    console.error('login Error calling the other server:', error.message);
    res.status(500).send('An error occurred while logging in');
  }
});

app.post('/signup', async (req, res) => {
  const { userId, password } = req.body;
  console.log(`New user ${userId} trying to signup - ${Date.now()}`)
  const anotherServerUrl = `${backend_url}/write/signup`;
  try {
    // Make a POST request to the other server
    const response = await axios.post(anotherServerUrl, {
      userId,
      password
    });
    // If successful, send the response from the other server as the response for this request
    res.send(response.data);
  } catch (error) {
    // If an error occurs during the request to the other server, handle it
    console.error('Signup Error calling the other server:', error.message);
    res.status(500).send('An error occurred while logging in');
  }
});


// Start the server on port 3000
app.listen(3000, () => {
  console.log('Server is listening on port 3000');
});
