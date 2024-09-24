package com.digikala;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.kafka.connect.transforms.util.Requirements;

import java.util.HashMap;
import java.util.Map;

public class KeyToValueTransformation<R extends ConnectRecord<R>> implements Transformation<R> {
    private String fieldName;
    @Override
    public R apply(R record) {

        // Verify if the key is a string
        if (record.key() == null || !(record.key() instanceof String) ) {
            return record;  // return record if
        }

        // Get String Key
        String key = (String) record.key();

        // Check if the value is null or empty, create a new HashMap if needed
        Map<String, Object> valueMap;

        if (record.value() == null) {
            valueMap = new HashMap<>();
        } else {
            valueMap = Requirements.requireMap(record.value(), "value");
        }


        // add the key into the value with dynamic field name
        valueMap.put(fieldName, key);

        // Create a new record
        return record.newRecord(
                record.topic(),
                record.kafkaPartition(),
                record.keySchema(),
                record.key(),
                record.valueSchema(),
                valueMap,
                record.timestamp()
        );
    }

    @Override
    public void configure(Map<String, ?> configs) {
        Object fieldNameObj = configs.get("field.name");
        fieldName = (fieldNameObj != null) ? fieldNameObj.toString() : "added_key";


    }

    @Override
    public void close() {
        //
    }

    @Override
    public ConfigDef config() {
        return new ConfigDef()
                .define("field.name", ConfigDef.Type.STRING, "added_key", ConfigDef.Importance.HIGH, "The field name to add to the value map");
    }
}
