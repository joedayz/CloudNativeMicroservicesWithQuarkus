# Prices Service for DO378

Prices microservice for the `reactive-architecture` exercise.
The exercise uses this app as a container (`"quay.io/redhattraining/do378-reactive-architecture-prices`).

To build the container image and push it to the registry, run:

```sh
./push_image.sh
```

```
podman run -d --name prices-service -p 5500:5000 docker.io/joedayz/do378-reactive-architecture-prices
```
