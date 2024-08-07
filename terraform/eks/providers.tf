terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.57.0"
    }
  }
  backend "s3" {
    bucket = "jumphost-bucket"
    key    = "eks/terraform.tfstate"
    region = "us-east-1"
  }
}

# provider "aws" {
#   region = local.region
# }
