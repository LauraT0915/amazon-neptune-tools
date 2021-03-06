/*
Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
Licensed under the Apache License, Version 2.0 (the "License").
You may not use this file except in compliance with the License.
A copy of the License is located at
    http://www.apache.org/licenses/LICENSE-2.0
or in the "license" file accompanying this file. This file is distributed
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
express or implied. See the License for the specific language governing
permissions and limitations under the License.
*/

package com.amazonaws.services.neptune.propertygraph.metadata;

import com.amazonaws.services.neptune.propertygraph.NamedQueriesCollection;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class CreateQueriesFromFile {
    private final File queriesFilePath;

    public CreateQueriesFromFile(File queriesFilePath) {
        this.queriesFilePath = queriesFilePath;
    }

    public NamedQueriesCollection execute() throws IOException {
        ArrayNode json = (ArrayNode) new ObjectMapper().readTree(queriesFilePath);
        return NamedQueriesCollection.fromJson(json);
    }
}
