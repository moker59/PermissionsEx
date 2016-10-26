/**
 * PermissionsEx
 * Copyright (C) zml and PermissionsEx contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ninja.leaping.permissionsex.backend.file;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import ninja.leaping.permissionsex.PermissionsEx;
import ninja.leaping.permissionsex.data.SubjectRef;
import ninja.leaping.permissionsex.util.Util;

/**
 * TypeSerializer implementation for {@link SubjectRef} instances
 */
public class SubjectRefTypeSerializer implements TypeSerializer<SubjectRef> {
    @Override
    public SubjectRef deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
        Object val = value.getValue();
        if (val == null) {
            return null;
        }
        String[] entries = val.toString().split(":", 2);
        if (entries.length == 1) {
            return SubjectRef.of(PermissionsEx.SUBJECTS_GROUP, entries[0]);
        } else {
            return SubjectRef.of(entries[0], entries[1]);
        }
    }

    @Override
    public void serialize(TypeToken<?> type, SubjectRef obj, ConfigurationNode value) throws ObjectMappingException {
        if (obj == null) {
            value.setValue(null);
        } else {
            value.setValue(obj.getType() + ":" + obj.getIdentifier());
        }

    }
}