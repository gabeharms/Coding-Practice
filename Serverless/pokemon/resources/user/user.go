package user

import (
  "log"
  "github.com/google/uuid"
  "golang.org/x/crypto/bcrypt"
)
type User struct {
  ID           string `json:"id"`
  Email        string `json:"email"`
  PasswordHash string `json:"-"`
}

func getUUID() string {
  uuid, err := uuid.NewRandom()
  if err != nil {
    log.Fatal(err)
    return ""
  }
  return uuid.String()
}

func GenerateFromPassword(password string) (string, error) {
  bytes, err := bcrypt.GenerateFromPassword([]byte(password), 14)
  return string(bytes), err
}

func ValidatePassword(password, hash string) bool {
  err := bcrypt.CompareHashAndPassword([]byte(hash), []byte(password))
  return err == nil
}
