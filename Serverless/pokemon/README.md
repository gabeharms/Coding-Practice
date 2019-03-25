This app is an AWS lambda function testing ground. It is a simple REST API backed by dynamo. It
allows you to create, list and show pokemon. (there's also a dumb add endpoint)


### Deploy
`$ serverless deploy --verbose`

### Logs
`$ serverless logs -f app -t`

### Consume
Create Pokemon:
`$ curl -X POST https://53m2usernl.execute-api.us-east-1.amazonaws.com/dev/pokemon -H "Content-Type: application/json"  --data '{"type":"cold","strength":"ice", "weakness": "fire"}'  -v`

List Pokemon:
`$ curl https://53m2usernl.execute-api.us-east-1.amazonaws.com/dev/pokemon -H "Content-Type: application/json"`

Show Pokemon:
`$ curl https://53m2usernl.execute-api.us-east-1.amazonaws.com/dev/pokemon/effcb3f3-be9d-46c2-bc33-229574c5b0e7 -H "Content-Type: application/json"`

Add Numbers:
`$ curl -X POST https://53m2usernl.execute-api.us-east-1.amazonaws.com/dev/add -H "Content-Type: application/json"  --data '{"number1":1,"number2":3}' -v`
