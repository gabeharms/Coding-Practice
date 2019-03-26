This app is playground for me to experiment with the go language in a REST API context.
It is a simple REST API backed by dynamo. It allows you to create, list and show pokemon.
(there's also a dumb add endpoint)

### Build
`$ make`

### Run
`$ ./bin/app`

### Consume

Add Numbers:
`$ curl -X POST localhost:8080/add -H "Content-Type: application/json" --data '{"number1":1,"number2":3}' -v`

Login:
`$ curl -X POST localhost:8080/login -H "Content-Type: application/json" --data '{"email":"gabeharms9@gmail.com","password":"<password>"}' -v`

Create Pokemon:
`$ curl -X POST localhost:8080/pokemon -H "Content-Type: application/json" --data '{"type":"cold","strength":"ice", "weakness": "fire"}' -H "Authorization: Bearer <token>" -v`

List Pokemon:
`$ curl localhost:8080/pokemon -H "Content-Type: application/json" -H "Authorization: Bearer <token>"`

Show Pokemon:
`$ curl localhost:8080/pokemon/effcb3f3-be9d-46c2-bc33-229574c5b0e7 -H "Content-Type: application/json" -H "Authorization: Bearer <token>"`


