echo Starting Kubernetes creation

# enables services to manage Kubernetes
gcloud services enable compute.googleapis.com container.googleapis.com -q

# test for 2 real machines
gcloud container clusters create spzc-decoy-mtd-real-cluster -q \
  --num-nodes 2 \
  --machine-type e2-medium \
  --zone europe-central2-a

# set project name in env variable
export GOOGLE_CLOUD_PROJECT=`gcloud config list --format="value(core.project)"`

# prepares deployment
kubectl create deployment spzc-decoy-mtd-real \
  --image=gcr.io/$GOOGLE_CLOUD_PROJECT/spzc-decoy-mtd-real:v1

# opens connection to Pods by Kubernetes LB
kubectl create service loadbalancer hello-java --tcp=8080:8080

# printing info to stdout
echo Kubernetes configuration done
echo to check deployments use: kubectl get deployments
echo to check pods use: get pods
echo to check services use: kubectl get services
echo You can scale deployments by: kubectl scale deployment spzc-decoy-mtd-real --replicas=n