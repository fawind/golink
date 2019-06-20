# /go/link

Self-hosted service for managing link aliases.

## Usage

```
# Run the backend service (defaults to localhost:9090)
make runService

# Run the frontend service (defaults to localhost:8080)
make runApp

# Build the service
make build

# Deploy to gcloud, make sure to set your project ID first
make deploy
```

## API

```
# Redirect to the url for <my-alias>
GET: /go/<my-alias>

# List all golinks
GET: /api/golink

# Add a new alias
POST: /api/golink - { "alias": <my-alias>, "url": <my-url> }

# Delete the mapping for <my-alias>
DELETE: /api/golink/<my-alias>
```

Per default, all endpoints except `/go/*` are secured (see [web.xml](https://github.com/fawind/golink/blob/master/service/src/main/webapp/WEB-INF/web.xml#L30-L56) for mappings).

