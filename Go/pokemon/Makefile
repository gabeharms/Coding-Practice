.PHONY: build clean deploy

build:
	dep ensure -v
	env GOOS=darwin GOARCH=386 go build -ldflags="-s -w" -o bin/app main.go auth.go config.go database.go

clean:
	rm -rf ./bin ./vendor Gopkg.lock

deploy: clean build
	sls deploy --verbose
