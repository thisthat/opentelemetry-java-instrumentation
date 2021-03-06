/*
 * Copyright The OpenTelemetry Authors
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

package io.opentelemetry.auto.instrumentation.kubernetes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KubernetesResource {

  public static final Pattern CORE_RESOURCE_URL_PATH_PATTERN =
      Pattern.compile(
          "^/api/v1(/namespaces/(?<namespace>[\\w-]+))?/(?<resource>[\\w-]+)(/(?<name>[\\w-]+))?(/(?<subresource>[\\w-]+))?");

  public static final Pattern REGULAR_RESOURCE_URL_PATH_PATTERN =
      Pattern.compile(
          "^/apis/(?<group>\\S+?)/(?<version>\\S+?)(/namespaces/(?<namespace>[\\w-]+))?/(?<resource>[\\w-]+)(/(?<name>[\\w-]+))?(/(?<subresource>[\\w-]+))?");

  public static KubernetesResource parseCoreResource(String urlPath)
      throws ParseKubernetesResourceException {
    Matcher matcher = CORE_RESOURCE_URL_PATH_PATTERN.matcher(urlPath);
    if (!matcher.matches()) {
      throw new ParseKubernetesResourceException();
    }
    KubernetesResource resource =
        new KubernetesResource(
            "",
            "v1",
            matcher.group("resource"),
            matcher.group("subresource"),
            matcher.group("namespace"),
            matcher.group("name"));
    return resource;
  }

  public static KubernetesResource parseRegularResource(String urlPath)
      throws ParseKubernetesResourceException {
    Matcher matcher = REGULAR_RESOURCE_URL_PATH_PATTERN.matcher(urlPath);
    if (!matcher.matches()) {
      throw new ParseKubernetesResourceException();
    }
    KubernetesResource resource =
        new KubernetesResource(
            matcher.group("group"),
            matcher.group("version"),
            matcher.group("resource"),
            matcher.group("subresource"),
            matcher.group("namespace"),
            matcher.group("name"));
    return resource;
  }

  KubernetesResource(
      String apiGroup,
      String apiVersion,
      String resource,
      String subResource,
      String namespace,
      String name) {
    this.apiGroup = apiGroup;
    this.apiVersion = apiVersion;
    this.resource = resource;
    this.subResource = subResource;
    this.namespace = namespace;
    this.name = name;
  }

  private final String apiGroup;
  private final String apiVersion;
  private final String resource;
  private final String subResource;

  private final String namespace;
  private final String name;

  public String getApiGroup() {
    return apiGroup;
  }

  public String getApiVersion() {
    return apiVersion;
  }

  public String getResource() {
    return resource;
  }

  public String getSubResource() {
    return subResource;
  }

  public String getNamespace() {
    return namespace;
  }

  public String getName() {
    return name;
  }
}
