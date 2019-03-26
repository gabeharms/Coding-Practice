package main

import (
  "github.com/spf13/viper"
)

var configuration *viper.Viper
var configEnvironment string

// InitializeEnvironment sets the kind of environment to use for application configuration.
func InitializeConfiguration(environment string) {
  configEnvironment = environment
}

// GetConfiguration loads and retrieves the application configuration based on the initialized environment.
func GetConfiguration() (*viper.Viper, error) {
  if configuration != nil {
    return configuration, nil
  }

  configuration = viper.New()
  configuration.AddConfigPath("./")
  configuration.SetConfigType("yaml")
  configuration.SetConfigName(configEnvironment)
  err := configuration.ReadInConfig()
  return configuration, err
}

// GetConfig retrieves a string configuration value.
func GetConfig(configValue string) string {
  cnfg, _ := GetConfiguration()
  return cnfg.GetString(configValue)
}
