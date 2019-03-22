package pokemon

import (
  "net/http"
  "github.com/gin-gonic/gin"
)


func Create(c *gin.Context) {
  newPokemon := Pokemon{}
  err := c.BindJSON(&newPokemon)

  if err != nil {
    c.JSON(400, gin.H{
      "error": "JSON not in correct format",
    })
    return
  }

  newPokemon.ID = getUUID()
  c.JSON(http.StatusAccepted, newPokemon)
}
