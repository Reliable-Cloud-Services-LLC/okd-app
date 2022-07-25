# okd-app
This is a java application deployed using OKD clusters
### Deploying the OKD cluster
SSH into an Amazon Ec2 Instance and make sure you have an SSH key to be associated with the cluster/
Next download the OKD installer available at https://github.com/openshift/okd/releases/
```
tar -xvf openshift-install-linux.tar.gz
```
The command above extracts the installer\

```
./openshift-install create cluster --dir <installation_directory> \ 
    --log-level=info 
```
Install the OpenShift Cli for your respective machine next\
After the OKD cluster is deployed from anywhere in 30 minutes to 60 minutes/
You have to set your KUBECONFIG environment variable path\
```
export KUBECONFIG=<installation_directory>/auth/kubeconfig
```
You should be able to access the OKD console and Cli communiting with the OKD cluster/
### Setting a secret
You have to set a secret for the repository you will pull the image onto the cluster for/
In the case of an AWS ECR repository with an image avilable inside follow these steps/
```
aws ecr get-login-password --region region | docker login --username AWS --password-stdin aws_account_id.dkr.ecr.region.amazonaws.com
```
This command logs into ECR/
```
aws ecr get-login-password --region region
```
You have to have your password for the next steps and you have to provide the region your ECR repository is in/
```
kubectl create secret docker-registry regcred --docker-server=<your-registry-server> --docker-username=<your-name> --docker-password=<your-pword> --docker-email=<your-email>
```
For this command you have to exchange some values/
<your-registry-server> would be aws_account_id.dkr.ecr.region.amazonaws.com/
<your-name> would be AWS/
<your-pword> would be the login password from the AWS ECR command above/
<your-email> would be the email of the AWS account/
To verify your secret is created run this command:
```
kubectl get secret regcred --output=yaml
```
Next step is to apply the pull secret to the namespace you want to deploy the app in\
```
oc secrets link default <pull_secret_name> --for=pull

```
### Pulling the image from Repository and deploying the app
With the OC Command line we have many commands to help us deploy applications/
To deploy your application with a single command run:
```
oc new-app <Link of private repository leading to image contained>
```

