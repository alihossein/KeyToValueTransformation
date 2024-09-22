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