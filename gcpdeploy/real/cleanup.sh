# Removing container clusters
gcloud container clusters delete hello-java-cluster --zone us-central1-c

# Images left
gcloud container images list

# Please remove not used images manually
echo "gcloud container images delete <image_name>"