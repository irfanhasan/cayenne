/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.lifecycle.relationship;

import org.apache.cayenne.Fault;
import org.apache.cayenne.Persistent;

/**
 * @since 3.1
 */
class UuidFault extends Fault {

    private String uuid;
    private UuidBatchFault batchFault;

    UuidFault(UuidBatchFault batchFault, String uuid) {
        this.batchFault = batchFault;
        this.uuid = uuid;
    }

    @Override
    public Object resolveFault(Persistent sourceObject, String relationshipName) {
        return uuid != null ? batchFault.getObjects().get(uuid) : null;
    }
}
