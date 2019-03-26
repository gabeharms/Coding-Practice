package user

import (
  "fmt"
  "net/http"
  "github.com/gin-gonic/gin"
  "github.com/guregu/dynamo"
)

type UserParams struct {
  Email        string `json:"email"`
  Password     string `json:"password"`
}

func Create(c *gin.Context) {
  newUser := User{}
  userParams := UserParams{}

  err1 := c.BindJSON(&userParams)

  newUser.ID = getUUID()
  newUser.Email = userParams.Email

  hashedPassword, err2 := GenerateFromPassword(userParams.Password)
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
