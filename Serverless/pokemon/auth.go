package main

import (
  "time"
  "github.com/appleboy/gin-jwt"
  "github.com/gin-gonic/gin"
  "pokemon/resources/user"
  "github.com/guregu/dynamo"
)

type login struct {
	Email string `form:"email" json:"email" binding:"required"`
	Password string `form:"password" json:"password" binding:"required"`
}

var identityKey = "email"

func GenerateAuthMiddleware() (*jwt.GinJWTMiddleware, error) {
  return jwt.New(&jwt.GinJWTMiddleware{
    Key:         []byte("secret key"),
    Timeout:     time.Hour,
    MaxRefresh:  time.Hour,
    IdentityKey: identityKey,
    PayloadFunc: func(data interface{}) jwt.MapClaims {
      if v, ok := data.(*user.User); ok {
        return jwt.MapClaims{
          identityKey: v.Email,
        }
      }
      return jwt.MapClaims{}
    },
    IdentityHandler: func(c *gin.Context) interface{} {
      claims := jwt.ExtractClaims(c)
      return &user.User{
        Email: claims["email"].(string),
      }
    },
    Authenticator: func(c *gin.Context) (interface{}, error) {
      var loginVals login
      if err := c.ShouldBind(&loginVals); err != nil {
        return "", jwt.ErrMissingLoginValues
      }
      email := loginVals.Email
      password := loginVals.Password

      currentUser := user.User{}
      var db *dynamo.DB = c.Keys["db"].(*dynamo.DB)
      err1 := db.Table("Users").Get("Email", email).One(&currentUser)

      if (err1 == nil && user.ValidatePassword(currentUser.PasswordHash, password)) {
        return currentUser, nil
      }

      return nil, jwt.ErrFailedAuthentication
    },
    Authorizator: func(data interface{}, c *gin.Context) bool {
      if v, ok := data.(*user.User); ok && v.Email == "admin" {
        return true
      }

      return false
    },
    Unauthorized: func(c *gin.Context, code int, message string) {
      c.JSON(code, gin.H{
        "code":    code,
        "message": message,
      })
    },
    // TokenLookup is a string in the form of "<source>:<name>" that is used
    // to extract token from the request.
    // Optional. Default value "header:Authorization".
    // Possible values:
    // - "header:<name>"
    // - "query:<name>"
    // - "cookie:<name>"
    // - "param:<name>"
    TokenLookup: "header: Authorization",
    // TokenLookup: "query:token",
    // TokenLookup: "cookie:token",

    // TokenHeadName is a string in the header. Default value is "Bearer"
    TokenHeadName: "Bearer",

    // TimeFunc provides the current time. You can override it to use another time value. This is useful for testing or if your server uses a different time zone than your tokens.
    TimeFunc: time.Now,
  })
}
