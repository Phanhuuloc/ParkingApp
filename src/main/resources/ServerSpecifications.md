### Web service api

##### R-S-PIR-01
```
http://192.168.1.41:9999/api/parking/area?uid=2becfc51-b5a8-4fab-8f38-74b6265265d4
```
- When Smart phone application requested parking area geometry information, Management server transmits parking area geometry information that stored in database
- Parking area geometry information has GPS coordinates of parking areas.

##### R-S-PIR-02
```
http://192.168.1.41:9999/api/parking/area?uid=2becfc51-b5a8-4fab-8f38-74b6265265d4
```
- When Smart phone application requested parking area status information, Management server transmits parking area status information that stored in database.
- Parking area status information has status of parking areas.(Empty or Full)

##### R-S-PR-01
```
http://192.168.1.41:9999/api/area/permission?uid=97cf7559-29e0-4b6b-b2bf-3b9d7c067c16
```
- Management server check whether or not a parking request is possible and transmit response
- Parking request include parking area information as user selected
- If user selected parking area is empty  =>  permission.
- If user selected parking area is full  => deny
- In case of parking request was permitted, execute R-S-PR-02  

##### R-S-PR-02
```
http://192.168.1.41:9999/api/area/car/move?car=8ac00514-b2a9-4133-b01a-d3e3b8b766a5&a=7a9276fb-295f-4c1a-8cb1-e914e9c09eb1
```
- In case of parking request was permitted, management server transmit parking location information (GPS coordinate) included in parking request to autonomous car.
- Parking location information is GPS coordinate of parking area as user selected in smart phone application.

##### R-S-PIU-01