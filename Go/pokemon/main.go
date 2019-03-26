package main

import (
  "github.com/gin-gonic/gin"
  "pokemon/resources/add"
  "pokemon/resources/pokemon"
  "pokemon/resources/user"
  "github.com/guregu/dynamo"
  "github.com/aws/aws-sdk-go/aws"
  "github.com/aws/aws-sdk-go/aws/session"
)

func main() {
  r := gin.Default()

  db := dynamo.New(session.New(), &aws.Config{Region: aws.String("us-east-1")})

  r.Use(func(c *gin.Context) {
    if c.Keys == nil {
      c.Keys = make(map[string]interface{})
    }

    c.Keys["db"] = db
    c.Next()
  })

  authMiddleware, _ := GenerateAuthMiddleware()


  r.POST("/login", authMiddleware.LoginHandler)
  r.POST("/user", user.Create)

  r.POST("/add", add.Create)

  authGroup := r.Group("auth")
  authGroup.Use(authMiddleware.MiddlewareFunc())
  authGroup.GET("/pokemon", pokemon.Index)
  authGroup.GET("/pokemon/:id", pokemon.Show)
  authGroup.POST("/pokemon", pokemon.Create)


  r.Run() // listen and serve on 0.0.0.0:8080
}
