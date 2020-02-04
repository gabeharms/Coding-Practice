```
curl -o /dev/null -s -w 'Total: %{time_total}s\n' -d "@request.json" -H "Content-Type: application/json" -X POST http://localhost:3000
```
