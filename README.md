# file service
file system that managing upload/download files


## Version: 1.0.0

### /spaces

#### POST
##### Summary:

create a space

##### Responses

| Code | Description |
| ---- | ----------- |
| 201 | Created |
| 400 | bad request |
| 404 | not found |
| 500 | Internal Server Error |

### /folders

#### POST
##### Summary:

create a folder

##### Responses

| Code | Description |
| ---- | ----------- |
| 201 | Created |
| 400 | bad request |
| 404 | not found |
| 500 | Internal Server Error |

### /files

#### POST
##### Summary:

create a file

##### Responses

| Code | Description |
| ---- | ----------- |
| 201 | Created |
| 400 | bad request |
| 404 | not found |
| 500 | Internal Server Error |

### /files/{fileId}

#### GET
##### Summary:

get a file

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| fileId | path | file id | Yes | integer |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | bad request |
| 404 | not found |
| 500 | Internal Server Error |

### /files/{fileId}/content

#### GET
##### Summary:

download the file content

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| fileId | path | file id | Yes | integer |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | bad request |
| 404 | not found |
| 500 | Internal Server Error |

### requirements to build, run and test the service

- java 11
- maven
- docker
- docker compose
- postman to import [Fileservice.yaml](openAPI/Fileservice.yaml) as postman collection
  to test the application
- by default the service run on port 8088 if you need to change it, update its value in [application.yaml](src/main/resources/application.yaml)
#### Step 1  *build the service using maven (packaging)*
```bash
    mvn clean package
```
#### Step 2 - *run the service*
* on local env
```bash
    java -jar ./target/file-service-0.0.1-SNAPSHOT
```
* on docker 
```bash
   docker-compose up --build    
```
### Step 3  *test the service*
- import [Fileservice.yaml](openAPI/Fileservice.yaml) as postman collection
- change the baseUrl var on postman with your server url and running port number example
  http://localhost:8080
- test all endpoints

### Step 4  *review the service documentation*
- you can review api specs [Fileservice.yaml](openAPI/Fileservice.yaml)
  on [swagger editor](https://editor.swagger.io/?url=https://raw.githubusercontent.com/ahmedshaheen93/file-service/master/openAPI/Fileservice.yaml)
