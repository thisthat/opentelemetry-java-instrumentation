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

public interface FaasSemanticConvention {
  void end();

  Span getSpan();

  
  /**
   * Sets a value for faas.trigger
   * @param faasTrigger Type of the trigger on which the function is executed.
   */
  FaasSemanticConvention setFaasTrigger(String faasTrigger);

  /**
   * Sets a value for faas.execution
   * @param faasExecution The execution id of the current function execution.
   */
  FaasSemanticConvention setFaasExecution(String faasExecution);

}