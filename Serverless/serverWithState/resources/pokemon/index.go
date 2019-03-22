package pokemon

import (
  "github.com/gin-gonic/gin"
)

func Index(c *gin.Context) {

  c.JSON(200, gin.H{
    "message": "JSON not in correct format",
  })
}
