### Web service api

###### ID:  R-A-PIR-01, R-A-PIR-02
- Request:
```html
GET /area/all
``` 
- Response body:
```JSON
{
  "datas": [
    {
      "uid": 1,
      "name": "A",
      "status": 0,
      "sensorStatus": 0,
      "ltLong": 108.1234765549,
      "ltLat": 10.45679643,
      "rtLong": 108.6471389,
      "rtLat": 10.42317875,
      "lbLong": 109.55887989,
      "lbLat": 10.57895423,
      "rbLong": 109.4563799,
      "rbLat": 11.4568987
    }
  ],
  "model": null
}
```
- Example

** REQ
```html
http://192.168.1.41:8888/api/area/all
```

** RES
```JSON
{
  "datas": [
    {
      "uid": 1,
      "name": "A",
      "status": 0,
      "sensorStatus": 0,
      "ltLong": 108.1234765549,
      "ltLat": 10.45679643,
      "rtLong": 108.6471389,
      "rtLat": 10.42317875,
      "lbLong": 109.55887989,
      "lbLat": 10.57895423,
      "rbLong": 109.4563799,
      "rbLat": 11.4568987
    }
  ],
  "model": null
}
```

###### ID:  R-S-PR-01, R-S-PR-02
- Request:
```html
POST /area/parking

|  param   |  value  | require  |
|----------|---------|----------|
|   car    |  car_id | true     |

``` 
- Response:
```JSON
{
  "value": "0x3",
  "meaning": "ALLOW"
}
```
or
```JSON
{
  "value": "0x4",
  "meaning": "ALLOW"
}
```
- Example

** REQ

```html
http://192.168.1.41:8888/api/area/parking?car=1
```

** RES
```JSON
{
  "value": "0x3",
  "meaning": "ALLOW"
}

```

###### ID: R-S-CS-01, R-S-CS-02, R-S-CS-03
- Request:
```html
POST /car/call

|  param   |   value  |require  |
|----------|----------|---------|
|   car    |  car_id  |  true   |

``` 
- Response:
```JSON
{
  "value": "0x3",
  "meaning": "ALLOW"
}
```
or
```JSON
{
  "value": "0x4",
  "meaning": "ALLOW"
}
```
- Example

** REQ

```html
http://192.168.1.41:8888/api/area/parking?car=1
```

** RES
```html
Response Code: 423

```

###### ID: R-S-APM-03
- device get autonomous car status:
- Request:
```html
GET /car/info/{uid}

|  param   |   value  | require |
|----------|----------|---------|
|   car    |  car_id  |  true   |
``` 
- Response body:
```JSON
{
  "dateTime": "2017-09-06T04:33:10.917Z",
  "lat": 0,
  "lon": 0,
  "status": 0,
  "token": "string",
  "uid": 0
}
```
- Example

** REQ

```html
http://192.168.1.41:8888/api/car/info/1
```

** RES: Response body: 
```JSON
{
  "uid": 1,
  "dateTime": null,
  "token": null,
  "status": 1,
  "lon": 108.9865678995,
  "lat": 10.25678995
}
```

##### When car finish parking/call
```html
POST /move/success

|  param   |  value  | require |
|----------|---------|---------|
|   car    | car_id  |  true   |
```

- Example

** REQ

```html
http://192.168.1.41:8888/api/move/success?car=1
```

** RES
```
Response code: 
200 success
500/400 fail/bad request
```

##### Device send token to sever
```html
POST /car/token

|  param   |  value  | require |
|----------|---------|---------|
|   uid    | car_id  |  true   |
|----------|---------|---------|
|   token  | token   |  true   |
```

- Example

** REQ

```html
http://192.168.1.41:8888/car/token?uid=2&token=3434dfsdf
```

** RES
```
Response code: 
200 success
500/400 fail/bad request
```