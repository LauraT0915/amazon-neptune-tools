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

package com.amazonaws.services.neptune.cli;

import com.amazonaws.services.neptune.io.Directories;
import com.amazonaws.services.neptune.io.KinesisConfig;
import com.amazonaws.services.neptune.io.Target;
import com.amazonaws.services.neptune.rdf.io.RdfExportFormat;
import com.amazonaws.services.neptune.rdf.io.RdfTargetConfig;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.AllowedValues;
import com.github.rvesse.airline.annotations.restrictions.Once;

import java.nio.file.Path;

public class RdfTargetModule {

    @Option(name = {"-o", "--output"}, description = "Output target (optional, default 'file')")
    @Once
    @AllowedValues(allowedValues = {"files", "stdout", "stream"})
    private Target output = Target.files;

    @Option(name = {"--format"}, description = "Output format (optional, default 'turtle'")
    @Once
    @AllowedValues(allowedValues = {"turtle", "nquads", "neptuneStreamsJson"})
    private RdfExportFormat format = RdfExportFormat.turtle;

    @Option(name = {"--stream-name"}, description = "Name of an Amazon Kinesis Data Stream")
    @Once
    private String streamName;

    @Option(name = {"--region"}, description = "AWS Region in which your Amazon Kinesis Data Stream is located")
    @Once
    private String region;

    public RdfTargetConfig config(Directories directories) {
        return new RdfTargetConfig(directories, new KinesisConfig(streamName, region), output, format);
    }

    public void writeCommandResult(Path path){
        output.writeCommandResult(path);
    }
}
