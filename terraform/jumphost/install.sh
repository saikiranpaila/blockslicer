#!/bin/bash
sudo yum update -y

#--------------- git install ---------------
echo "installing git"
sudo yum install git -y

#--------------- java install ----------
echo "installing java"
sudo yum install java-17-amazon-corretto -y

#--------------- install jenkins ----------------
echo "installing jenkins"
sudo wget -O /etc/yum.repos.d/jenkins.repo     https://pkg.jenkins.io/redhat/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat/jenkins.io-2023.key
sudo yum upgrade
sudo yum install jenkins -y
sudo systemctl enable jenkins
sudo systemctl start jenkins

#--------------- install terraform --------------
echo "installing terraform"
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/AmazonLinux/hashicorp.repo
sudo yum -y install terraform

#---------------- Maven install -------------
echo "installing maven"
sudo yum install maven -y

#---------------- Docker install ------------
echo "installing docker"
sudo yum install docker -y #linux 2023
sudo usermod -aG docker ec2-user
sudo usermod -aG docker jenkins 
newgrp docker
sudo chmod 777 /var/run/docker.sock
sudo systemctl start docker

#---------------- kubectl install ----------------
echo "installing kubectl"
curl -LO https://dl.k8s.io/release/v1.30.0/bin/linux/amd64/kubectl
sudo chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin

#----------------- helm install ------------------
echo "installing helm"
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
chmod 700 get_helm.sh
./get_helm.sh
chmod 777 /usr/local/bin/helm

#----------------- trivy -------------------
echo "installing trivy"
sudo rpm -ivh https://github.com/aquasecurity/trivy/releases/download/v0.53.0/trivy_0.53.0_Linux-64bit.rpm

#----------------- sonarqube --------------
echo "installing sonarqube"
docker run -d -p 9000:9000 sonarqube
