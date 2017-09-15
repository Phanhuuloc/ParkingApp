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
  "points": [
    {
      "x": 10.802292797113738,
      "y": 106.63976389914752
    }
  ],
  "area": {
    "uid": 1,
    "name": "B18",
    "status": 0,
    "sensorStatus": 0,
    "ltLong": 106.63966130465269,
    "ltLat": 10.802160075099328,
    "rtLong": 106.6396824270487,
    "rtLat": 10.802155793743044,
    "lbLong": 106.63965459913015,
    "lbLat": 10.802135045630981,
    "rbLong": 106.63967672735453,
    "rbLat": 10.802131093609468
  },
  "value": "0x3",
  "meaning": "ALLOW"
}
```
or
```JSON
{
  "points": null,
  "area":null,
  "value": "0x4",
  "meaning": "DENY"
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
  "points": [
    {
      "x": 10.802292797113738,
      "y": 106.63976389914752
    },
    {
      "x": 10.802239115511995,
      "y": 106.63975484669209
    },
    {
      "x": 10.802200912648473,
      "y": 106.63974680006503
    },
    {
      "x": 10.802153159062229,
      "y": 106.63973808288574
    },
    {
      "x": 10.802102770787128,
      "y": 106.63972534239294
    },
    {
      "x": 10.802109357490234,
      "y": 106.63969315588474
    },
    {
      "x": 10.802117920204061,
      "y": 106.63966197520494
    },
    {
      "x": 10.802146572360078,
      "y": 106.63966935127974
    }
  ],
  "area": {
    "uid": 1,
    "name": "B18",
    "status": 0,
    "sensorStatus": 0,
    "ltLong": 106.63966130465269,
    "ltLat": 10.802160075099328,
    "rtLong": 106.6396824270487,
    "rtLat": 10.802155793743044,
    "lbLong": 106.63965459913015,
    "lbLat": 10.802135045630981,
    "rbLong": 106.63967672735453,
    "rbLat": 10.802131093609468
  },
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
  "points": [
    {
      "x": 10.802146572360078,
      "y": 106.63966935127974
    }
  ],
  "area": null,
  "value": "0x3",
  "meaning": "ALLOW"
}
```
or
```JSON
{
  "points": null,
  "area":null,
  "value": "0x4",
  "meaning": "DENY"
}
```
- Example

** REQ

```html
http://192.168.1.41:8888/api/car/call?car=1
```

** RES
```JSON
{
  "points": [
    {
      "x": 10.802146572360078,
      "y": 106.63966935127974
    },
    {
      "x": 10.802117920204061,
      "y": 106.63966197520494
    },
    {
      "x": 10.802109357490234,
      "y": 106.63969315588474
    },
    {
      "x": 10.802102770787128,
      "y": 106.63972534239294
    },
    {
      "x": 10.802153159062229,
      "y": 106.63973808288574
    },
    {
      "x": 10.802200912648473,
      "y": 106.63974680006503
    },
    {
      "x": 10.802239115511995,
      "y": 106.63975484669209
    },
    {
      "x": 10.802292797113738,
      "y": 106.63976389914752
    }
  ],
  "area": null,
  "value": "0x3",
  "meaning": "ALLOW"
}

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