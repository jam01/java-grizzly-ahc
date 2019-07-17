/*
 * Copyright 2018 The OpenTracing Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.opentracing.contrib.grizzly.ahc;

import java.util.Iterator;
import java.util.Map;

import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilderBase;

import io.opentracing.propagation.TextMap;

/**
 * @author Jose Montoya
 */
public class RequestBuilderInjectAdapter implements TextMap {
	private final RequestBuilderBase<?> builder;

	public RequestBuilderInjectAdapter(RequestBuilderBase<?> builder) {
		this.builder = builder;
	}

	@Override
	public Iterator<Map.Entry<String, String>> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void put(String key, String value) {
		builder.addHeader(key, value);
	}

	public Request injectedRequest() {
		return builder.build();
	}
}
