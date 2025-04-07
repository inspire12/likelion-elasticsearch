PUT /reviews
{
"mappings": {
"properties": {
"storeId":    { "type": "long" },
"customerId": { "type": "long" },
"content":    { "type": "text", "analyzer": "nori" },
"rating":     { "type": "integer" },
"sentiment":  { "type": "keyword" },
"createdAt":  { "type": "date" },
"updatedAt":  { "type": "date" }
}
}
}
