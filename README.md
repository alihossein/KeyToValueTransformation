# KeyToValueTransformation
With this SMT (Single Message Transform), we can add the value of an existing key in Kafka records to the message payload when needed. This allows for flexible use cases where the key itself needs to be included in the record value. It's important to note that the message value must be in JSON format.

# Configuration
You can specify the name of the final field that will contain the key by using the following configuration:

```
"transforms.AddKeyToValue.field.name": "my_custom_key"
```

Example
Kafka Connect Configuration:
```
"transforms": "AddKeyToValue",
"transforms.AddKeyToValue.type": "com.digikala.KeyToValueTransformation",
"transforms.AddKeyToValue.field.name": "my_custom_key"

```
This configuration adds the key as a field called my_custom_key in the value.

# Build
To build the project, run:
```
mvn clean install
```
Example Scenarios
- With Value:

Input Record:
``` 
key: "test"
value: {
    "ping": "pong"
}
```
Final Message:
``` 
{
    "ping": "pong",
    "my_custom_key": "test"
}
```
- Without Value (Null Value):

  Input Record:
``` 
key: "test"
value: null
```
Final Message:

``` 
{
  "my_custom_key": "test"
}
```
This transformation is useful when you need to ensure that the key is part of the message payload, especially for downstream systems that may rely on the key for processing.








