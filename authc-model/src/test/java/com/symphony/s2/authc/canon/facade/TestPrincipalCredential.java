/*
 *
 *
 * Copyright 2021 Symphony Communication Services, LLC.
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

package com.symphony.s2.authc.canon.facade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.canon.facade.UserId;

@SuppressWarnings("javadoc")
public class TestPrincipalCredential
{
  @Test
  public void testRedacted()
  {
    PodAndUserId          podAndUserId = PodAndUserId.newBuilder().build(PodId.newBuilder().build(123), UserId.newBuilder().build(1L));
    
    NewPrincipalCredential newCredential = new NewPrincipalCredential.Builder()
        .withUserId(podAndUserId)
        .build();
    
    assertEquals(PrincipalCredential.REDACTED, newCredential.getRedacted().getRequiredString(PrincipalCredential.ENCODED_PRIVATE_KEY));
  }
}
