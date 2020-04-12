terraform {
  backend "s3" {
    bucket = "social-science"
    region = "sa-east-1"
    key = "tfstates/oauth-mongodb.tfstate"
  }
}
