# Set environment variables
# Adjust the paths based on your OS
ifeq ($(OS), Windows_NT)
    # Windows path
    LOG_PATH := C:/warehouse-logs
else
    # Linux or Mac path
    LOG_PATH := /var/log/myapp/logs
endif

# Set environment file for Docker Compose
ENV_FILE := .env

# Write LOG_PATH to .env file
.PHONY: write-env
write-env:
	@echo "Updating .env file with LOG_PATH..."
	@echo "LOG_PATH=$(LOG_PATH)" > $(ENV_FILE)

DOCKER_IMAGE_NAME := app
DOCKER_COMPOSE_FILE := docker-compose.yml

# Default target - Run the application
.PHONY: all
all: run

# Build the Docker image
.PHONY: build
build:
	@echo "Building Docker image..."
	docker build -t $(DOCKER_IMAGE_NAME) .

# Start the application with Docker Compose
.PHONY: run
run: write-env build
	@echo "Running application with Docker Compose..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) --env-file $(ENV_FILE) up -d

# Stop the Docker Compose setup
.PHONY: stop
stop:
	@echo "Stopping Docker Compose..."
	docker-compose -f $(DOCKER_COMPOSE_FILE) --env-file $(ENV_FILE) down

# Rebuild the Docker image and restart the application
.PHONY: rebuild
rebuild: clean build run
