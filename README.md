# KeyToValueTransformation

With this SMT (Single Message Transform), we can add the value of an existing key in Kafka records when needed. It's important to note that the value must be in JSON format.

Using the following configuration, we can specify the name of the final field:

```
"transforms.AddKeyToValue.field.name": "my_custom_key",
```

Example:


```
"transforms": "AddKeyToValue",
"transforms.AddKeyToValue.type": "com.digikala.KeyToValueTransformation",
"transforms.AddKeyToValue.field.name": "my_custom_key",
```

# Build 
```
mvn clean install
```

# Note
- If the record contains no value and only has a key, the final message will only include the key's value.

# Example 
record : 
key :
test

value :
```json
{
    "ping": "pong"
}
```

Final Message :
```json
{
    "ping": "pong",
    "custom_key": "test"
}

```

------------------------------
key : test

value : null

Final Message : 

```json
{
  "custom_key": "test"
}

```





