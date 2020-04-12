resource "aws_ebs_volume" "oauth_volume" {
  availability_zone = "sa-east-1a"
  size = 5

  tags = {
    type = "database"
    app = "oauth"
  }
}

output "volume_id" {
  value = aws_ebs_volume.oauth_volume.id
}

output "volume_type" {
  value = aws_ebs_volume.oauth_volume.type
}