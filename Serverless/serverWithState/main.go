package main

import (
  "log"
  "github.com/aws/aws-lambda-go/events"
  "github.com/aws/aws-lambda-go/lambda"
  "github.com/awslabs/aws-lambda-go-api-proxy/gin"
  "github.com/gin-gonic/gin"
  "serverWithState/resources/add"
  "serverWithState/resources/pokemon"
)

var ginLambda *ginadapter.GinLambda

func Handler(req events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
  if ginLambda == nil {
    log.Printf("Cold start")
    r := gin.Default()

    r.POST("/add", add.Create)
    r.GET("/pokemon", pokemon.Index)
    r.POST("/pokemon", pokemon.Create)

    ginLambda = ginadapter.New(r)
  }

  return ginLambda.Proxy(req)
}

func main() {
  lambda.Start(Handler)
}
