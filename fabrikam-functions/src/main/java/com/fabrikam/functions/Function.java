package com.fabrikam.functions;

import java.util.*;

import com.microsoft.azure.serverless.functions.*;
import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.ExecutionContext;

/**
 * "register" function with HTTP Trigger.
 */
public class Function {
    @FunctionName("register")
    public HttpResponseMessage register(
        @HttpTrigger(name = "req", methods = { "get", "post" }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage req,
        @TableOutput(name = "user", connection = "UserStorage", tableName = "user", partitionKey = "JavaDemo") OutputBinding<UserEntry> newEntry,
        ExecutionContext context) {
              
        String ua = req.getQueryParameters().get("ua");

        System.out.println(ua);

        if (ua == null) {
            return req.createResponse(400, "Please pass a User-Agent string on the query string");
        } else {
            UserEntry newUser = new UserEntry(ua);
            newEntry.setValue(newUser);
    
            return req.createResponse(200, "ok");
        }
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