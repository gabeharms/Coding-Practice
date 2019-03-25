package pokemon

import (
  "fmt"
  "github.com/gin-gonic/gin"
  "github.com/guregu/dynamo"
)

func Show(c *gin.Context) {
  var db *dynamo.DB = c.Keys["db"].(*dynamo.DB)
  var pokemon Pokemon;
  var id string = c.Param("id")

  err := db.Table("Pokemon").Get("ID", id).One(&pokemon)

  if err != nil{
    c.JSON(400, gin.H{
      "error": fmt.Sprintf("Sorry, server no likey. %s", err),
    })
    return
  }

  c.JSON(200, pokemon)
}
