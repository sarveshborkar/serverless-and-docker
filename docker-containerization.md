# Containerization an Application using Docker

This project is a Java-based web application that computes the MD5 checksum of a given string. The application is containerized using Docker for ease of deployment and scalability.
The goal of this activity is to containerize this application using Docker and publish image to a registry.

## Create Dockerfile for building and deploying the application 

To setup a docker container, we need to prepare `Dockerfile` for the application to be deployed:

1. Create Dockerfile to setup the environment and build the application if required.
<img width="1440" alt="Screenshot 2024-09-21 at 3 11 15 PM" src="https://github.com/user-attachments/assets/eb781b05-0740-4c8b-b55d-3adeaf54dd76">


## Run Tests & Build the Docker Image

To build the Docker image for the application, follow these steps:

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/sarveshborkar/serverless-and-docker.git
    cd md5-checksum-app
    ```

2. Build the Docker image using the Dockerfile provided:

    ```bash
    docker build  --progress=plain --no-cache -t md5-checksum-app .
    ```
    
    This command will run unit tests and build the application into the container.

    <img width="1440" alt="Screenshot 2024-09-21 at 2 21 55 PM" src="https://github.com/user-attachments/assets/bdd2cfaa-d32a-46fd-8ba7-5ee2df05015a">
    <img width="1440" alt="Screenshot 2024-09-21 at 2 22 28 PM" src="https://github.com/user-attachments/assets/35f627bb-167e-4b3e-bf0a-92fe8eca6cb2">
    <img width="1440" alt="Screenshot 2024-09-21 at 2 22 39 PM" src="https://github.com/user-attachments/assets/44d44ab6-156f-48e0-bdcd-b3c620c47d69">
    <img width="1440" alt="Screenshot 2024-09-21 at 2 22 45 PM" src="https://github.com/user-attachments/assets/2ea36e54-f4c0-4a7b-ad77-82e7532b0d29">


## Running the Docker Container

Once the image is built, you can run it as a container:

```bash
docker run -d -p 8080:8080 md5-checksum-app
```
<img width="1440" alt="Screenshot 2024-09-21 at 2 44 01 PM" src="https://github.com/user-attachments/assets/04b3120c-69ce-462c-8347-30d09df2b7dd">

## Testing the application

Now, we can test out application. In this particular case, we will try to make a sample api request using postman.
<img width="1440" alt="Screenshot 2024-09-21 at 2 45 06 PM" src="https://github.com/user-attachments/assets/bc701377-6382-4d62-b884-568ee06e79b7">

## Publish Docker Image to Docker Hub

### 1. Create a Docker Hub Account
- If you don’t already have an account, go to [Docker Hub](https://hub.docker.com/signup) and sign up.

### 2. Log in to Docker Hub from the Command Line
- Once your account is ready, log in to Docker Hub using the Docker CLI:

    ```bash
    docker login
    ```

- You'll be prompted to enter your Docker Hub username and password.
<img width="1440" alt="Screenshot 2024-09-21 at 2 45 46 PM" src="https://github.com/user-attachments/assets/4f2a7508-3e61-48b1-adf8-6df39b92043d">

### 3. Tag Your Docker Image
- Before pushing your image to Docker Hub, you need to tag it with your Docker Hub username and repository name. Use the following format:

    ```bash
    docker tag md5-checksum-app <your-dockerhub-username>/md5-checksum-app:latest
    ```

- Example:

    ```bash
    docker tag md5-checksum-app borkarsarvesh/md5-checksum-app:latest
    ```
    
<img width="1440" alt="Screenshot 2024-09-21 at 2 46 11 PM" src="https://github.com/user-attachments/assets/e2fbcf38-fce7-482e-9560-96eee009e05f">

### 4. Push the Image to Docker Hub
- After tagging the image, push it to your Docker Hub repository using the `docker push` command:

    ```bash
    docker push <your-dockerhub-username>/md5-checksum-app:latest
    ```
- Example:

    ```bash
    docker push borkarsarvesh/md5-checksum-app:latest
    ```
    
<img width="1440" alt="Screenshot 2024-09-21 at 2 47 08 PM" src="https://github.com/user-attachments/assets/342ce415-8b87-499f-ac5d-4037b1eb3b8e">
<img width="1440" alt="Screenshot 2024-09-21 at 2 47 54 PM" src="https://github.com/user-attachments/assets/666c0f94-b120-4b77-9c13-80dc7de67ade">
