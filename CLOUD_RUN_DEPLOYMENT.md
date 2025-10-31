# Google Cloud Run Deployment Guide

## Prerequisites
1. Google Cloud Platform account
2. gcloud CLI installed and authenticated
3. Docker installed locally (optional, for local testing)

## Deployment Steps

### 1. Enable Required APIs
```bash
gcloud services enable run.googleapis.com
gcloud services enable containerregistry.googleapis.com
gcloud services enable cloudbuild.googleapis.com
```

### 2. Set Your Project ID
```bash
gcloud config set project YOUR_PROJECT_ID
```

### 3. Build and Deploy to Cloud Run (One Command)
Cloud Run can build your image directly from source:
```bash
gcloud run deploy gcp-demo-service ^
  --source . ^
  --platform managed ^
  --region us-central1 ^
  --allow-unauthenticated ^
  --port 8080
```

### 4. Alternative: Build Locally and Deploy
If you prefer to build the Docker image locally:

#### a. Build the Docker image
```bash
docker build -t gcr.io/YOUR_PROJECT_ID/gcp-demo:latest .
```

#### b. Push to Google Container Registry
```bash
docker push gcr.io/YOUR_PROJECT_ID/gcp-demo:latest
```

#### c. Deploy to Cloud Run
```bash
gcloud run deploy gcp-demo-service ^
  --image gcr.io/YOUR_PROJECT_ID/gcp-demo:latest ^
  --platform managed ^
  --region us-central1 ^
  --allow-unauthenticated ^
  --port 8080
```

## Test Your Deployment

After deployment, Cloud Run will provide a URL like:
`https://gcp-demo-service-XXXXX-uc.a.run.app`

Test the endpoints:
- Home: `https://your-service-url/`
- User greeting: `https://your-service-url/John`
- About: `https://your-service-url/about`

## Important Notes

### ✅ What's Now Configured:
1. **Multi-stage Docker build** - Optimized image size
2. **PORT environment variable** - Spring Boot reads from `${PORT:8080}`
3. **Lightweight base image** - Using Alpine Linux
4. **Proper build process** - Gradle build happens inside Docker

### 📝 Additional Configurations (Optional):

#### Set Memory Limits
```bash
gcloud run deploy gcp-demo-service --memory 512Mi
```

#### Set CPU Allocation
```bash
gcloud run deploy gcp-demo-service --cpu 1
```

#### Set Environment Variables
```bash
gcloud run deploy gcp-demo-service --set-env-vars "KEY1=VALUE1,KEY2=VALUE2"
```

#### Enable Automatic Scaling
```bash
gcloud run deploy gcp-demo-service --min-instances 0 --max-instances 10
```

## Troubleshooting

### View Logs
```bash
gcloud run services logs read gcp-demo-service --region us-central1
```

### Describe Service
```bash
gcloud run services describe gcp-demo-service --region us-central1
```

### Delete Service
```bash
gcloud run services delete gcp-demo-service --region us-central1
```

## Cost Optimization
- Cloud Run charges only when your service is processing requests
- Set `--min-instances 0` to scale to zero when not in use
- First 2 million requests per month are free

