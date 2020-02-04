var markup = require ('@procore/financials-markup-engine')
const fastify = require('fastify')()
const path = require('path')

fastify.register(require('fastify-static'), {
  root: path.join(__dirname, 'public'),
  prefix: '/public/', // optional: default '/'
})

fastify.get('/', function (req, reply) {
  reply.sendFile('index.html')
})

fastify.post('/generateMarkupLineItems', (request, reply) => {
  reply.send(markup.generateMarkupLineItems(request.body.markups, request.body.lineItems))
})

fastify.listen(80, '0.0.0.0', (err, address) => {
  if (err) throw err
  fastify.log.info(`server listening on ${address}`)
})
