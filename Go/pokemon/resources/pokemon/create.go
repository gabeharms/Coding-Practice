package pokemon

import (
  "log"
  "fmt"
  "net/http"
  "github.com/gin-gonic/gin"
  "github.com/guregu/dynamo"
)


func Create(c *gin.Context) {
  newPokemon := Pokemon{}
  err1 := c.BindJSON(&newPokemon)

  newPokemon.ID = getUUID()
  log.Println(c.Keys)
  newPokemon.UserID = c.Keys["userID"].(string)

  var db *dynamo.DB = c.Keys["db"].(*dynamo.DB)
  err2 := db.Table("Pokemon").Put(newPokemon).Run()

  if err1 != nil || err2 != nil{
    c.JSON(400, gin.H{
      "error": fmt.Sprintf("Sorry, server no likey. %s", err2),
    })
    return
  }

  c.JSON(http.StatusAccepted, newPokemon)
}
