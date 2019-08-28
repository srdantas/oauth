# OAuth Server
Using spring-cloud-starter-oauth2 for implements this.

I can't make it a reactive project because the configuration is very hard to make and the objective of this is maker vary hard for start.

### Dependencies
- MongoDB

### Start MongoDB
The yml configuration has any authentication mode for create mongo connections.
```bash
docker run -d -p 27017:27017 mongo:latest
```
This is a sufficient for start application.

### Request a token
For get a token the application that make a request is necessary meet the `client-id` and `secret-id`. It is from yml of application.

Which this keys in hands, make a base64 encoder with pattern `client-id:secret-id` and send it in header request with pattern `Basic <encode>`.
The username and password is send in query parameter with the `grant_type` [password for login and refresh_token for update access_token].

Like it:
```bash
curl -X POST \
  'http://localhost:8082/oauth/token?grant_type=password&username=renato.dantas&password=1234' \
  -H 'Authorization: Basic ZGF0YXNjaWVuY2VuZXQ6MTIzNA=='
``` 
it response:
```bash
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjY5NTE5NTUsInVzZXJfbmFtZSI6InJlbmF0by5kYW50YXMiLCJhdXRob3JpdGllcyI6WyJkZjZmMmQxZC01NDc3LTQzNWItYjA0Ni0xMTgxOTg3NzA2Y2EiXSwianRpIjoiOGIyMTQxNmQtODc2MS00NzVmLWExOGEtODM0ZWIyNjAzZTg4IiwiY2xpZW50X2lkIjoiZGF0YXNjaWVuY2VuZXQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.qwmKfvALbsofx5n2vhsiVHL24EEXSP3v2hdpoXpi7Mk",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJyZW5hdG8uZGFudGFzIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6IjhiMjE0MTZkLTg3NjEtNDc1Zi1hMThhLTgzNGViMjYwM2U4OCIsImV4cCI6MTU2OTU0MjE1NSwiYXV0aG9yaXRpZXMiOlsiZGY2ZjJkMWQtNTQ3Ny00MzViLWIwNDYtMTE4MTk4NzcwNmNhIl0sImp0aSI6ImM4ZDg3ZGRmLTY3MDQtNDdmMi04YWEzLTk4NGI2ZTBlYmM4YSIsImNsaWVudF9pZCI6ImRhdGFzY2llbmNlbmV0In0.XdsUKbBaWANc5FqvtfOQvvYjeKKSMuRGbkr1HX7bpfU",
    "expires_in": 1799,
    "scope": "read write",
    "jti": "8b21416d-8761-475f-a18a-834eb2603e88"
}
```

### Refresh token
For refresh token not is necessary using the username and password, for it make this:
```bash
curl -X POST \
  'http://localhost:8082/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJyZW5hdG8uZGFudGFzIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6IjRkMDM5ZGQzLTExNTAtNGNiMi1hZDE1LWUyMDVlN2U1OTk4MiIsImV4cCI6MTU2OTU0MjA5NiwiYXV0aG9yaXRpZXMiOlsiZGY2ZjJkMWQtNTQ3Ny00MzViLWIwNDYtMTE4MTk4NzcwNmNhIl0sImp0aSI6IjUyZmVkNDgyLTc5MmItNGNlNS1iMmU1LWNlYzQxM2RkZjgyMSIsImNsaWVudF9pZCI6ImRhdGFzY2llbmNlbmV0In0.R-jksrqNXwjcKo_CmrB6YFU47_Q2W4lj4vxsnNz58jc' \
  -H 'Authorization: Basic ZGF0YXNjaWVuY2VuZXQ6MTIzNA=='
``` 
Like the `refresh_token` here is a refresh_token received in get first access token.
With the response:
```bash
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjY5NTE5ODQsInVzZXJfbmFtZSI6InJlbmF0by5kYW50YXMiLCJhdXRob3JpdGllcyI6WyJkZjZmMmQxZC01NDc3LTQzNWItYjA0Ni0xMTgxOTg3NzA2Y2EiXSwianRpIjoiMmMzNjRhN2MtNzJiOS00MDJjLTlmM2YtZDI0YmE2YzRhZWFiIiwiY2xpZW50X2lkIjoiZGF0YXNjaWVuY2VuZXQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.MNDeNDGlIKNNLbvWFVTiRlfT8Qpn-hyK96Z4TP__7Oo",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJyZW5hdG8uZGFudGFzIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6IjJjMzY0YTdjLTcyYjktNDAyYy05ZjNmLWQyNGJhNmM0YWVhYiIsImV4cCI6MTU2OTU0MjA5NiwiYXV0aG9yaXRpZXMiOlsiZGY2ZjJkMWQtNTQ3Ny00MzViLWIwNDYtMTE4MTk4NzcwNmNhIl0sImp0aSI6IjUyZmVkNDgyLTc5MmItNGNlNS1iMmU1LWNlYzQxM2RkZjgyMSIsImNsaWVudF9pZCI6ImRhdGFzY2llbmNlbmV0In0.06JZ8Gc5_mzIZJVIqb19dHTHMvo4NJiPLiKXWGL6a0w",
    "expires_in": 1799,
    "scope": "read write",
    "jti": "2c364a7c-72b9-402c-9f3f-d24ba6c4aeab"
}
```  