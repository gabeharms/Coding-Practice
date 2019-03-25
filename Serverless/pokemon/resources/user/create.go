package user

import (
  "fmt"
  "net/http"
  "github.com/gin-gonic/gin"
  "github.com/guregu/dynamo"
)


func Create(c *gin.Context) {
  newUser := User{}

  err1 := c.BindJSON(&newUser)

  newUser.ID = getUUID()

  hashedPassword, err2 := GenerateFromPassword(c.Param("password"))
  newUser.PasswordHash = hashedPassword

  var db *dynamo.DB = c.Keys["db"].(*dynamo.DB)
  err := db.Table("Users").Put(newUser).Run()

  if err != nil || err1 != nil || err2 != nil {
    c.JSON(400, gin.H{
      "error": fmt.Sprintf("Sorry, server no likey. %s", err),
    })
    return
  }

  c.JSON(http.StatusAccepted, newUser)
}
