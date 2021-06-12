echo "Starting docker image creation"

# creater jar package
./mvnw -DskipTests package

# makes sure than it is possible to add containers to registry
gcloud services enable containerregistry.googleapis.com

# set project name in env variable
export GOOGLE_CLOUD_PROJECT=`gcloud config list --format="value(core.project)"`

# builds a Docker img
./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build \
  -Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/spzc-decoy-mtd-real:v1
