package pokemon

import (
  "fmt"
  "github.com/gin-gonic/gin"
  "github.com/guregu/dynamo"
)

func Index(c *gin.Context) {
  var db *dynamo.DB = c.Keys["db"].(*dynamo.DB)
  var allPokemon []Pokemon;

  err := db.Table("Pokemon").
    Scan().
    Filter("'UserID' = ?", c.Keys["userID"].(string)).
    All(&allPokemon)

  if err != nil{
    c.JSON(400, gin.H{
      "error": fmt.Sprintf("Sorry, server no likey. %s", err),
    })
    return
  }

  c.JSON(200, allPokemon)
}
