/**
 * Copyright 2013 Alex Yanchenko
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.droidparts.reflect.type;

import java.util.ArrayList;

import org.droidparts.reflect.util.TypeHelper;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public class JSONObjectHandler extends AbstractTypeHandler<JSONObject> {

	@Override
	public boolean canHandle(Class<?> cls) {
		return TypeHelper.isJSONObject(cls);
	}

	@Override
	public String getDBColumnType() {
		return TEXT;
	}

	@Override
	public JSONObject readFromJSON(Class<?> cls, JSONObject obj, String key)
			throws JSONException {
		return new JSONObject(obj.getString(key));
	}

	@Override
	public void putToJSONObject(JSONObject obj, String key, Object val)
			throws JSONException {
		obj.put(key, val.toString());
	}

	@Override
	public void putToContentValues(ContentValues cv, String key, Object val) {
		cv.put(key, val.toString());
	}

	@Override
	public JSONObject readFromCursor(Class<?> cls, Cursor cursor,
			int columnIndex) throws IllegalArgumentException {
		try {
			return new JSONObject(cursor.getString(columnIndex));
		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public Object parseTypeArr(Class<?> arrValType, String[] arr) {
		ArrayList<JSONObject> list = toTypeColl(JSONObject.class, arr);
		return list.toArray(new JSONObject[list.size()]);
	}

}
