/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dinky.operations;

import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.internal.TableResultInternal;
import org.apache.flink.table.operations.ExecutableOperation;
import org.apache.flink.table.operations.Operation;

public class DinkyExecutableOperation implements ExecutableOperation {

    private final Operation innerOperation;
    private final TableEnvironment tableEnvironment;

    public DinkyExecutableOperation(TableEnvironment tableEnvironment, Operation innerOperation) {
        this.tableEnvironment = tableEnvironment;
        this.innerOperation = innerOperation;
    }

    @Override
    public TableResultInternal execute(Context ctx) {
        DinkyOperationExecutor operationExecutor = new DinkyOperationExecutor(tableEnvironment, ctx);
        return operationExecutor.executeOperation(innerOperation).get();
    }

    public Operation getInnerOperation() {
        return innerOperation;
    }

    @Override
    public String asSummaryString() {
        return innerOperation.asSummaryString();
    }
}