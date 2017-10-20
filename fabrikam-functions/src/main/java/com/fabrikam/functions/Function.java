package com.fabrikam.functions;

import java.util.*;

import com.microsoft.azure.serverless.functions.*;
import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.ExecutionContext;

/**
 * Hello function with HTTP Trigger.
 */
public class Function {
    @FunctionName("hello")
    public String hello(
        @HttpTrigger(name = "req", methods = { "get", "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage req,
        @TableOutput(name = "user", connection = "UserStorage", tableName = "user", partitionKey = "JavaDemo") OutputBinding<UserEntry> newEntry,
        ExecutionContext context) {
        
        Object a = req.getHeaders();
        
        String ua = req.getQueryParameters().get("ua");

        System.out.println(ua);

        UserEntry newUser = new UserEntry(ua);
        newEntry.setValue(newUser);

        return "ok";
    }
}

final class UserEntry {
    UserEntry(String ua) {
        this.RowKey = UUID.randomUUID().toString();
        this.UA = ua;
    }
    
    public String getUserId() { return this.RowKey; }
    public String getUA() { return this.UA; }

    private String RowKey;
    private String UA;
}