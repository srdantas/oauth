# MongoDB on Kubernetes Cluster
This help us to create a mongo database into kubernetes cluster.

For run local, we need the ```deployment-local.yaml```.

For run AWS Cluster, we need the ```deployment.yaml```, before run the terraform.
When you run a terraform the output, volume id and the volume type, you put in ```deployment.yaml```.

After, run ```service.yaml```.