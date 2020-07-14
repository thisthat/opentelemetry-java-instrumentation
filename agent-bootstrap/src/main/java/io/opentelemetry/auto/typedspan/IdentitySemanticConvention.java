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
package io.opentelemetry.auto.typedspan;

import io.opentelemetry.trace.Span;

public interface IdentitySemanticConvention {
  void end();

  Span getSpan();

  /**
   * Sets a value for enduser.id
   *
   * @param enduserId Username or client_id extracted from the access token or Authorization header
   *     in the inbound request from outside the system..
   */
  public IdentitySemanticConvention setEnduserId(String enduserId);

  /**
   * Sets a value for enduser.role
   *
   * @param enduserRole Actual/assumed role the client is making the request under extracted from
   *     token or application security context..
   */
  public IdentitySemanticConvention setEnduserRole(String enduserRole);

  /**
   * Sets a value for enduser.scope
   *
   * @param enduserScope Scopes or granted authorities the client currently possesses extracted from
   *     token or application security context. The value would come from the scope associated with
   *     an OAuth 2.0 Access Token or an attribute value in a SAML 2.0 Assertion..
   */
  public IdentitySemanticConvention setEnduserScope(String enduserScope);
}