package add

import (
  "net/http"
  "github.com/gin-gonic/gin"
)

type RequestBody struct {
  Number1 int64 `json:"number1"`
  Number2 int64 `json:"number2"`
}

type ResponseBody struct {
  Result int64 `json:"result"`
}

func Create(c *gin.Context) {
  requestBody := RequestBody{Number1: 1, Number2: 2};
  responseBody := ResponseBody{};

  c.BindJSON(&requestBody)

  responseBody.Result = requestBody.Number1 + requestBody.Number2

  c.JSON(http.StatusAccepted, responseBody)
}
