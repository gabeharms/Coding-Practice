package main

import (
  "flag"
  "github.com/gin-gonic/gin"
  "pokemon/resources/add"
  "pokemon/resources/pokemon"
  "pokemon/resources/user"
)

func main() {
  environment := flag.String("env", "development", "")
  InitializeConfiguration(*environment)

  r := gin.Default()

  db := GetDatabaseClient()

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
