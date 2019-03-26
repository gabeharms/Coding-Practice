package main

import (
  "github.com/aws/aws-sdk-go/aws"
  "github.com/aws/aws-sdk-go/aws/credentials"
  "github.com/aws/aws-sdk-go/aws/session"
  "github.com/guregu/dynamo"
)

var svc *dynamo.DB
var sess *session.Session

// GetClient provides a dynamodb client.
func GetDatabaseClient() *dynamo.DB {
  if svc != nil {
    return svc
  }

  session, _ := GetSession()
  svc = dynamo.New(session, &aws.Config{Region: aws.String(GetConfig("aws.region"))})

  return svc
}

// GetSession provides an AWS session.
func GetSession() (*session.Session, error) {
  if sess != nil {
    return sess, nil
  }

  creds := credentials.NewSharedCredentials(GetConfig("aws.credentials_file_path"), GetConfig("aws.credentials_name"))
  sess, err := session.NewSession(&aws.Config{
    Region:      aws.String(GetConfig("aws.region")),
    Credentials: creds,
  })

  return sess, err
}
