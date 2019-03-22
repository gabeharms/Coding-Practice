package main

import (
	"time"
	"log"
	"net/http"
  "github.com/google/uuid"
	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
  "github.com/awslabs/aws-lambda-go-api-proxy/gin"
	"github.com/gin-gonic/gin"
)


var ginLambda *ginadapter.GinLambda

func Handler(req events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	if ginLambda == nil {
		// stdout and stderr are sent to AWS CloudWatch Logs
		log.Printf("Cold start")
		r := gin.Default()
		r.POST("/add", addNumbers)

		ginLambda = ginadapter.New(r)
	}

	return ginLambda.Proxy(req)
}

func main() {
	lambda.Start(Handler)
}

type Pet struct {
	ID          string    `json:"id"`
	Breed       string    `json:"breed"`
	Name        string    `json:"name"`
	DateOfBirth time.Time `json:"dateOfBirth"`
}

func getUUID() string {
	uuid, err := uuid.NewRandom()
	if err != nil {
		log.Fatal(err)
		return ""
	}
	return uuid.String()
}

func addNumbers(c *gin.Context) {
	newPet := Pet{}
	err := c.BindJSON(&newPet)

	if err != nil {
    c.JSON(200, gin.H{
			"message": "JSON not in correct format",
		})
		return
	}

	newPet.ID = getUUID()
	c.JSON(http.StatusAccepted, newPet)
}
