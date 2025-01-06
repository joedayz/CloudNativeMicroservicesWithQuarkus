#!/usr/bin/env bash

# Define variables
REGISTRY="docker.io"
USER="joedayz"
IMAGE="${REGISTRY}/${USER}/do378-reactive-architecture-prices"

# Login to Docker Hub
podman login ${REGISTRY} -u ${USER}

# Build and push the image
podman build -f Containerfile -t ${IMAGE} .
podman push ${IMAGE}