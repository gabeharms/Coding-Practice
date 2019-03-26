package pokemon

import (
  "log"
  "github.com/google/uuid"
)
type Pokemon struct {
  ID       string `json:"id"`
  UserID   string `json:"userId"`
  Type     string `json:"type"`
  Strength string `json:"strength"`
  Weakness string `json:"weakness"`
}

func getUUID() string {
  uuid, err := uuid.NewRandom()
  if err != nil {
    log.Fatal(err)
    return ""
  }
  return uuid.String()
}
