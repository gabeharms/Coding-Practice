package pokemon

import (
  "fmt"
  "net/http"
  "github.com/gin-gonic/gin"
  "github.com/guregu/dynamo"
)


func Create(c *gin.Context) {
  newPokemon := Pokemon{}
  err1 := c.BindJSON(&newPokemon)

  newPokemon.ID = getUUID()

  db := c.Keys["db"].(*dynamo.DB)
  err2 := db.Table("Pokemon").Put(newPokemon).Run()

  if err1 != nil || err2 != nil{
    c.JSON(400, gin.H{
      "error": fmt.Sprintf("Sorry, server no likey. %s", err2),
    })
    return
  }

  c.JSON(http.StatusAccepted, newPokemon)
}
